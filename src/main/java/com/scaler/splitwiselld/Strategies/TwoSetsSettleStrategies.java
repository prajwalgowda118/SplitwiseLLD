package com.scaler.splitwiselld.Strategies;

import com.scaler.splitwiselld.Models.Expense;

import java.util.List;

public class TwoSetsSettleStrategies implements  SettleUpStrategies{
    @Override
    public List<Transaction> settleUpTransactions(List<Expense> expenses) {
        return List.of();
    }
}
