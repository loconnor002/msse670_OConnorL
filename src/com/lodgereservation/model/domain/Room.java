package com.lodgereservation.model.domain;

public class Room {

    private boolean available;
    private String name;
    private int roomNum;

    public Room() {
        roomNum = -99;
        name = "";
        available = false;
    }

    public Room(int roomNum) {
        this.available = false;
        this.name = "";
        this.roomNum = roomNum;
    }

    public Room(int roomNum, boolean available) {
        this.roomNum = roomNum;
        this.available = available;
    }

    //todo, return boolean success?
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public boolean getAvailable() {
        return available;
    }

    public int getRoomNum() {
        return roomNum;
    }


    public String toString() {
        //todo, StringBuffer?
        return String.valueOf(roomNum);
    }
}
