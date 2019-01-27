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
    }

    @Override
    public void run(String[] command) {
        if(intro){
            intro = false;
        }
        else{
            description = "You are in the hallway, there are 4 rooms you can enter. \n" +
                    "There is a master a bedroom, an office, a games room and a small bedroom";
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
                                photoOutput.append("You see you Mother");
                                break;
                            case "f":
                                photoOutput.append("You see you Father");
                                break;
                            case "s":
                                photoOutput.append("You see you Sister");
                                break;
                            case "b":
                                photoOutput.append("You see you Brother");
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
