package com.stswengists;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;

public class Room {
    private String roomName;
    private final int maxCapacity;
    private int currentCapacity;

    public Room(String roomID, String roomName, int maxCapacity) {
        notBlank(roomName,
                "Room name cannot be blank, empyt or whitespace");
        Validate.isTrue(StringUtils.isAlphanumeric(roomName),
                "Room Name must be alphanumeric, was: " + roomName);

        this.roomName = roomName;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomName.equals(room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName);
    }


    void checkCapacity ()
    {
        if (this.currentCapacity > maxCapacity || this.currentCapacity == maxCapacity) {
            throw new ScheduleConflictException(
                    "room is full "
            );
        }
    }





}