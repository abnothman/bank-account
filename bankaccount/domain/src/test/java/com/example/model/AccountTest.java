package com.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.exception.InvalidAmountException;
import com.example.exception.NullAccountException;

public class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder().build();
    }

    @Test
    void testCreateAccountWithBuilder() {
        //Given
        Client client = Client.builder()
                            .name("Ayoub BEN OTHMAN")
                            .address("5 Rue de la fontaine, 75000 Paris")
                            .build();
        Account testAccount = Account.builder()
                                .balance(new BigDecimal("5000.50"))
                                .client(client)
                                .build();
        
        //When
        BigDecimal balance = testAccount.getBalance();
        Client clientAccount = testAccount.getClient();

        //Then
        assertNotNull(testAccount);
        assertEquals(new BigDecimal("5000.50"), balance);
        assertNotNull(clientAccount);
        assertEquals("Ayoub BEN OTHMAN", clientAccount.getName());
        assertEquals("5 Rue de la fontaine, 75000 Paris", clientAccount.getAddress());
    }

    @Test
    void testAccountDefaultValues() {

        // Given
        BigDecimal balance = account.getBalance();
        List<Transaction> transactions = account.getTransactions();

        //Then
        assertNotNull(account);
        assertEquals(BigDecimal.ZERO, balance);
        assertTrue(transactions.isEmpty());
    }

    @Test
    void testDepositWithNullAmountShouldThrowException() {

        // Given
        Client client = Client.builder()
                            .name("Ayoub BEN OTHMAN")
                            .address("5 Rue de la fontaine, 75000 Paris")
                            .build();
        Account testAccount = Account.builder()
                                .balance(new BigDecimal("5000.50"))
                                .client(client)
                                .build();
        // Then
        assertThrows(InvalidAmountException.class, () -> {
            testAccount.deposit(testAccount, null);
        });
    }

    @Test
    void deposit_NegativeAmount_ShouldThrowException() {
        // Given
        BigDecimal depositAmount = new BigDecimal("-50.00");
        // Then
        assertThrows(InvalidAmountException.class, () -> 
            account.deposit(account, depositAmount));
    }

    @Test
    void testDepositWithNullAccounttShouldThrowException() {
        // Then
        assertThrows(NullAccountException.class, () -> {
            account.deposit(null, BigDecimal.valueOf(500));
        });
    }

    @Test
    void deposit_ShouldIncreaseBalanceAndAddTransaction() {
        //Given
        BigDecimal depositAmount = new BigDecimal("100.00");

        //When
        account.deposit(account, depositAmount);

        //Then
        assertEquals(depositAmount, account.getBalance());
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void withdraw_ShouldDecreaseBalanceAndAddTransaction() {
        // Given
        BigDecimal depositAmount = new BigDecimal("200.00");
        BigDecimal withdrawAmount = new BigDecimal("100.00");
        
        // When
        account.deposit(account, depositAmount);
        account.withdraw(account, withdrawAmount);
        
        // Then
        assertEquals(new BigDecimal("100.00"), account.getBalance());
        assertEquals(2, account.getTransactions().size());
    }

    @Test
    void withdraw_InsufficientBalance_ShouldThrowException() {
        //Given
        BigDecimal withdrawAmount = new BigDecimal("50.00");

        // Then
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(account, withdrawAmount));
    }

    
    
}
