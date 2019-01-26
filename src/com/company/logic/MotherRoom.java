package com.company.logic;

import javax.swing.*;

public class MotherRoom extends Room{

    public MotherRoom(JTextArea output){
        super(output);
        roomName = "Parent's room";
        description = "This seems to be the master bedroom";

    }

    @Override
    public void run(String[] command) {
        if(command[0].equals("go")){

        }else if(command[0].equals("take")){

        }else if(command[0].equals("use")){

        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }

    private void puzzleControl(){

    }
}
