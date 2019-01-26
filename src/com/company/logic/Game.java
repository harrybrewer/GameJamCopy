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
        this.player = new Player(new ArrayList<>(), new familyPhoto(0,0,0,0));


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
        currentRoom = rooms.get("Hallway");
        String[] s = {"leave"};
        sendCommand(currentRoom, s);
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
                    if(currentRoom.roomName.equals("Hallway")) {
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
                    }else{
                        responseRef.setText("You can't go to that room as you aren't in the hallway");
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
                    StringBuilder items = new StringBuilder("Items: \n");
                    for(Item item : userItems){
                        items.append(item.getItem()).append("\n");
                    }
                    outputRef.setText(items.toString());
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
        motherItems.add(new Item("Vase", false, "Poop", "you use the vase"));
        // Father
        fatherItems.add(new Item("Journal", false, "A old journal, with a bookmark holding a page open", "This journal has seen some use over the years. The spine has been warn away from constant use and the title is barely readable. Opening the page saved by the bookmark you read the following" +"\n" +
                        "... \"The safe has finally arrived. I should probably think of a decent key code to secure it. Maybe I can find inspiration in this room? \""));
        fatherItems.add(new Item("Calendar", false, "A calendar open to the month of June", "you use the calendar"));
        fatherItems.add(new Item("book", false, "A book all about remembering!", "Examining the front of the book reads - \n" + "\"the KEY to remembering - 23rd edition\""));

        // Brother
        brotherItems.add(new Item("Computer", false, "A retro computer form the 90s", "you use the computer"));
        brotherItems.add(new Item("Football table", false, "A classic football table", "you use the football table"));

        //Sister
        sisterItems.add(new Item("Diary", false, "The diary appears to be a girl's, but hasn't been written in for a couple years. " +
                "There is a small but visible gap in the middle of the diary.", "The diary seems to be written from the perspective of young woman. It contains "));
        sisterItems.add(new Item("Notebook", false, "A notebook consisting of high school notes.", "you use the notebook"));
    }
}

class Item {
    private String item;
    private Boolean taken;
    private String description;
    private String useDescription;

     Item(String item, Boolean taken, String description, String useDescription){
         this.item = item;
         this.taken = taken;
         this.description = description;
         this.useDescription = useDescription;
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

    public String getUseDescription() {
        return useDescription;
    }
}
