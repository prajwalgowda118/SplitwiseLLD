package com.scaler.splitwiselld.Commands;

public interface Command {

    boolean matches(String command);

    void execute (String command);


}
