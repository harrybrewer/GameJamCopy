package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class Hallway extends Room {


    public Hallway(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player) {
        super(output, response, itemList, player);
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
