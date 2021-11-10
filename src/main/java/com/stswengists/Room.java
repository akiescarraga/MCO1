package com.stswengists;

import org.apache.commons.lang3.*;

public class Room {
    private String roomName;
    private final int maxCapacity;
    private int currCapacity;

    public Room(String roomName, int maxCapacity) {
        notBlank(roomName,
                "Room name cannot be blank, empyt or whitespace");
        Validate.isTrue(StringUtils.isAlphaNumeric(roomName),
                "Room Name must be alphanumeric, was: " + roomName);

        this.roomName = roomName;
        this.maxCapacity = maxCapacity;
        this.currCapacity = currCapacity;
    }
}