package com.scaler.splitwiselld;

import com.scaler.splitwiselld.Commands.Command;
import com.scaler.splitwiselld.Commands.CommandRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SplitwiseLldApplication implements CommandLineRunner {

    //private List<Command> commandList;
    private CommandRegistry commandRegistry;
    private Scanner scanner= scanner = new Scanner(System.in);;

    @Autowired
    public SplitwiseLldApplication(CommandRegistry commandRegistry){
        this.commandRegistry = commandRegistry;

    }

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseLldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while(true)
        {
            System.out.println("Tell what do you want to do?");
            String input=scanner.nextLine();
            commandRegistry.execute(input);
            /*if(input.equalsIgnoreCase("R")){
            }*/
        }
    }
}
