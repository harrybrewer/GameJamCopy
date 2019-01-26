package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class FatherRoom extends Room {

    public FatherRoom (JTextArea output, ArrayList<Item> itemList){
        super(output,itemList);
        roomName = "Office";
        description = "Walking into the office you appreciate the neatness and order of the room.\n"+
        "The dimly lit room being illuminated by the half opened window ";
    }
    @Override
    public void run(String[] command) {
        if(command[0].equals("go")){
            display();
        }else if(command[0].equals("take")){

        }else if(command[0].equals("use")){

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
