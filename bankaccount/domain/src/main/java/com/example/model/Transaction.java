package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Transaction class model
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private LocalDateTime transactionTime; // transaction time
    private TransactionType type; // transaction type
    private BigDecimal amount; // transaction amount
    private BigDecimal balanceAfter; // new balance after transaction
}
