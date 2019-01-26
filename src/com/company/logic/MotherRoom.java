package com.company.logic;

import javax.swing.*;
import java.util.ArrayList;

public class MotherRoom extends Room{

    public MotherRoom(JTextArea output, ArrayList<Item> itemList){
        super(output,itemList);
        roomName = "Parent's room";
        description = "This seems to be the master bedroom";

    }

    @Override
    public void run(String[] command) {
        if(command[0].equals("go")){
            this.display();
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

    private void puzzleControl(){

    }
}
