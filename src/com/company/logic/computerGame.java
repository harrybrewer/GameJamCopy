package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class computerGame{
    private JTextArea output, response, userInput;
    private String currentFolder = "home";
    private ArrayList<String> documentsFolder = new ArrayList<>(), downloadsFolder = new ArrayList<>(), musicFolder = new ArrayList<>();

    computerGame(JTextArea output, JTextArea response, JTextArea userInput){
        this.output = output;
        this.response = response;
        this.userInput = userInput;
        initialiseFolders();
    }

    public void readUserInput(String command){

        String[]parsedCommand=CommandParser.parseCommand(command);
        output.setText("");
        userInput.setText("");
        response.setText("Computer running");
        String followUp;
        switch(parsedCommand[0]){
            case "dir":
            case "ls" :
                String print = "";
                switch (currentFolder) {
                    case "home":
                        print+="Documents\nDownloads\nMusic";
                        break;
                    case "doc":
                        for (String s : documentsFolder) {
                            print += s + "\n";
                        }
                        break;
                    case "music":
                        for (String s : musicFolder) {
                            print += s + "\n";
                        }
                        break;
                    case "down":
                        for (String s : downloadsFolder) {
                            print += s + "\n";
                        }
                        break;
                }
                output.setText(print);
                break;
            case "cd":
                if(parsedCommand.length > 1) {
                    followUp = parsedCommand[1];
                    switch (followUp) {
                        case "..":
                            currentFolder = "home";
                            break;
                        case "documents":
                            currentFolder = "doc";
                            break;
                        case "downloads":
                            currentFolder = "down";
                            break;
                        case "music":
                            currentFolder = "music";
                            break;
                    }
                }else{
                    response.setText("Please enter a value after the command");
                }
                break;
            case "help":

                break;
            case "run":
                if(parsedCommand.length > 1) {
                    followUp = parsedCommand[1];
                    if (followUp.equals("virus.exe")) {
                        output.setText("Computer dies");
                        Gui.usingComputer = false;
                    } else if (followUp.equals("things.exe")) {
                        output.setText("The good kush");
                    }
                }else{
                    response.setText("Please enter a value after the command");
                }

                break;
            case "exit":
                Gui.usingComputer = false;
                output.setText("Exiting terminal");
                break;
        }
    }

    private void initialiseFolders(){
        documentsFolder.add("VIRUS.exe");
        documentsFolder.add("Things.exe");

        downloadsFolder.add("Single_Russians.html");
        downloadsFolder.add("BanderSnatch.pdf");
        downloadsFolder.add("Final_Report.docs");

        musicFolder.add("Life_On_Mars.mp3");
        musicFolder.add("Take_On_Me.mp3");
        musicFolder.add("Never_Gunna_Give_You_Up.mp3");
        musicFolder.add("Waterloo.mp3");
    }
}
