package com.keimi.okamoto.meetingItem;

import org.junit.Test;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PastMeetingTest {
    private int id;
    private Calendar date;
    private Set<Contact> aListOfContacts;

    @Test
    public void shouldBeAbleToGetNotesEvenIfEmpty() throws IllegalMeetingException {
        aListOfContacts = new HashSet<>();
        aListOfContacts.add(new ContactImpl("Adam", 0));

        PastMeeting aPastMeeting = new PastMeetingImpl(id, date, aListOfContacts);
        String actual = aPastMeeting.getNotes();
        String expected = "";

        assertEquals(expected, actual);
    }
}
