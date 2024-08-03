package com.scaler.splitwiselld.Controllers;

import com.scaler.splitwiselld.Services.ExpenseService;
import com.scaler.splitwiselld.Strategies.Transaction;
import com.scaler.splitwiselld.dtos.SettleUpResponseDto;
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


    public SettleUpResponseDto SettleUpUser(SettleUpUserRequestDto settleUpUserRequestDto)
    {

       // SettleUpUserRequestDto settleUpUserRequestDto = new SettleUpUserRequestDto();

        SettleUpResponseDto settleUpResponseDto=new SettleUpResponseDto();

        long userID = settleUpUserRequestDto.getUserID();

        SettleUpResponseDto SettleUpResponseDto;
        try{
            List<Transaction> transactions = expenseService.SettleUpUser(userID);

            settleUpResponseDto.setMessage("Successfully settled user");
            settleUpResponseDto.setStatus("Success");
            settleUpResponseDto.setTransactionList(transactions);
            return settleUpResponseDto;
        }catch (Exception e){
           settleUpResponseDto.setStatus("Error");
           settleUpResponseDto.setMessage(e.getMessage());
           return settleUpResponseDto;
        }

        //return new ArrayList<>();

    }
}
