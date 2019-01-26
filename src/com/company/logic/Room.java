package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

/*
    Room class used to model each room in the game.
    Contains attributes that define that room
*/
public abstract class Room {
    protected String roomName;
    protected String description;
    protected String[] command;
    protected JTextArea output;
    protected JTextArea response;
    protected ArrayList<Item> itemList;
    protected Player player;
    protected Boolean takeItem;

    public Room(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player) {
        this.output = output;
        this.response = response;
        this.itemList = itemList;
        this.player = player;
    }

    public abstract void run(String[] command);
    public abstract void display();

    protected String displaySearch(){
        String itemString = "After a quick look around the room you find the following items -\n";
        for(Item i : itemList){
            if(!i.getTaken())
                itemString+= i.getItem() + "\n";
        }
        return itemString;
    }
}
