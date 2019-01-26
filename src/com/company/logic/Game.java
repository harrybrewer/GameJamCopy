package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Game object used to deal with the flow of logic.

    Houses any game specific objects and also dictates what the GUI must draw
*/
public class Game {
    private JTextArea outputRef;
    private JTextArea inputRef;
    private Hallway hallway;
    private Map<String,Room> rooms = new HashMap<>();
    private Room currentRoom;
    private List<item> motherItems = new ArrayList<>(), brotherItem = new ArrayList<>(), fatherItem = new ArrayList<>(), sisterItemm = new ArrayList<>();


    public Game(JTextArea outputRef, JTextArea inputRef){
        this.outputRef = outputRef;
        this.inputRef = inputRef;
        rooms.put("Hallway", new Hallway(outputRef));
        rooms.put("Mother", new MotherRoom(outputRef));

        currentRoom = rooms.get("Hallway");
    }

    // Master method to activate the game;
    public void run(){
        outputRef.setText("Oooof; Wrong choice, mate.");
    }

    /*
        Function used to load a new room into the game.
    */
    private void loadRoom(String name){

    }

    public void readUserInput(String command){
        String[] parsedCommand = CommandParser.parseCommand(command);
        String followUp;
        switch (parsedCommand[0]){
            case "test":
                outputRef.setText("This is a test");
                break;
            case "go":
                followUp = parsedCommand[1];
                if(followUp.equals("brother") || followUp.equals("brothers") || followUp.equals("brother's")){

                }else if(followUp.equals("mother") || followUp.equals("mothers") || followUp.equals("mother's")){
                    currentRoom = rooms.get("Mother");


                }else if(followUp.equals("father") || followUp.equals("fathers") || followUp.equals("father's")){


                }else if(followUp.equals("sister") || followUp.equals("sisters") || followUp.equals("sister's")){

                }else{
                    inputRef.setText("Invalid command");
                }

                break;
            case "take":
                //Give room to command
                break;
            case "use":
                //give command to room
                break;
            case "time":
                //Check current time
                break;
            case "search":
                //Things you can interact with in the room
                //Give command to room
                break;
            case "leave":
                currentRoom = rooms.get("Hallway");
                break;
            case "help":
                outputRef.setText("Help page");
                inputRef.setText("Press enter to continue");
                break;
            case "Press":
                //start room again
                break;
             default:
                 inputRef.setText("Invalid command");
                 break;
        }
    }

    private void sendCommand(String[] command, Room currentRoom){

    }

    private void setUpItems(){
        motherItems.add(new item("Vase", false, "Poop"));

        fatherItem.add(new item("Journal", false, "A old journal, with a bookmark holding a page open"));
        fatherItem.add(new item("Calendar", false, "A calendar open to the month of June"));


    }
}

class item {
    private String item;
    private Boolean taken;
    private String description;

     public item(String item, Boolean taken, String description){
         this.item = item;
         this.taken = taken;
         this.description = description;
     }
}
