package com.company.logic;

import javax.swing.*;
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

    public Room(JTextArea output) {
        this.output = output;
    }

    public abstract void run();
    public abstract void display();

}
