package com.company.logic;

import javax.swing.*;

public class LocketPuzzle {
    private JTextArea output, response, userInput;

    LocketPuzzle(JTextArea output, JTextArea response, JTextArea userInput){
        this.output = output;
        this.response = response;
        this.userInput = userInput;
    }

    void readUserInput(String command) {
        String[] parsedCommand = CommandParser.parseCommand(command);
        output.setText("");
        userInput.setText("");
        response.setText("Searching for missing photo");
        switch (parsedCommand[0]) {
            case "search":
                if(parsedCommand.length == 1){
                    // Change these
                    output.setText("You can search:\nBed\nDesk\nBedside Table\nWardrobe\nCoat");
                }else {
                    switch (parsedCommand[1]) {
                        // Match above choices
                        case "bed":
                            output.setText("You do not find anything here");
                            break;
                        case "desk":
                            output.setText("You do not find anything here");
                            break;
                        case "bedside":
                            output.setText("You do not find anything here");
                            break;
                        case "wardrobe":
                            output.setText("You do not find anything here");
                            break;
                        case "coat":
                            output.setText("You find the missing page");
                            response.setText("You are now back in the small bedroom");
                            Gui.diaryCompleted = true;
                            Gui.diaryPuzzle = false;
                            break;
                    }
                }
                break;
            default:
                response.setText("No such command");
                break;
        }
    }
}
