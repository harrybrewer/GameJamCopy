package com.company.logic;

import javax.swing.*;

public class Hallway extends Room {


    public Hallway(JTextArea output) {
        super(output);
        roomName = "Hallway";
        description = "You are in the hallway";
    }

    @Override
    public void run(String[] command) {
        switch(command[0]){
            case "take":

                break;
        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }
}
