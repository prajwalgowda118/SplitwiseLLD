package com.scaler.splitwiselld.Controllers;

import com.scaler.splitwiselld.Services.ExpenseService;
import com.scaler.splitwiselld.Strategies.Transaction;
import com.scaler.splitwiselld.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwiselld.dtos.SettleUpGroupResponseDto;
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
    public SettleUpGroupResponseDto SettleUpGroup(SettleUpGroupRequestDto settleUpGroupRequestDto){

        long groupID= settleUpGroupRequestDto.getGroupId();

        List<Transaction> transactions = expenseService.SettleUpGroup(groupID);

        SettleUpGroupResponseDto settleUpGroupResponseDto=new SettleUpGroupResponseDto();

        settleUpGroupResponseDto.setMessage("Successfully settled group");
        settleUpGroupResponseDto.setStatus("Success");
        settleUpGroupResponseDto.setTransactionList(transactions);

        return settleUpGroupResponseDto;

    }
}
