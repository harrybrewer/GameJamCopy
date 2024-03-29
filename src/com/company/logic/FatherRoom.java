package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class FatherRoom extends Room {

    FatherRoom(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output,response,itemList,player);
        roomName = "Office";
        description = "Walking into the office you appreciate the neatness and order of the room.\n"+
                "A single ray of light exposed from the crack in the blinds hits the desk, almost like a\n spotlight in a play.\n\n" +
                "On the opposite wall you notice a safe embedded into the foundations of the house, \n whatever is in here must be important...";
        hint = "You need to look at the items in the room to get the code for the safe,\n <use safe> to access safe";
    }
    @Override
    public void run(String[] command) {
        switch (command[0]) {
            case "go":
                display();
                break;
            case "take":
                if(command.length == 1){
                    response.setText("No item selected to take");
                }else{
                    takeItem = false;
                    String followUp = command[1];
                    for(Item item: itemList){
                        if(item.getItem().toLowerCase().equals(followUp) && !item.getTaken()){
                            output.setText("You pick up the " + item.getItem() + "\n" + item.getDescription());
                            response.setText("");
                            player.addItem(item);
                            takeItem = true;
                        }
                    }
                    if(!takeItem){
                        response.setText("There is no item of that name");
                    }
                }
                break;
            case "use":
                boolean hasItem = fetchItemFromInventory(command[1]);
                if(!hasItem){
                    if(command[1].equals("safe")){
                        if(!Gui.safeBroken && !Gui.safeCompleted) {
                            output.setText("The safe seems to be locked using a key code of sorts.\n.");
                            response.setText("<Please type in the code e.g. 0000 and press enter>");
                            Gui.safePuzzle = true;
                        }else if(Gui.safeCompleted){
                            output.setText("You have already opened the safe");
                        }
                        else{
                            output.setText("You have already broken the safe");
                        }

                    }else
                        output.setText("You don't have that item");
                }
                break;
            case "search":
                output.setText(displaySearch());
                break;
            case "hint":
                response.setText(hint);
                break;
        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }
}
