package com.scaler.splitwiselld.Strategies;


import com.scaler.splitwiselld.Models.Expense;
import com.scaler.splitwiselld.Models.User;
import com.scaler.splitwiselld.Models.UserExpenseType;
import com.scaler.splitwiselld.Models.userExpense;
import com.scaler.splitwiselld.Repository.UserExpenseRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component("twoSetsSettleUpStrategy")
public class TwoSetsSettleStrategies implements  SettleUpStrategies{

    private UserExpenseRepository userExpenseRepository;
    @Autowired
    public TwoSetsSettleStrategies(UserExpenseRepository userExpenseRepository) {
        this.userExpenseRepository = userExpenseRepository;
    }
    @Override
    public List<Transaction> settleUpTransactions(List<Expense> expenses) {

        List<userExpense> userExpenses = userExpenseRepository.findAllByExpenseIn(expenses);

        HashMap<User, Long> userHashMap = new HashMap<>();
        for (userExpense userExpense : userExpenses)
        {
            User user = userExpense.getUser();
            int CurrentAmount=0;
            if (userHashMap.containsKey(user)) {

                CurrentAmount= Math.toIntExact(userHashMap.get(user));
            }
            if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID))
            {
                userHashMap.put(user, Long.valueOf(CurrentAmount+ userExpense.getAmount()));
            }
            else
            {
                userHashMap.put(user, Long.valueOf(CurrentAmount-userExpense.getAmount()));
            }
        }
        TreeSet<Pair<User, Long>> MorePaidUser = new TreeSet<Pair<User, Long>>();
        TreeSet<Pair<User, Long>> LessPaidUser = new TreeSet<Pair<User, Long>>();

        for(Map.Entry<User, Long> userAmount : userHashMap.entrySet()){

            if(userAmount.getValue()>0){
                //MorePaidUser.add(new Pair<>(entry.getKey(),entry.getValue()));
                MorePaidUser.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));

            }else{
                LessPaidUser.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));
            }
        }
        List<Transaction> transactions = new ArrayList<>();

        while(!LessPaidUser.isEmpty()){

            Pair<User, Long> morePaidUser = MorePaidUser.pollFirst();
            Pair<User, Long> lessPaidUser = LessPaidUser.pollFirst();
            Transaction transaction = new Transaction();
            transaction.setFrom(lessPaidUser.a);
            transaction.setTo(morePaidUser.a);

            if(Math.abs(lessPaidUser.b)<morePaidUser.b){
                transaction.setAmount(Math.abs(lessPaidUser.b));

                if(!((morePaidUser.b-Math.abs(lessPaidUser.b)) ==0))
                {
                     MorePaidUser.add(new Pair<>(morePaidUser.a, morePaidUser.b-Math.abs(lessPaidUser.b)));
                }

            }else{

                transaction.setAmount(morePaidUser.b);
                if(!((morePaidUser.b+lessPaidUser.b) ==0))
                {
                    LessPaidUser.add(new Pair<>(lessPaidUser.a, morePaidUser.b+lessPaidUser.b));
                }
            }

        }
        return transactions;

    }
}
