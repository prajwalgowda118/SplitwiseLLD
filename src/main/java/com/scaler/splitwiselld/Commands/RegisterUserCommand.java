package com.scaler.splitwiselld.Commands;

import com.scaler.splitwiselld.Controllers.UserController;
import com.scaler.splitwiselld.dtos.UserRegisterRequestDTo;
import com.scaler.splitwiselld.dtos.UserRegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterUserCommand implements Command {

    private UserController userController;
    @Autowired
    public RegisterUserCommand(UserController userController1){
        this.userController = userController1;

    }

    @Override
    public boolean matches(String command)
    {
        List<String> commandList= Arrays.stream(command.split(" ")).toList();

        if(commandList.size()==4 && commandList.get(0).equalsIgnoreCase(CommandKeyWords.REGISTER_USER)){

            return true;
        }
        return false;
    }

    @Override
    public void execute(String command) {

        List<String> inpWords = Arrays.stream(command.split(" ")).toList();

        String password = inpWords.get(1);
        String phoneNumber = inpWords.get(2);
        String username = inpWords.get(3);

        UserRegisterRequestDTo userRegisterRequestDTo = new UserRegisterRequestDTo();
        userRegisterRequestDTo.setUsername(username);
        userRegisterRequestDTo.setPassword(password);
        userRegisterRequestDTo.setPhoneNumber(phoneNumber);

        UserRegisterResponseDTO userRegisterResponseDTO=userController.registerUser(userRegisterRequestDTo);

        System.out.println("User registered successfully");
        System.out.println(userRegisterResponseDTO.toString());


    }
}
