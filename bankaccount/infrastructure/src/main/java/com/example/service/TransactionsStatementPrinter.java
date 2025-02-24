package com.example.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.model.Account;
import com.example.model.Transaction;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class to print transactions statement
 */
@Slf4j
@UtilityClass
public class TransactionsStatementPrinter {
    /**
     * print Transactions statement of an account
     * @param account
     */
    public static void printTransactionsStatement(Account account) {
        
        log.info("Transactions statement for Account: " + account.getClient().getName());
        log.info("*************************************************");
        List<Transaction> transactions = account.getTransactions();
        for (Transaction transaction : transactions) {
            log.info("Date : " + transaction.getTransactionTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            log.info("Amount : " + transaction.getAmount().toString());
            log.info("Balance : " + transaction.getBalanceAfter().toString());
        }
        log.info("*************************************************");
        log.info("Date of statement : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }
}
