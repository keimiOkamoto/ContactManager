package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.IllegalMeetingException;
import com.keimi.okamoto.app.items.PastMeeting;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class MeetingFactoryTest {

    @Test
    public void shouldBeAbleToCreateFutureMeeting() throws IllegalMeetingException {
        int id = 0;
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        Contact aContact = mock(Contact.class);
        aSetOfContacts.add(aContact);

        MeetingFactory aMeetingFactory = new MeetingFactoryImpl();
        FutureMeeting actual = aMeetingFactory.createFutureMeeting(id, date, aSetOfContacts);

        assertEquals(id, actual.getId());
        assertEquals(aSetOfContacts, actual.getContacts());
        assertEquals(date, actual.getDate());
    }

    @Test
    public void shouldBeAbleToCreatePastMeeting() throws IllegalMeetingException {
        String notes = "some notes...";
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        int id = 0;

        Contact aContact = mock(Contact.class);
        aSetOfContacts.add(aContact);

        MeetingFactory aMeetingFactory = new MeetingFactoryImpl();
        PastMeeting actual = aMeetingFactory.createPastMeeting(id, aSetOfContacts, date, notes);

        assertEquals(notes, actual.getNotes());
        assertEquals(aSetOfContacts, actual.getContacts());
        assertEquals(date, actual.getDate());
    }
}
