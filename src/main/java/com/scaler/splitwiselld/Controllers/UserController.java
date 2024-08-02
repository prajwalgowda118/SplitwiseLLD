package com.scaler.splitwiselld.Controllers;

import com.scaler.splitwiselld.Exception.UserAlreadyFoundException;
import com.scaler.splitwiselld.Exception.UserNotFoundException;
import com.scaler.splitwiselld.Models.User;
import com.scaler.splitwiselld.Services.UserService;
import com.scaler.splitwiselld.dtos.UserRegisterRequestDTo;
import com.scaler.splitwiselld.dtos.UserRegisterResponseDTO;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTo userRegisterRequestDTo) {

        User user;
        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();

        try
        {
               user = userService.registerUser(
                            userRegisterRequestDTo.getUsername(),
                                 userRegisterRequestDTo.getPassword(),
                                       userRegisterRequestDTo.getPhoneNumber()
               );

            responseDTO.setUserId(user.getId());
            responseDTO.setStatus("Success");
            responseDTO.setMessage("User has been registered successfully");
            return responseDTO;

        } catch (UserAlreadyFoundException e) {
            responseDTO.setStatus("Failuree");
            responseDTO.setMessage(e.getMessage());
            return responseDTO;
        }


    }
}
