package com.keimi.okamoto.meetingItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IllegalMeetingExceptionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldHaveAppropriateMessage() throws IllegalMeetingException {
        thrown.expect(IllegalMeetingException.class);
        thrown.expectMessage("Meeting must contain at least one contact.");

        throw new IllegalMeetingException();
    }
}
