package com.scaler.splitwiselld.Commands;

import com.scaler.splitwiselld.Controllers.UserController;
import com.scaler.splitwiselld.Models.User;
import com.scaler.splitwiselld.dtos.UpdateProfileRequestDto;
import com.scaler.splitwiselld.dtos.UpdateProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Component
public class UpdateUserCommand implements Command {

    private final UserController userController;
   @Autowired
   public UpdateUserCommand(UserController userController) {
       this.userController=userController;

   }
    @Override
    public boolean matches(String command) {


        List<String>  inputWords= Arrays.stream(command.split(" ")).toList();

        if(inputWords.size() == 3 && inputWords.get(1).equalsIgnoreCase(CommandKeyWords.UPDATE_USER) ){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String command) {

        List<String> inputWords= Arrays.stream(command.split(" ")).toList();

        String password=inputWords.get(2);
        long userID=Long.parseLong(inputWords.get(0));

        UpdateProfileRequestDto updateProfileRequestDto=new UpdateProfileRequestDto();

        updateProfileRequestDto.setPassword(password);
        updateProfileRequestDto.setUserID(userID);

        UpdateProfileResponseDto updateProfileResponseDto= userController.updateUser(updateProfileRequestDto);

        System.out.println(updateProfileResponseDto);



    }
}
