package com.keimi.okamoto.meetingItem;

public class IllegalMeetingException extends Throwable {

    public IllegalMeetingException() {
        super("Meeting must contain at least one contact.");
    }
}
