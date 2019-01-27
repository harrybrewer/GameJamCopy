package com.company.logic;

import java.util.ArrayList;

class Player {
    private ArrayList<Item> takenItems;
    familyPhoto familyPhoto;

    Player(ArrayList<Item> takenItems, familyPhoto familyPhoto){
        this.takenItems = takenItems;
        this.familyPhoto = familyPhoto;
    }

    void addItem(Item item){
        takenItems.add(item);
        item.setTaken(true);
    }

    ArrayList<Item> getTakenItems() { return takenItems; }
}

class familyPhoto{
    private Integer mother, father, sister, brother;
    familyPhoto(Integer mother, Integer father, Integer sister, Integer brother){
        this.brother = brother;
        this.father = father;
        this.mother = mother;
        this.sister = sister;
    }

    Integer getTotal(){
        return mother + father + sister + brother;
    }

    String getMembers(){
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

    void setFather(Integer father) { this.father = father; }

    public void setMother(Integer mother) { this.mother = mother; }

    void setBrother(Integer brother) { this.brother = brother; }

    public void setSister(Integer sister) { this.sister = sister; }
}
