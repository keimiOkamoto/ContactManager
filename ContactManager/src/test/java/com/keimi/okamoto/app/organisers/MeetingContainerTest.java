package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.IllegalMeetingException;
import com.keimi.okamoto.app.utils.MeetingFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anySet;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MeetingContainerTest {
    private MeetingContainer aMeetingContainer;
    private FutureMeeting aFutureMeeting;
    private MeetingFactory aMeetingFactory;
    private UniqueNumberGenerator aUniqueNumberGenerator;

    @Before
    public void buildUp() {
        aMeetingFactory = mock(MeetingFactory.class);
        aFutureMeeting = mock(FutureMeeting.class);
        aUniqueNumberGenerator = mock(UniqueNumberGenerator.class);
        aMeetingContainer = new MeetingContainerImpl(aMeetingFactory, aUniqueNumberGenerator);
    }

    @Test
    public void shouldBeAbleToAddFutureMeetingAndReturnId() throws IllegalMeetingException {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        int actualId = 5;
        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(actualId);
        when(aMeetingFactory.createFutureMeeting(anyInt(), eq(date), anySet())).thenReturn(aFutureMeeting);

        aMeetingContainer.addFutureMeeting(aSetOfContacts, date);

        assertEquals(actualId, 5 );
    }

    @Test
    public void shouldBeAbleToCheckForValidDate() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);

        assertFalse(aMeetingContainer.future(date));

        date.add(Calendar.DATE, 2);

        assertTrue(aMeetingContainer.future(date));
    }

    @Test
    public void shouldBeAbleToGetFutureMeetingById() throws IllegalMeetingException {
        int id = 0;
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, 2);

        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(id);
        when(aMeetingFactory.createFutureMeeting(anyInt(), eq(date), anySet())).thenReturn(aFutureMeeting);

        aMeetingContainer.addFutureMeeting(new HashSet<Contact>(), date);
        FutureMeeting actualFutureMeeting = aMeetingContainer.getFutureMeeting(id);

        assertEquals(aFutureMeeting, actualFutureMeeting);
    }

    @Test
    public void shouldReturnNullIfIdDoesNotCorrespondWithFutureMeeting() {
        int id = 1;
        FutureMeeting actualFutureMeeting = aMeetingContainer.getFutureMeeting(id);

        assertEquals(null, actualFutureMeeting);
    }
}
