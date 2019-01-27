package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

class ComputerGame {
    private JTextArea output, response, userInput;
    private String currentFolder = "home";
    private ArrayList<String> documentsFolder = new ArrayList<>(), downloadsFolder = new ArrayList<>(), musicFolder = new ArrayList<>();

    ComputerGame(JTextArea output, JTextArea response, JTextArea userInput){
        this.output = output;
        this.response = response;
        this.userInput = userInput;
        initialiseFolders();
    }

    void readUserInput(String command){

        String[]parsedCommand=CommandParser.parseCommand(command);
        output.setText("");
        userInput.setText("");
        response.setText("Computer running\nFor options type 'help'");
        String followUp;
        switch(parsedCommand[0]){
            case "dir":
            case "ls" :
                StringBuilder print = new StringBuilder();
                switch (currentFolder) {
                    case "home":
                        print.append("Documents\nDownloads\nMusic");
                        break;
                    case "doc":
                        for (String s : documentsFolder) {
                            print.append(s).append("\n");
                        }
                        break;
                    case "music":
                        for (String s : musicFolder) {
                            print.append(s).append("\n");
                        }
                        break;
                    case "down":
                        for (String s : downloadsFolder) {
                            print.append(s).append("\n");
                        }
                        break;
                }
                output.setText(print.toString());
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
                String out = "Help Menu\n";
                out += "dir or ls - Will list current directories\n";
                out += "cd <file> - Will change directory\n";
                out += "run <executable> - Will run a program\n";
                out += "exit - Stop using the computer\n";
                output.setText(out);
                break;
            case "run":
                if(parsedCommand.length > 1) {
                    followUp = parsedCommand[1];
                    if (followUp.equals("virus.exe")) {
                        String virusOut = "Windows detected a hard disk problem\n";
                        virusOut += "Please run the Hard Disk Test in System Diagnostics\n";
                        virusOut += "Hard Disk # (XXX)";
                        virusOut += "For more information, please visit:\n";
                        output.setText(virusOut);
                        response.setText("You shut down the computer and get up from the desk.");
                        Gui.computerBroken = true;
                        Gui.usingComputer = false;

                    } else if (followUp.equals("memories.exe")) {
                        output.setText(" As the program runs the display changes between pictures and videos of two young \n boys, seemingly brothers, doing various activities:" +
                                "\n a day out to the city, a picture of the boys on a roller coaster, on a road trip." +
                                "\n\n As the program moves through the photos and videos, you slowly begin to recognise the two boys:" +
                                "\n it is you and your older brother, and some of your most cherished memories together." +
                                "\n You shut down the computer and get up from the desk.");
                        response.setText("You are back in the games room");
                        Gui.computerCompleted = true;
                        Gui.usingComputer = false;
                    }
                }else{
                    response.setText("Please enter a value after the command");
                }

                break;
            case "exit":
                Gui.usingComputer = false;
                output.setText("Exiting terminal");
                break;
            default:
                output.setText("Invalid command");
                break;
        }
    }

    private void initialiseFolders(){
        documentsFolder.add("VIRUS.exe");
        documentsFolder.add("memories.exe");

        downloadsFolder.add("Single_Russians.html");
        downloadsFolder.add("BanderSnatch.pdf");
        downloadsFolder.add("Final_Report.docs");

        musicFolder.add("Life_On_Mars.mp3");
        musicFolder.add("Take_On_Me.mp3");
        musicFolder.add("Never_Gunna_Give_You_Up.mp3");
        musicFolder.add("Waterloo.mp3");
        musicFolder.add("Country_roads.mp3");
    }
}
