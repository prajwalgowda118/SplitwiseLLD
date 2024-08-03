package com.scaler.splitwiselld.Controllers;

import com.scaler.splitwiselld.Services.ExpenseService;
import com.scaler.splitwiselld.Strategies.Transaction;
import com.scaler.splitwiselld.dtos.SettleUpUserRequestDto;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseController {

    private ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }


    public List<Transaction> SettleUpUser(SettleUpUserRequestDto settleUpUserRequestDto){


        SettleUpUserRequestDto settleUpUserRequestDto1 = new SettleUpUserRequestDto();

        long userID = settleUpUserRequestDto.getUserID();

        List<Transaction> transactions = expenseService.SettleUpUser(userID);




        return new ArrayList<>();

    }
}
