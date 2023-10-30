package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

    private boolean available;
    private String roomName;
    private int roomNum;
    //todo image?

    public Room() {
        roomNum = -99;
        roomName = "";
        available = false;
    }

    public Room(int roomNum) {
        this.available = false;
        this.roomName = "";
        this.roomNum = roomNum;
    }

    public Room(int roomNum, boolean available) {
        this.roomNum = roomNum;
        this.available = available;
    }
    public String toString() {
        //todo, StringBuffer?
        return "Room{roomNum=" + roomNum +
                ", roomName=" + roomName +
                ", available=" + available + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return available == room.available && roomNum == room.roomNum && Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(available, roomName, roomNum);
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
}