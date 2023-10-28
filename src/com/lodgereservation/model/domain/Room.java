package com.lodgereservation.model.domain;

public class Room {

    private boolean available;
    private int roomNum;

    public Room() {
        roomNum = -99;
        available = false;
    }

    public Room(int num) {
        roomNum = num;
        available = false;
    }

    public Room(int num, boolean avail) {
        roomNum = num;
        available = avail;
    }

    //todo, return boolean success?
    public void setAvailable(boolean avail) {
        available = avail;
    }

    public void setRoomNum(int rn) {
        roomNum = rn;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public int getRoomNum() {
        return this.roomNum;
    }

    public String toString() {
        //todo, StringBuffer?
        return "room: " + roomNum;
    }
}
