package com.scaler.splitwiselld.Strategies;

import com.scaler.splitwiselld.Models.Expense;

import java.util.List;

public interface SettleUpStrategies {

    List<Transaction> settleUpTransactions(List<Expense> expenses);
}
