package com.company.logic;

import javax.swing.*;

public class SisterRoom extends Room {
    public BrotherRoom(JTextArea output){
        super(output);
        roomName = "Bedroom";
        description = "This room appears to be a shared bedroom. There are two beds on either side against the walls. The room consists of objects you" +
                "would expect to be in a bedroom.";
    }

    @Override
    public void run(String[] command) {
        if(command[0].equals("go")){
            display();
        }
        else if(command[0].equals("take")){

        }
        else if(command[0].equals("use")){

        }
        else if(command[0].equals("search")){
            searchRoom();
        }
    }

    private void searchRoom(){
        output.setText("You search the room and find a diary in one of the bedside tables.");
    }
}
