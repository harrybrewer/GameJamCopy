package com.company;

import com.company.logic.Room;

import java.util.HashMap;
import java.util.Map;

public class Hallway {

    public Hallway(){

    }

    Room currentRoom;
    // Contains all room objects.
    Map rooms = new HashMap<String,Object>();

    public void main(){

    }

    private void moveToRoom(String roomName){

        if(roomName == "brothers room"){
            currentRoom = (Room) rooms.get(roomName);
        }
    }


}
