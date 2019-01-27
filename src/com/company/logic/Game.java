package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Game {
    private JTextArea outputRef;
    private JTextArea inputRef;
    private JTextArea responseRef;
    private Map<String,Room> rooms = new HashMap<>();
    private Room currentRoom;
    private ArrayList<Item> motherItems = new ArrayList<>(), brotherItems = new ArrayList<>(), fatherItems = new ArrayList<>(), sisterItems = new ArrayList<>();
    private Player player;
    private boolean finished;

    Game(JTextArea outputRef, JTextArea inputRef, JTextArea responseRef){
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
    void run(){
        currentRoom = rooms.get("Hallway");
        String[] s = {"leave"};
        sendCommand(currentRoom, s);
    }

    void readUserInput(String command){
        responseRef.setText("");
        if(Gui.computerCompleted){
            player.familyPhoto.setBrother(1);
        }
        if(Gui.safeCompleted){
            player.familyPhoto.setFather(1);
        }
        if(Gui.diaryCompleted){
            player.familyPhoto.setSister(1);
        }
        if(Gui.locketCompleted){
            player.familyPhoto.setMother(1);
        }

        if(player.familyPhoto.getTotal() == 4){
            //good ending
            outputRef.setText("You walk back into the hallway as you hear the doorbell.\nYou walk to the door and open it, in surprise you see you family.\n" +
                    "They have a look of relief on their faces upon seeing you");
            responseRef.setText("You have completed the game");
            inputRef.setText("");
            finished = true;
        }else if((Gui.safeCompleted && Gui.diaryCompleted && Gui.locketCompleted && Gui.computerBroken) ||
                (Gui.locketCompleted && Gui.diaryCompleted && Gui.computerCompleted && Gui.safeBroken)){
            //Mild ending
            outputRef.setText("You walk back into the hallway as you hear the doorbell.\nYou walk to the door and open it, you see a woman from the hospital.\n" +
                    "She says to you that you need to come back to the hospital,\n she says your family are there to meet you");
            responseRef.setText("You have completed the game");
            inputRef.setText("");
            finished = true;
        }else if(Gui.diaryCompleted && Gui.locketCompleted && Gui.computerBroken && Gui.safeBroken){
            //Bad ending
            outputRef.setText("You walk back into the hallway as you hear the doorbell.\nYou walk to the door and open it, its the mail man, he has an eviction notice for you.\n" +
                    "You sit down by the door confused and alone.");
            responseRef.setText("You have completed the game");
            inputRef.setText("");
            finished = true;
        }
        if(!finished) {
            String[] parsedCommand = CommandParser.parseCommand(command);
            String followUp;
            switch (parsedCommand[0]) {
                case "go":
                    if (parsedCommand.length == 1) {
                        responseRef.setText("No destination set");
                    } else {
                        followUp = parsedCommand[1];
                        if (currentRoom.roomName.equals("Hallway")) {
                            //noinspection IfCanBeSwitch
                            if (followUp.equals("game") || followUp.equals("games")) {
                                responseRef.setText("Going to the games room");
                                currentRoom = rooms.get("Brother");
                                sendCommand(currentRoom, parsedCommand);
                            } else if (followUp.equals("master")) {
                                currentRoom = rooms.get("Mother");
                                sendCommand(currentRoom, parsedCommand);
                                responseRef.setText("Going to the master bedroom");
                            } else if (followUp.equals("office")) {
                                responseRef.setText("Going to the office");
                                currentRoom = rooms.get("Father");
                                sendCommand(currentRoom, parsedCommand);
                            } else if (followUp.equals("small") || followUp.equals("bedroom")) {
                                responseRef.setText("Going to the small bedroom");
                                currentRoom = rooms.get("Sister");
                                sendCommand(currentRoom, parsedCommand);
                            } else {
                                responseRef.setText("Invalid command");
                            }
                        } else {
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
                    inputRef.setText(" ");
                    break;
                case "use":
                    sendCommand(currentRoom, parsedCommand);
                    inputRef.setText(" ");
                    break;
                case "leave":
                    currentRoom = rooms.get("Hallway");
                    inputRef.setText(" ");
                    sendCommand(currentRoom, parsedCommand);
                    break;
                case "inventory":
                    inputRef.setText("");
                    ArrayList<Item> userItems = player.getTakenItems();
                    if (userItems.size() > 0) {
                        StringBuilder items = new StringBuilder("Items: \n");
                        for (Item item : userItems) {
                            items.append(item.getItem()).append("\n");
                        }
                        outputRef.setText(items.toString());
                    } else {
                        responseRef.setText("You have no items in your inventory");
                    }
                    break;
                case "help":
                    outputRef.setText("Help - typing the following commands will help you interact with the game\n\n" +
                            "search: explore the current room and list items to interact with\n" +
                            "go <room name>: enter one of the rooms - games room, master bedroom, \n        small bedroom, office\n" +
                            "take <item name>: pick up an item and add it to your inventory\n" +
                            "use <item name>: use an item, as long as it is in your inventory\n" +
                            "inventory: displays the items in your inventory\n" +
                            "leave: exits the current room and enter the hallway\n" +
                            "hint: if you need a hint when in a room towards the puzzle"
                    );
                    responseRef.setText("Help page");
                    inputRef.setText("");
                case "hint":
                    sendCommand(currentRoom, parsedCommand);
                    inputRef.setText(" ");
                    break;
                default:
                    responseRef.setText("Invalid command");
                    inputRef.setText(" ");
                    break;
            }
        }
    }

    private void sendCommand(Room currentRoom, String[] command){
        currentRoom.run(command);
    }

    private void setUpItems(){
        // Mother
        motherItems.add(new Item("Vase", false, "A integrate vase", "You examine the vase, it looks ornate, highly decorative"));
        motherItems.add(new Item("Lamp" , false, "This is a lamp", "Its just a lamp"));
        motherItems.add(new Item("Clock" , false, "A alarm clock", "Tick tock goes the clock"));
        // Father
        fatherItems.add(new Item("Journal", false, "A old journal, with a bookmark holding a page open", "This journal has seen some use over the years. The spine has been warn away from\n constant use and the title is barely readable. Opening the page saved by the bookmark you\n read the following" +"\n" +
                        "... \"The safe has finally arrived. I should probably think of a decent key code to secure it. Maybe I can find inspiration in this room? \""));
        fatherItems.add(new Item("Calendar", false, "A calendar sheet filled with dates and events", "The calendar page has a single date circled in red pen. The 10th.\n Curiously it's the only date to be circled.... It must be important."));
        fatherItems.add(new Item("Book", false, "A book all about remembering!", "Examining the front of the book reads - \n" + "\"The KEY to remembering - 23rd edition\""));
        fatherItems.add(new Item("Trophy", false, "An impressive trophy for an impressive someone", "Looking at the trophy, it doesn't look all that helpful..."));

        // Brother
        brotherItems.add(new Item("Guide", false, "A game strategy guide", "You examine a Final Fantasy VI strategy guide"));
        brotherItems.add(new Item("Controller" , false, "A game controller", "You use the Playstation controller to play a level of Crash Bandicoot, you do badly"));
        brotherItems.add(new Item("BoardGame" , false, "Generic board game", "This game seems like a Monopoly of Operations, it really gives your head a twister"));

        //Sister
        sisterItems.add(new Item("TextBook", false, "A school textbook", "You look through the school text book, it's not very interesting"));
        sisterItems.add(new Item("Notebook", false, "A notebook consisting of high school notes.", "You flick through the notebook and see the writing of a girl"));
        sisterItems.add(new Item("Toy" , false, "A stuffed animal", "You squeeze the stuffed dog and it barks"));
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

    String getItem() {
        return item;
    }

    Boolean getTaken() {
        return taken;
    }

    String getDescription() {
        return description;
    }

    void setTaken(Boolean taken) { this.taken = taken; }

    String getUseDescription() {
        return useDescription;
    }
}
