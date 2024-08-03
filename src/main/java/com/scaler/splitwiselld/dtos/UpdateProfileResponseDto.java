package com.scaler.splitwiselld.dtos;

import com.scaler.splitwiselld.Models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateProfileResponseDto {

    private User user;
    private String message;
    private String status;

}
