package com.scaler.splitwiselld.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {

    private List<Command> commands ;
    @Autowired
    public CommandRegistry(RegisterUserCommand registerUserCommand,
                           UpdateUserCommand updateUserCommand) {
        commands = new ArrayList<>();
        commands.add(registerUserCommand);
        commands.add(updateUserCommand);
    }

    public void execute(String input) {

        for (Command command : commands) {
            if(command.matches(input)){
                command.execute(input);
                break;
            }
        }
    }
}
