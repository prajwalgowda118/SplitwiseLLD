package com.scaler.splitwiselld.dtos;


import com.scaler.splitwiselld.Strategies.Transaction;
import lombok.Getter;
import lombok.Setter;
import java.util.*;


@Getter
@Setter
public class SettleUpResponseDto {

    private String message;
    private String status;

    private List<Transaction> transactionList;

}
