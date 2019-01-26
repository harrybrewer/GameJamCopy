package com.company.logic;

import javax.swing.*;

public class SafePuzzle {
    private static int lives = 3;
    private JTextArea output, response, userInput;
    public SafePuzzle(JTextArea output, JTextArea response, JTextArea userInput){
        this.output = output;
        this.response = response;
        this.userInput = userInput;
    }

    public void readUserInput(String command){
        if(lives <=0){
            output.setText("You broke the safe!\n"+
            "<type exit to leave the safe>");
            Gui.safePuzzle = false;
            return;
        }
        String[] parsedCommand = CommandParser.parseCommand(command);
        output.setText("");
        response.setText("Enter the 4 digit key code like -: 0000\n"+
                        "<type exit to leave the safe alone>");
        switch (parsedCommand[0]){
            case "exit":
                Gui.safePuzzle = false;
                break;
            case "1023":
                output.setText("\"CLANK\"\n"+
                                "The safe door unlocks. You slowly pull the door open to find a pristine old stopwatch." +
                        "\nAs you go to touch the stopwatch an overdose of emotions and memories of a father figure attack your mind.\n" +
                        "It's clear as day now. The man who worked in this office was your father...\n");
                Gui.safePuzzle = false;
            default:
                lives--;
                if(lives<=0){
                    output.setText("The safe buzzes for the last time. It seems you have triggered the automatic lock down, it's impossible to open now!");
                    Gui.safePuzzle = false;
                    break;
                }
                output.setText("\"BUZZ\".\n" +
                                "Oh dear. Turns out that wasn't the correct code.\n" +
                                "You have #"+lives+" attempts left!");
                break;

        }
    }
}
