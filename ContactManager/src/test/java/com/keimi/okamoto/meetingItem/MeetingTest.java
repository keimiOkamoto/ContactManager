package com.keimi.okamoto.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;

public class MeetingTest {

    @Test
    public void shouldBeAbleToGetMeetingId() {
        Meeting aMeeting = new MeetingImpl();
        int actual = aMeeting.getId();
        int expected = 0;

        assertEquals(expected, actual);
    }
}
