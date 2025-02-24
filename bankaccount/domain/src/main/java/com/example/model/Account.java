package com.example.model;



import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.exception.InvalidAmountException;
import com.example.exception.NullAccountException;

/**
 * Account class model
 * with business logic
 * Using Hexagonal approach
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO; // account balance
    @Builder.Default
    private List<Transaction> transactions = new ArrayList<>(); // account transactions list
    private Client client; // client holding account

    /**
     * deposit amount method
     * @param account
     * @param amount
     */
    public void deposit(Account account, BigDecimal amount) {
        // null test for the used account
        validateAccount(account);
        // validity test for the used amount
        validateAmount(amount, "Deposit");
        BigDecimal balance = account.getBalance();
        // deposit amount to balance
        BigDecimal newBalance = balance.add(amount);
        account.setBalance(newBalance);
        // add transaction to account's transactions list
        List<Transaction> transactions = account.getTransactions();
        transactions.add(Transaction.builder()
                .transactionTime(LocalDateTime.now())
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .balanceAfter(newBalance)
                .build()
                );
    }

    /**
     * withdraw amount method
     * @param account
     * @param amount
     */
    public void withdraw(Account account, BigDecimal amount) {
        // null test for the used account
        validateAccount(account);
        // validity test for the used amount
        validateAmount(amount, "Withdraw");
        BigDecimal balance = account.getBalance();
        // check of balance sufficency
        if(amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal. Balance : " + balance + " Amount : " + amount);
        }
        // withdraw amount from balance
        BigDecimal newBalance = balance.subtract(amount);
        account.setBalance(newBalance);
        // add transaction to account's transactions list
        List<Transaction> transactions = account.getTransactions();
        transactions.add(
          Transaction.builder()
                    .transactionTime(LocalDateTime.now())
                    .type(TransactionType.WITHDRAWAL)
                    .amount(amount.negate())
                    .balanceAfter(newBalance)
                    .build()  
        );
    }

    /**
     * null test for account method
     * @param account
     */
    private void validateAccount(Account account) throws NullAccountException {
        if (account == null) {
            // throw defined exception for null acount
            throw new NullAccountException("Account cannot be null");
        }
    }

    /**
     * check amount validity method
     * @param amount
     * @param transaction
     */
    private void validateAmount(BigDecimal amount, String transaction) throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            // throw defined exception for invalid amount
            throw new InvalidAmountException(transaction + " amount must be positive: " + amount);
        }
    }

}
