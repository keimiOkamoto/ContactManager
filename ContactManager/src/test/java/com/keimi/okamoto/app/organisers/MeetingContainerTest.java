package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;
import com.keimi.okamoto.app.utils.MeetingFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.MockHandler;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
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
        assertFalse(aMeetingContainer.checkForFuture(date));

        date.add(Calendar.DATE, 2);
        assertTrue(aMeetingContainer.checkForFuture(date));
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

    @Test
    public void shouldReturnMeetingWithTheRequestedId() throws IllegalMeetingException {
        int id = 0;
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, 1);

        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(id);
        when(aMeetingFactory.createFutureMeeting(anyInt(), eq(date), anySet())).thenReturn(aFutureMeeting);

        aMeetingContainer.addFutureMeeting(new HashSet<Contact>(), date);
        Meeting actualMeeting = aMeetingContainer.getMeeting(id);

        assertEquals(aFutureMeeting, actualMeeting);
    }

    @Test
    public void shouldReturnNullIfIdDoesNotCorrespondWithMeeting() throws IllegalMeetingException {
        int id = 0;
        Meeting actualMeeting = aMeetingContainer.getMeeting(id);

        assertEquals(null, actualMeeting);
    }

    /**
     * Test for addPastMeeting() implementation
     * Starts here.
     */
    @Test
    public void shouldBeAbleToAddPastMeeting() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);
        String notes = "Some notes go here..";
        PastMeeting pastMeeting = mock(PastMeeting.class);

        when(aMeetingFactory.createPastMeeting(anySet(), eq(date), anyString())).thenReturn(pastMeeting);
        aMeetingContainer.addPastMeeting(aSetOfContacts, date, notes);
        verify(aMeetingFactory).createPastMeeting(anySet(), eq(date), anyString());
    }

    
    @Test
    public void shouldThrowIllegalArgumentExceptionIfDateEnteredIsNotInThePast() {

    }

    /**
     * Test for getPastMeeting()
     * UNFINISHED
     *
     * add meeting
     * getMeeting with the id
     * Compatare!
     *
     * addNewPastMeeting make first
     */
    @Test
    public void shouldBeAbleToGetPastMeetingWithTheRequestedId() {
    }
}
