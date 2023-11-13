package com.lodgereservation.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

    private boolean available;
    private boolean clean;
    private String roomName;
    private int roomNum;
    //todo image?

    public Room() {
        roomNum = -99;
        roomName = "";
        available = false;
        clean = true;
    }

    public Room(int roomNum) {
        this.available = false;
        this.roomName = "";
        this.roomNum = roomNum;
        clean = true;
    }

    public Room(int roomNum, boolean available) {
        this.roomNum = roomNum;
        this.available = available;
        clean = true;
    }

    public Room(int roomNum, boolean available, boolean clean) {
        this.roomNum = roomNum;
        this.available = available;
        this.clean = clean;
    }

    public String toString() {
        return "Room{roomNum=" + roomNum +
                ", roomName=" + roomName +
                ", available=" + available +
                ", clean=" + clean + "}";
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

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public boolean getClean() {
        return clean;
    }
}