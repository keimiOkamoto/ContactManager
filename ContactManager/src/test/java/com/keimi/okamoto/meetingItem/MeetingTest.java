package com.keimi.okamoto.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Set;


public class MeetingTest {
    private Meeting aMeeting;
    private Calendar aCalendar;

    @Before
    public void buildUp() {
        aMeeting = new MeetingImpl(0, aCalendar);
    }

    @Test
    public void shouldBeAbleToGetMeetingId() {
        int actual = aMeeting.getId();
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToGetDate() {
        Calendar actual = aMeeting.getDate();
        Calendar expected = aCalendar;

        assertSame(expected, actual);
    }


}
