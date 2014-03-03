package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class MeetingContainerTest {
    MeetingContainer aMeetingContainer;

    @Test
    public void shouldBeAbleToAddFutureMeeting() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        int actual = aMeetingContainer.addFutureMeeting(aSetOfContacts, date);
        int expected = 0;

        assertEquals(actual, expected);
    }
}
