package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class BrotherRoom extends Room {

    BrotherRoom(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output, response, itemList, player);
        roomName = "Games room";
        description = "The room is cluttered with old broken consoles, computer equipment and various wires. The walls are plastered with video game box art and posters and various post-it notes\n" +
                "In between the jungle of wires and hardware components you notice an old computer\nthat looks some what functional.\n";
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
                    System.out.println("Taking");
                    String followUp = command[1];
                    for (Item item : itemList) {
                        if (item.getItem().toLowerCase().equals(followUp) && !item.getTaken()) {
                            response.setText("You pick up the " + item.getItem());
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
                if (!hasItem) {
                    if (command[1].toLowerCase().equals("computer")) {
                        useComputer();
                    } else
                        output.setText("You can't seem to find this item");
                }
                break;
            case "search":
                output.setText(displaySearch());
                break;
        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }

    private void useComputer(){
        if(!Gui.computerBroken) {
            output.setText("You fire up the computer and a Windows XP operating system begins to boot up. The display changes to a background of what " +
                    "appears to be a younger version of yourself and a boy who looks like a slightly older version of yourself.\n\n" +
                    "<Press enter to access terminal>");
            Gui.usingComputer = true;
        }else{
            output.setText("You look back at the computer and it is still displaying the same error message");
        }
    }
}