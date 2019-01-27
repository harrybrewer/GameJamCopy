package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class SisterRoom extends Room {
    SisterRoom(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output, response, itemList, player);
        roomName = "Bedroom";
        description = "This room appears to be a shared bedroom. There are two beds on either side against\n the walls. The room consists of objects you" +
                " would expect to be in a bedroom. A diary on the desk does catch your eye, though.";
        hint = "<use diary> to start the puzzle";
    }

    @Override
    public void run(String[] command) {
        switch (command[0]) {
            case "go":
                display();
                break;
            case "take":
                if (command.length == 1) {
                    response.setText("No item selected to take");
                } else {
                    takeItem = false;
                    String followUp = command[1];
                    for (Item item : itemList) {
                        if (item.getItem().toLowerCase().equals(followUp) && !item.getTaken()) {
                            output.setText("You pick up the " + item.getItem() + "\n" + item.getDescription());
                            response.setText("");
                            player.addItem(item);
                            takeItem = true;
                        }
                    }
                    if (!takeItem) {
                        response.setText("There is no item of that name");
                    }
                }
                break;
            case "use":
                boolean hasItem = fetchItemFromInventory(command[1]);
                if(!hasItem){
                    if(command[1].equals("diary")){
                        output.setText("The diary appears to be a girl's, but hasn't been written in for a couple years.\n" +
                        "There is a small but visible gap in the middle of the diary.\n");
                        response.setText("<Type 'search' to start the puzzle>");
                        Gui.diaryPuzzle = true;
                    }
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
