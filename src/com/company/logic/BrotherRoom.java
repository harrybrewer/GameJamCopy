package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class BrotherRoom extends Room {
    public BrotherRoom(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output, response, itemList, player);
        roomName = "Games room";
        description = "This room appears to be a games room. There is an old computer on the desk in the corner.";
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

    private void useComputer(String[] command){
        output.setText("You fire up the computer and a Windows XP operating system begins to boot up. The display changes to a background of what " +
                "appears to be a younger version of yourself and a boy who looks like a slightly older version of yourself. You open the file explorer" +
                "abd the following folders appear:");
        Gui gui = new Gui();
        gui.usingComputer = true;
    }
}