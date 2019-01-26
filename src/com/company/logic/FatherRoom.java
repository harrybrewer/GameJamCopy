package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class FatherRoom extends Room {

    public FatherRoom (JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output,response,itemList,player);
        roomName = "Office";
        description = "Walking into the office you appreciate the neatness and order of the room.\n"+
        "A single ray of light exposed from the crack in the blinds hits the desk, almost like a spotlight in a play.\n" +
                "On the opposite wall you notice a safe embedded into the foundations of the house, what ever is in here must be important...";
    }
    @Override
    public void run(String[] command) {
        switch (command[0]) {
            case "go":
                display();
                break;
            case "take":
                if(command.length == 1){
                    response.setText("No item selected to take");
                }else{
                    takeItem = false;
                    System.out.println("Taking");
                    String followUp = command[1];
                    for(Item item: itemList){
                        if(item.getItem().toLowerCase().equals(followUp) && !item.getTaken()){
                            response.setText("You pick up the " + item.getItem());
                            player.addItem(item);
                            takeItem = true;
                        }
                    }
                    if(!takeItem){
                        response.setText("There is no item of that name");
                    }
                }
                break;
            case "use":

                break;
            case "search":
                output.setText(displaySearch());
                break;
        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }
}
