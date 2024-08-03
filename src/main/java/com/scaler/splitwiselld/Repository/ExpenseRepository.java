package com.scaler.splitwiselld.Repository;

import com.scaler.splitwiselld.Models.Expense;
import com.scaler.splitwiselld.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    Optional<Expense> findById(Long id);

    List<Expense> findAllByGroups(Group group);
}
