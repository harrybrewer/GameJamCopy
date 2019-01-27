package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class MotherRoom extends Room{

    MotherRoom(JTextArea output, JTextArea response, ArrayList<Item> itemList, Player player){
        super(output, response,itemList, player);
        roomName = "Master bedroom";
        description = "This seems to be the master bedroom. The room is generally pretty neat; the bed has \nbeen made, the bedside table is clean, the dressing table" +
                " has a few pieces of cosmetics \nlying around. A locket sticking out from underneath the bed does seem out of place.";
        hint = "<use locket> to start the puzzle";
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
                    if(command[1].equals("locket")){
                        output.setText("Opening the locket reveals a picture of a young man no older than 10 years of age. He \nstrikes a resemblance to someone you know well but you can't quite recall who. " +
                        "\nYou notice the other picture has been misplaced, maybe finding it could give you some \ncontext on who this mysterious yet familiar stranger is?");
                        Gui.locketPuzzle = true;
                        break;
                    }else
                        output.setText("You can't seem to find this item");
                }
                break;
            case "search":
                output.setText(displaySearch());
                break;
            case "hint":
                output.setText(hint);
                break;
        }
    }

    @Override
    public void display() {
        output.setText(roomName + "\n" + description);
    }

}
