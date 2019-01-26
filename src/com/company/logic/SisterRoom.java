package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class SisterRoom extends Room {
    public SisterRoom(JTextArea output, ArrayList<Item> itemList){
        super(output, itemList);
        roomName = "Bedroom";
        description = "This room appears to be a shared bedroom. There are two beds on either side against the walls. The room consists of objects you" +
                "would expect to be in a bedroom.";
    }

    @Override
    public void run(String[] command) {
        if(command[0].equals("go")){
            display();
        }
        else if(command[0].equals("take")){

        }
        else if(command[0].equals("use")){

        }
        else if(command[0].equals("search")){
            output.setText(displaySearch());
        }
    }

    @Override
    public void display() {
            output.setText(roomName + "\n" + description);
    }
}
