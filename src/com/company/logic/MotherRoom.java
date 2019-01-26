package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class MotherRoom extends Room{

    MotherRoom(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output, response,itemList, player);
        roomName = "Parent's room";
        description = "This seems to be the master bedroom";

    }

    @Override
    public void run(String[] command) {
        switch (command[0]) {
            case "go":
                this.display();
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

    private void puzzleControl(){

    }
}
