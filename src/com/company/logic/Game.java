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
    private JTextArea responseRef;
    private Map<String,Room> rooms = new HashMap<>();
    private Room currentRoom;
    private List<item> motherItems = new ArrayList<>(), brotherItem = new ArrayList<>(), fatherItem = new ArrayList<>(), sisterItemm = new ArrayList<>();

    public Game(JTextArea outputRef, JTextArea inputRef, JTextArea responseRef){
        this.outputRef = outputRef;
        this.inputRef = inputRef;
        this.responseRef = responseRef;
        rooms.put("Hallway", new Hallway(outputRef));
        rooms.put("Mother", new MotherRoom(outputRef));

        currentRoom = rooms.get("Hallway");
    }

    // Master method to activate the game;
    public void run(){
        outputRef.setText("Oooof; Wrong choice, mate.");
    }

    public void readUserInput(String command){
        String[] parsedCommand = CommandParser.parseCommand(command);
        String followUp;
        switch (parsedCommand[0]){
            case "go":
                if(parsedCommand.length == 1){
                    responseRef.setText("No destination set");
                }else {
                    followUp = parsedCommand[1];
                    if (followUp.equals("brother") || followUp.equals("brothers") || followUp.equals("brother's")) {
                        responseRef.setText("Going to Brothers");
                    } else if (followUp.equals("mother") || followUp.equals("mothers") || followUp.equals("mother's")) {
                        currentRoom = rooms.get("Mother");
                        sendCommand(currentRoom, parsedCommand);
                        responseRef.setText("Going to mothers");
                    } else if (followUp.equals("father") || followUp.equals("fathers") || followUp.equals("father's")) {
                        responseRef.setText("Going to Fathers");
                    } else if (followUp.equals("sister") || followUp.equals("sisters") || followUp.equals("sister's")) {
                        responseRef.setText("Going to Sisters");
                    } else {
                        responseRef.setText("Invalid command");
                    }
                    inputRef.setText(" ");
                }
                break;
            case "take":
                sendCommand(currentRoom, parsedCommand);
                responseRef.setText("Take");
                inputRef.setText(" ");
                break;
            case "search":
                sendCommand(currentRoom, parsedCommand);
                responseRef.setText("Search");
                inputRef.setText(" ");
                break;
            case "use":
                sendCommand(currentRoom, parsedCommand);
                responseRef.setText("Use");
                inputRef.setText(" ");
                break;
            case "time":
                responseRef.setText("Time");
                inputRef.setText(" ");
                //Check current time
                break;
            case "leave":
                currentRoom = rooms.get("Hallway");
                responseRef.setText("Leave");
                inputRef.setText(" ");
                sendCommand(currentRoom, parsedCommand);
                break;
            case "help":
                responseRef.setText("Help page");
                inputRef.setText("");
                break;
             default:
                 inputRef.setText("Invalid command");
                 inputRef.setText(" ");
                 break;
        }
    }

    private void sendCommand(Room currentRoom, String[] command){
        currentRoom.run(command);
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

     item(String item, Boolean taken, String description){
         this.item = item;
         this.taken = taken;
         this.description = description;
     }
}
