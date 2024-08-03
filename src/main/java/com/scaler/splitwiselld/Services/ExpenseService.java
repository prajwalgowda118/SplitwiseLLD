package com.scaler.splitwiselld.Services;


import com.scaler.splitwiselld.Exception.UserNotFoundException;
import com.scaler.splitwiselld.Models.Expense;
import com.scaler.splitwiselld.Models.Group;
import com.scaler.splitwiselld.Models.User;
import com.scaler.splitwiselld.Models.userExpense;
import com.scaler.splitwiselld.Repository.ExpenseRepository;
import com.scaler.splitwiselld.Repository.GroupRepository;
import com.scaler.splitwiselld.Repository.UserExpenseRepository;
import com.scaler.splitwiselld.Repository.UserRepository;
import com.scaler.splitwiselld.Strategies.SettleUpStrategies;
import com.scaler.splitwiselld.Strategies.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

        private UserRepository userRepository;
        private UserExpenseRepository userExpenseRepository;
        private ExpenseRepository expenseRepository;
        private SettleUpStrategies settleUpStrategies;
        private GroupRepository groupRepository;

        @Autowired
        public ExpenseService(UserRepository userRepository
        , UserExpenseRepository userExpenseRepository ,
                              ExpenseRepository expenseRepository,
                              @Qualifier("twoSetsSettleUpStrategy")  SettleUpStrategies settleUpStrategies
            , GroupRepository groupRepository) {
            this.userRepository = userRepository;
            this.userExpenseRepository = userExpenseRepository;
            this.expenseRepository = expenseRepository;
            this.settleUpStrategies = settleUpStrategies;
            this.groupRepository = groupRepository;

        }
        public List<Transaction> SettleUpUser(long userId) throws UserNotFoundException {

            List<Transaction> transactions ;

            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent()) {

                throw new UserNotFoundException("User not found");

            }
            List<userExpense> getAllforUser=userExpenseRepository.findAllByUser(user.get());

            List<Expense> expenses = new ArrayList<>();

            for (userExpense userExpense : getAllforUser) {
                expenses.add(userExpense.getExpense());

            }
            transactions=settleUpStrategies.settleUpTransactions(expenses);

            List<Transaction> filteredTransactions = new ArrayList<>();

            for (Transaction transaction : transactions) {

                if(transaction.getFrom().equals(userId) || transaction.getTo().equals(userId)) {
                    filteredTransactions.add(transaction);
                }
            }

            return filteredTransactions;

        }

        public List<Transaction> SettleUpGroup(long groupID){

            List<Transaction> transactions1 ;

            Optional<Group> groupOptional=groupRepository.findById(groupID);
            if (!groupOptional.isPresent()) {
                return null;
            }

            List<Expense> expenses =expenseRepository.findAllByGroups(groupOptional.get());

            transactions1=settleUpStrategies.settleUpTransactions(expenses);

            return transactions1;

        }

}
