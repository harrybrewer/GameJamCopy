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
                    output.setText("You can search:\nVase\nDrawer\nnBathroom\nHeadboard\nBedside lamp");
                }else {
                    switch (parsedCommand[1]) {
                        // Match above choices
                        case "vase":
                            output.setText("You do not find anything inside the vase");
                            break;
                        case "drawer":
                            output.setText("You do not find anything useful here");
                            break;
                        case "bathroom":
                            output.setText("You find a small photograph that fits the locket. it appears to be a woman in her 30s. \nYou place this picture in the" +
                                    " missing space in the locket and it fits perfectly. Seeing the \ntwo photos side by side triggers your memory and you now " +
                                    " recognise that the young \nboy is you and the woman is your mother.\n" +
                                    "You had given this locket as a gift to your mother as a young boy");
                            response.setText("You are now back in the master bedroom");
                            Gui.locketCompleted = true;
                            Gui.locketPuzzle = false;
                            break;
                        case "headboard":
                            output.setText("You do not find anything here");
                            break;
                        case "bedside":
                            output.setText("You do not find anything here");
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
