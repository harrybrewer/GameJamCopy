package com.company.logic;

import java.util.ArrayList;

public class Player {
    private ArrayList<Item> takenItems;
    public familyPhoto familyPhoto;

    Player(ArrayList<Item> takenItems, familyPhoto familyPhoto){
        this.takenItems = takenItems;
        this.familyPhoto = familyPhoto;
    }

    public void addItem(Item item){
        takenItems.add(item);
        item.setTaken(true);
    }

    public ArrayList<Item> getTakenItems() { return takenItems; }
}

class familyPhoto{
    private Integer mother, father, sister, brother;
    familyPhoto(Integer mother, Integer father, Integer sister, Integer brother){
        this.brother = brother;
        this.father = father;
        this.mother = mother;
        this.sister = sister;
    }

    public Integer getTotal(){
        return mother + father + sister + brother;
    }

    public String getMembers(){
        String completed = "";
        if(mother > 0){
            completed += "m";
        }
        if(brother > 0){
            completed += "b";
        }
        if(sister > 0){
            completed += "s";
        }
        if(father > 0){
            completed += "f";
        }
        return completed;
    }

    public void setFather(Integer father) { this.father = father; }

    public void setMother(Integer mother) { this.mother = mother; }

    public void setBrother(Integer brother) { this.brother = brother; }

    public void setSister(Integer sister) { this.sister = sister; }
}
