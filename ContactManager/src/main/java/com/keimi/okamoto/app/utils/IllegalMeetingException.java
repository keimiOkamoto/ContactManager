package com.keimi.okamoto.app.utils;

/**
 * This class is mhy own exception.
 * It will print out a message to prompt
 * users if the meeting is illegal.
 */
public class IllegalMeetingException extends Exception {
    /**
     * If the meeting doesn't have at least
     * one contact.
     */
    public IllegalMeetingException() {
        super("Meeting must contain at least one contact.");
    }
}
