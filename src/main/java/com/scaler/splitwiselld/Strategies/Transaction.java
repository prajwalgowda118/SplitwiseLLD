package com.scaler.splitwiselld.Strategies;

import com.scaler.splitwiselld.Models.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Transaction {

    private User from;
    private User to;
    private double amount;

}
