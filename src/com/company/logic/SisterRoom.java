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
                    if(command[1].equals("diary")){
                        output.setText("You open the diary to where the gap was. It opens to a page that has been half torn \nout. It reads:" +
                                "\n\n Today was a good day. It could've gone a lot worse, had it not been for Craig. I had \nrecently broken up with a dickhead boyfriend" +
                                " so had been feeling down all week. In an \nattempt to cheer me up my older brother took me to our favourite park downtown." +
                                " I was \nactually beginning to cheer up but then -" +
                                "\n\n The rest of the entry has been torn out. Feeling a sense of familiarity about this story, \nyou begin looking for the missing part of " +
                                "the page");
                        Gui.diaryPuzzle = true;
                    }
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
