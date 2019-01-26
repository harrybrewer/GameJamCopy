package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/*
    Game object used to deal with the flow of logic.

    Houses any game specific objects and also dictates what the GUI must draw
*/
public class Game {
    private JTextArea outputRef;
    private JTextField inputRef;

    private Collection<Room> rooms = new ArrayList<Room>();
    private Room currentRoom;

    public Game(JTextArea outputRef, JTextField inputRef){
        this.outputRef = outputRef;
        this.inputRef = inputRef;
    }

    // Master method to activate the game;
    public void run(){
        outputRef.setText("Oooof; Wrong choice, mate.");
    }

    /*
        Function used to load a new room into the game.
        Depending on where the player is at the time
    */
    private void loadRoom(){}

    private String readUserInput(){
        inputRef.getText();
        return null;
    }
}
