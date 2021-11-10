package com.stswengists;

public class ScheduleConflictException extends RuntimeException {
    ScheduleConflictException(String msg) {
        super(msg);
    }
}
