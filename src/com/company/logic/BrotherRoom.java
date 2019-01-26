package com.company.logic;

import com.company.ui.Gui;

public class BrotherRoom {
    private Gui Gui = new Gui();
    private void initialise(){
        String mainText = "You have entered a single bedroom that appears to be for a male. The room is neat and clean, the bed has been made./" +
                "There is an old computer on the desk.";
        Gui.output.setText(mainText);

        String input = Gui.userInput.getText();
        while(true){
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
