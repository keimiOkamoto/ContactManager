package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MeetingContainerTest {
    private MeetingContainer aMeetingContainer;


    @Test
    public void shouldBeAbleToAddFutureMeetingAndReturnId() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        aMeetingContainer = new MeetingContainerImpl();

        int actual = aMeetingContainer.addFutureMeeting(aSetOfContacts, date);
        int expected = 1;

        assertEquals(expected, actual);
    }

}
