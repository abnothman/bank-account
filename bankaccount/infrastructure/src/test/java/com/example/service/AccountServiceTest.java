package com.example.service;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.exception.InvalidAmountException;
import com.example.exception.NullAccountException;
import com.example.model.Account;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private final AccountService accountService = new AccountService();

    @Mock
    private Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDepositSuccess() throws InvalidAmountException, NullAccountException {
        // Given
        BigDecimal amount = new BigDecimal("100.00");
        
        // When
        when(account.getBalance()).thenReturn(new BigDecimal("500.00"));
        accountService.deposit(account, amount);

        // Then
        verify(account).deposit(account, amount);
        verify(account).getBalance(); // 
    }

    @Test
    void testWithdrawSuccess() {
        // Given
        BigDecimal amount = new BigDecimal("50.00");
        // When
        when(account.getBalance()).thenReturn(new BigDecimal("500.00"));

        accountService.withdraw(account, amount);
        // Then
        verify(account).withdraw(account, amount);
        verify(account).getBalance();
    }
}