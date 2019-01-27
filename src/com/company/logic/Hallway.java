package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class Hallway extends Room {


    Hallway(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player) {
        super(output, response, itemList, player);
        roomName = "Hallway";
        description = "You are in the hallway, there are 4 rooms you can enter. \n" +
                "There is a master a bedroom, an office, a games room and a small bedroom";
    }

    @Override
    public void run(String[] command) {
        switch(command[0]){
            case "take":
                response.setText("There is nothing to pick up in this room");
                break;
            case "leave":
                display();
                break;
            case "use":
                fetchItemFromInventory(command[1]);
                break;
            case "search":
                StringBuilder photoOutput = new StringBuilder("The only thing you see is a family portrait\n" + "You see a younger version of yourself");
                if(player.familyPhoto.getTotal() == 0){
                    photoOutput.append(" and 4 other people that you don't recognise");
                }else{
                    photoOutput.append("\n");
                    String[] members = player.familyPhoto.getMembers().split("");
                    for(String s: members){
                        switch (s){
                            case "m":
                                photoOutput.append("You see your mother\n");
                                break;
                            case "f":
                                photoOutput.append("You see your Father\n");
                                break;
                            case "s":
                                photoOutput.append("You see your Sister\n");
                                break;
                            case "b":
                                photoOutput.append("You see your Brother\n");
                                break;
                        }
                    }
                }
                output.setText(photoOutput.toString());
                break;
        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }
}
