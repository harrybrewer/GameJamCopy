package com.company.logic;

import com.company.Hallway;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
    Game object used to deal with the flow of logic.

    Houses any game specific objects and also dictates what the GUI must draw
*/
public class Game {
    private JTextArea outputRef;
    private JTextField inputRef;
    private Hallway hallway;

    private Map<String,Room> rooms = new HashMap<>();
    private Room currentRoom;

    public Game(JTextArea outputRef, JTextField inputRef){
        this.hallway = new Hallway();
        this.outputRef = outputRef;
        this.inputRef = inputRef;
    }

    // Master method to activate the game;
    public void run(){
        outputRef.setText("Oooof; Wrong choice, mate.");


        readUserInput();
    }

    /*
        Function used to load a new room into the game.
    */
    private void loadRoom(String name){

    }

    private String readUserInput(){

        String command = "enter brother's room";
        String[] parsedCommand = CommandParser.parseCommand(command);
        System.out.println();

        return null;
    }
}
