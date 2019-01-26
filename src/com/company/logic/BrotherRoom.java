package com.company.logic;

import com.company.ui.Gui;

import javax.swing.*;

public class BrotherRoom extends Room {
    public BrotherRoom(JTextArea output){
        super(output);
        roomName = "Games room";
        description = "This room appears to be a games room. There is an old computer on the desk in the corner.";

    }

    @Override
    public void run(String[] command) {
        if(command[0].equals("go")){
            display();
        }
        else if(command[0].equals("take")){

        }
        else if(command[0].equals("use")){
            if (command[1].toLowerCase().equals("computer")){
                useComputer();
            }
        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }

    private void useComputer(){
        output.setText("You fire up the computer and a Windows XP operating system begins to boot up. The display changes to a background of what/" +
                "appears to be a younger version of yourself and a boy who looks like a slightly older version of yourself.");
    }
}
