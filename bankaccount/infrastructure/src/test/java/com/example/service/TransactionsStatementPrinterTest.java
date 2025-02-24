package com.example.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import com.example.model.Account;
import com.example.model.Client;
import com.example.model.Transaction;
import com.example.model.TransactionType;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class TransactionsStatementPrinterTest {
    private Account account;
    private ListAppender<ILoggingEvent> logAppender;

    @BeforeEach
    void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(TransactionsStatementPrinter.class);
        logAppender = new ListAppender<>();
        logAppender.start();
        logger.addAppender(logAppender);

        Client client = Client.builder()
                .name("Ayoub BEN OTHMAN")
                .address("5 Rue de la fontaine, 75000 Paris")
                .build();

        account = Account.builder()
                .client(client)
                .balance(new BigDecimal("1000.00"))
                .transactions(Arrays.asList(
                        Transaction.builder()
                                .transactionTime(LocalDateTime.of(2024, 2, 6, 10, 30))
                                .type(TransactionType.DEPOSIT)
                                .amount(new BigDecimal("500.00"))
                                .balanceAfter(new BigDecimal("1500.00"))
                                .build(),
                        Transaction.builder()
                                .transactionTime(LocalDateTime.of(2024, 2, 6, 14, 45))
                                .type(TransactionType.WITHDRAWAL)
                                .amount(new BigDecimal("-200.00"))
                                .balanceAfter(new BigDecimal("1300.00"))
                                .build()
                ))
                .build();
    }

    @Test
    void testPrintStatementShouldLogFormattedStatement() {
        //When
        TransactionsStatementPrinter.printTransactionsStatement(account);

        //Then
        List<ILoggingEvent> logs = logAppender.list;
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Transactions statement for Account: Ayoub BEN OTHMAN")));
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Date : 06-02-2024 10:30:00")));
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Amount : 500.00")));
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Balance : 1500.00")));
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Date : 06-02-2024 14:45:00")));
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Amount : -200.00")));
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Balance : 1300.00")));
        assertTrue(logs.stream().anyMatch(event -> event.getFormattedMessage().contains("Date of statement : "  
                                                                                            + LocalDateTime.now().format(DateTimeFormatter
                                                                                                .ofPattern("dd-MM-yyyy HH:mm:ss")))));
    }

}
