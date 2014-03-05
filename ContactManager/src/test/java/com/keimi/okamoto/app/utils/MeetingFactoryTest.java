package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.IllegalMeetingException;
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
}
