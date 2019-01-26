package com.company.logic;

import javax.swing.*;

public class computerGame{
    private JTextArea output, response, userInput;
    public computerGame(JTextArea output, JTextArea response, JTextArea userInput){
        this.output = output;
        this.response = response;
        this.userInput = userInput;
    }

    public void readUserInput(String command){
        String[]parsedCommand=CommandParser.parseCommand(command);
        output.setText("");
        userInput.setText("");
        response.setText("Computer running");
        switch(parsedCommand[0]){
            case "exit":
                Gui.usingComputer = false;
                output.setText("Exiting terminal");
                break;
        }
    }
}
