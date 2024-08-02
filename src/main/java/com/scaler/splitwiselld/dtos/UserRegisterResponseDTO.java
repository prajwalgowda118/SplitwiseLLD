package com.scaler.splitwiselld.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResponseDTO {

    private Long userId;
    private String status;
    private String message;

}
