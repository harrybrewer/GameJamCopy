package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class SisterRoom extends Room {
    public SisterRoom(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output, response, itemList, player);
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
        }
        else if(command[0].equals("use")){

        }
        else if(command[0].equals("search")){
            output.setText(displaySearch());
        }
    }

    @Override
    public void display() {
            output.setText(roomName + "\n" + description);
    }
}
