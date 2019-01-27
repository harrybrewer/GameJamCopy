package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class Hallway extends Room {

    private boolean intro = true;
    Hallway(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player) {
        super(output, response, itemList, player);
        roomName = "Hallway";
        description = "After running for what seemed like hours you reach the house. It feels foreign but familiar at the time...\n" +
                "You've been drawn here for a reason and you're determined to find out why\n" +
                "You enter the house and enter the hallway. There are a total of 4 rooms you can see\n" +
                "An office\n" +
                "Small bedroom\n" +
                "Master bedroom\n" +
                "And a games rooms\n\n" +
                "Where do you wish to go?";
        response.setText("Click on the panel below and type help to begin playing.");
    }

    @Override
    public void run(String[] command) {
        if(intro){
            intro = false;
        }
        else{
            description = "You enter the house and enter the hallway. There are a total of 4 rooms you can see\n" +
                        "An office\n" +
                        "Small bedroom\n" +
                        "Master bedroom\n" +
                        "And a games rooms\n\n" +
                        "Where do you wish to go?";;
        }
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
