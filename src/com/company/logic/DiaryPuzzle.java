package com.company.logic;

import javax.swing.*;

class DiaryPuzzle {
    private JTextArea output, response, userInput;

    DiaryPuzzle(JTextArea output, JTextArea response, JTextArea userInput){
        this.output = output;
        this.response = response;
        this.userInput = userInput;
    }

    void readUserInput(String command) {
        String[] parsedCommand = CommandParser.parseCommand(command);
        userInput.setText("");
        response.setText("Searching for missing piece");
        switch (parsedCommand[0]) {
            case "search":
                if(parsedCommand.length == 1){
                    output.setText("You can search:\nBed\nDesk\nBedside Table\nWardrobe\nCoat");
                }else {
                    switch (parsedCommand[1]) {
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
                            output.setText("You find the missing page and line it up against the one in the diary. The full entry now \nreads:" +
                                    "\n\n Today was a good day. It could've gone a lot worse, had it not been for Craig. I had \nrecently broken up with a dickhead boyfriend" +
                                    " so had been feeling down all week. In an \nattempt to cheer me up my older brother took me to our favourite park downtown." +
                                    " I was actually beginning to cheer up but then out of nowhere my ex, the same one I broke up \nwith earlier this week turned up in the park." +
                                    " He looked ready to cause trouble but, \nthankfully, Craig stood up to this arsehole and scared him off. I truly don;t know what I \nwould do without him." +
                                    "\n\n Reading the rest of the entry jogs your memory and you recognise that you are Craig,  and this is the diary of your younger sister.");
                            response.setText("You leave the torn page inside and put the diary back on the desk");
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


