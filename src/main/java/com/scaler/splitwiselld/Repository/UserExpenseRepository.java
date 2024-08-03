package com.scaler.splitwiselld.Repository;

import com.scaler.splitwiselld.Models.Expense;
import com.scaler.splitwiselld.Models.User;
import com.scaler.splitwiselld.Models.userExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface UserExpenseRepository extends JpaRepository<userExpense, Long> {

    List<userExpense> findAllByUser(User user);

    List<userExpense> findAllByExpenseIn(List<Expense> expenses);





}
