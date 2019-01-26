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
    protected ArrayList<Item> itemList;

    public Room(JTextArea output, ArrayList<Item> itemList) {
        this.output = output;
        this.itemList = itemList;
    }

    public abstract void run(String[] command);
    public abstract void display();

    protected String displaySearch(){
        String itemString = "";
        for(Item i : itemList){
            if(!i.getTaken())
                itemString+= i.getItem() + "\n";
        }
        return itemString;
    }
}
