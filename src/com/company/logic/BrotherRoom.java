package com.company.logic;

import com.company.ui.gui;

public class BrotherRoom {
    private gui gui = new gui();
    private void initialise(){
        String mainText = "You have entered a single bedroom that appears to be for a male. The room is neat and clean, the bed has been made./" +
                "There is an old computer on the desk.";
        gui.output.setText(mainText);

        String input = gui.userInput.getText();
        while(){
            Command(input);
        }
    }

    void Command(String input){
        String[] action = CommandParser.parseCommand(input);

    }

    void UseComputer(){

    }

    void roomControl(){
        initialise();

    }
}
