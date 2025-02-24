package com.example.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.exception.InvalidAmountException;
import com.example.exception.NullAccountException;
import com.example.model.Account;
import com.example.port.in.AccountServicePort;

import lombok.extern.slf4j.Slf4j;

/**
 * Service to manage account
 */
@Slf4j
@Service
public class AccountService implements AccountServicePort {

    /**
     * deposit to account method
     * @param account
     * @param amount
     */
    @Override
    public void deposit(Account account, BigDecimal amount) throws InvalidAmountException, NullAccountException {
        account.deposit(account, amount);
        log.info("Amount " + " Deposited to account. " + " New Balance : " + account.getBalance());
    }

    /**
     * withdraw from account method
     * @param account
     * @param amount
     */
    @Override
    public void withdraw(Account account, BigDecimal amount) {
        account.withdraw(account, amount);
        log.info("Amount " + " Withdew from account " + " New balance : " + account.getBalance());
    }

}
