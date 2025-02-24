package com.example.port.in;

import java.math.BigDecimal;

import com.example.model.Account;
/**
 * Account service contract model
 * Hexagonal approach
 */
public interface AccountServicePort {

    /**
     * deposit abstract method
     * @param account
     * @param amount
     */
    public void  deposit(Account account, BigDecimal amount);

    /**
     * withdraw abstract method
     * @param account
     * @param amount
     */
    public void  withdraw(Account account, BigDecimal amount);
}
