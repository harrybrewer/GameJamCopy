package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<Item> motherItems = new ArrayList<>(), brotherItems = new ArrayList<>(), fatherItems = new ArrayList<>(), sisterItems = new ArrayList<>();
    private Player player;

    public Game(JTextArea outputRef, JTextArea inputRef, JTextArea responseRef){
        this.outputRef = outputRef;
        this.inputRef = inputRef;
        this.responseRef = responseRef;
        this.player = new Player(new ArrayList<>());


        rooms.put("Hallway", new Hallway(outputRef, responseRef, null, player));
        rooms.put("Mother", new MotherRoom(outputRef, responseRef, motherItems, player));
        rooms.put("Father", new FatherRoom(outputRef, responseRef, fatherItems, player));
        rooms.put("Brother", new BrotherRoom(outputRef, responseRef, brotherItems, player));
        rooms.put("Sister",  new SisterRoom(outputRef, responseRef, sisterItems, player));
        currentRoom = rooms.get("Hallway");

        setUpItems();
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
                        currentRoom = rooms.get("Brother");
                        sendCommand(currentRoom, parsedCommand);
                    } else if (followUp.equals("mother") || followUp.equals("mothers") || followUp.equals("mother's")) {
                        currentRoom = rooms.get("Mother");
                        sendCommand(currentRoom, parsedCommand);
                        responseRef.setText("Going to mothers");
                    } else if (followUp.equals("father") || followUp.equals("fathers") || followUp.equals("father's")) {
                        responseRef.setText("Going to Fathers");
                        currentRoom = rooms.get("Father");
                        sendCommand(currentRoom, parsedCommand);
                    } else if (followUp.equals("sister") || followUp.equals("sisters") || followUp.equals("sister's")) {
                        responseRef.setText("Going to Sisters");
                        currentRoom = rooms.get("Sister");
                        sendCommand(currentRoom, parsedCommand);
                    } else {
                        responseRef.setText("Invalid command");
                    }
                    inputRef.setText(" ");
                }
                break;
            case "take":
                inputRef.setText(" ");
                sendCommand(currentRoom, parsedCommand);

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
            case "inventory":
                inputRef.setText("");
                ArrayList<Item> userItems = player.getTakenItems();
                if(userItems.size() > 0){
                    String items = "Items: \n";
                    for(Item item : userItems){
                        items += item.getItem() + "\n";
                    }
                    outputRef.setText(items);
                }else{
                    responseRef.setText("You have no items in your inventory");
                }

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
        // Mother
        motherItems.add(new Item("Vase", false, "Poop"));
        // Father
        fatherItems.add(new Item("Journal", false, "A old journal, with a bookmark holding a page open"));
        fatherItems.add(new Item("Calendar", false, "A calendar open to the month of June"));

        // Brother
        brotherItems.add(new Item("Test", false, "test object"));

        //Sister
        sisterItems.add(new Item("Diary", false, "The diary appears to be a girl's, but hasn't been written in for a couple years. " +
                "There is a small but visible gap in the middle of the diary."));
        sisterItems.add(new Item("Notebook", false, "A notebook consisting of high school notes."));
    }
}

class Item {
    private String item;
    private Boolean taken;
    private String description;

     Item(String item, Boolean taken, String description){
         this.item = item;
         this.taken = taken;
         this.description = description;
     }

    public String getItem() {
        return item;
    }

    public Boolean getTaken() {
        return taken;
    }

    public String getDescription() {
        return description;
    }

    public void setTaken(Boolean taken) { this.taken = taken; }
}
