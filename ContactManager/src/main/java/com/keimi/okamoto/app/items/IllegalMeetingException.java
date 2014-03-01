package com.keimi.okamoto.app.items;

public class IllegalMeetingException extends Throwable {

    public IllegalMeetingException() {
        super("Meeting must contain at least one contact.");
    }
}
