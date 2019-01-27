package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

/*
    Room class used to model each room in the game.
    Contains attributes that define that room
*/
public abstract class Room {
    String roomName;
    String description;
    JTextArea output;
    JTextArea response;
    ArrayList<Item> itemList;
    Player player;
    Boolean takeItem;

    Room(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player) {
        this.output = output;
        this.response = response;
        this.itemList = itemList;
        this.player = player;
    }

    public abstract void run(String[] command);
    public abstract void display();

    String displaySearch(){
        StringBuilder itemString = new StringBuilder("After a quick look around the room you find the following items -\n");
        for(Item i : itemList){
            if(!i.getTaken())
                itemString.append(i.getItem()).append("\n");
        }
        return itemString.toString();
    }

    boolean fetchItemFromInventory(String itemName){
        ArrayList<Item> temp = player.getTakenItems();
        for(Item i : temp){
            if(i.getItem().toLowerCase().equals(itemName)){
                output.setText(i.getUseDescription());
                return true;
            }
        }
        return false;
    }
}