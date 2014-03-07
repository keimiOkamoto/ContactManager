/**
 * Test for ContactManager
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


public class ContactManagerTest {
    private String notes;
    private String name;
    private ContactsContainer aContactContainer;
    private ContactManager aContactManager;
    private Contact aContact;
    private MeetingContainer aMeetingContainer;
    private FutureMeeting aFutureMeeting;
    private Meeting aMeeting;
    PastMeeting aPastMeeting;


    /**
     * Just builds up a new ContactsContainerImpl
     */
    @Before
    public void buildUp() {
        aMeetingContainer = mock(MeetingContainer.class);
        aContactContainer = mock(ContactsContainer.class);
        aFutureMeeting = mock(FutureMeeting.class);
        aContact = mock(Contact.class);
        aMeeting = mock(Meeting.class);
        aPastMeeting= mock(PastMeeting.class);

        aContactManager = new ContactManagerImpl(aContactContainer, aMeetingContainer);
        notes = "Some notes go here";
        name = "Adam";
    }

    /**
     * Test to make sure addNewContact() is implemented
     * as it should be.
     */
    @Test
    public void shouldBeAbleToAddNewContact() {
        aContactManager.addNewContact(name, notes);
        verify(aContactContainer).addContact(name, notes);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfNameIsEmpty() throws NullPointerException {
        aContactManager.addNewContact(null, notes);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfNotesIsNull() throws NullPointerException {
        aContactManager.addNewContact(name, null);
    }

    /**
     * Using the mocking framework test is made simpler
     * first verify, verifies the number of times the
     * getContacts() is being called 4 times ensuring that
     * 4 elements have been added.
     */
    @Test
    public void shouldBeAbleToGetSetOfContactsDependingOnId() {
        when(aContactContainer.checkForValidId(Matchers.<int[]>anyVararg())).thenReturn(true);
        when(aContactContainer.getContact(anyInt())).thenReturn(mock(Contact.class), mock(Contact.class), mock(Contact.class), mock(Contact.class));

        Set<Contact> contactSet = aContactManager.getContacts(1, 2, 3, 4);
        verify(aContactContainer, times(4)).getContact(anyInt());

        assertEquals(contactSet.size(), 4);
    }

    /**
     * Tests that an IllegalArgumentException is thrown if
     * non-existent id is asked for.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfIdIsNotValid() {
        Integer num = 26;
        when(aContactContainer.checkForValidId(anyInt())).thenReturn(false);
        aContactManager.getContacts(num);
    }

    /**
     * Test that should return a set of contacts by name.
     */
    @Test
    public void shouldBeAbleToGetSetOfContactsByName() {
        when(aContactContainer.checkForValidName(anyString())).thenReturn(true);

        Set<Contact> expected = new HashSet<>();
        when(aContactManager.getContacts(anyString())).thenReturn(expected);
        Set<Contact> actual = aContactManager.getContacts(name);
        verify(aContactContainer).getContacts(name);

        assertEquals(expected, actual);
    }

    /**
     * Test that checks that a NullPointerException is being
     * thrown if the parameter is null.
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfParameterIsNull() {
        String name = null;
        when(aContactContainer.checkForValidName(anyString())).thenReturn(false);
        aContactManager.getContacts(name);
    }

    /**
     * Test that a checkForFuture meeting can be added.
     */
    @Test
    public void shouldBeAbleToAddFutureMeeting() throws IllegalMeetingException {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        when(aMeetingContainer.checkForFuture(date)).thenReturn(true);
        when(aContactContainer.checkForValidSetOfContacts(anySet())).thenReturn(true);

        aContactManager.addFutureMeeting(aSetOfContacts, date);
        verify(aMeetingContainer).addFutureMeeting(aSetOfContacts, date);
    }

    /**
     * Test for Illegal Argument exception if a
     * meeting is set in the past.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDateIsInThePast() throws IllegalMeetingException {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);

        when(aMeetingContainer.checkForFuture(date)).thenReturn(false);
        aContactManager.addFutureMeeting(aSetOfContacts, date);
    }

    /**
     * contact is unknown - zak
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfContactIsUnknownInSet() throws IllegalMeetingException {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        when(aContactContainer.checkForValidSetOfContacts(aSetOfContacts)).thenReturn(false);
        aContactManager.addFutureMeeting(aSetOfContacts, date);
    }

    /**
     * Test for Illegal Argument exception if a
     * non-existent contact Set is added. (with null value)
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfContactDoesNotExistInSet() throws IllegalMeetingException {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        when(aContactContainer.checkForValidSetOfContacts(aSetOfContacts)).thenReturn(false);
        aContactManager.addFutureMeeting(null, date);
    }

    @Test
    public void shouldReturnTheFutureMeetingWithTheRequestedId() {
        int id = 1;
        Calendar date = Calendar.getInstance();

        when(aMeetingContainer.getFutureMeeting(anyInt())).thenReturn(aFutureMeeting);
        when(aMeetingContainer.checkForPast(date)).thenReturn(false);

        FutureMeeting actualFutureMeeting = aContactManager.getFutureMeeting(id);

        assertEquals(aFutureMeeting, actualFutureMeeting);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfThereIsAMeetingWithThatIdHappeningInThePast() {
        Calendar date = Calendar.getInstance();

        when(aFutureMeeting.getDate()).thenReturn(date);
        when(aMeetingContainer.getFutureMeeting(anyInt())).thenReturn(aFutureMeeting);
        when(aMeetingContainer.checkForPast(date)).thenReturn(true);
        aContactManager.getFutureMeeting(1);
    }

    @Test
    public void shouldReturnNullIfIdDoesNotCorrespondWithFutureMeeting() {
        int id = 1;

        when(aMeetingContainer.getFutureMeeting(anyInt())).thenReturn(null);
        FutureMeeting actualFutureMeeting = aContactManager.getFutureMeeting(id);

        assertEquals(null, actualFutureMeeting);

    }

    @Test
    public void shouldReturnMeetingWithTheRequestedId() {
        int id = 1;
        when(aMeetingContainer.getMeeting(anyInt())).thenReturn(aMeeting);
        Meeting actualMeeting = aContactManager.getMeeting(id);
        verify(aMeetingContainer).getMeeting(id);

        assertEquals(aMeeting, actualMeeting);
    }

    @Test
    public void shouldReturnNullIfIdDoesNotCorrespondWithMeeting() {
        int id = 1;
        when(aMeetingContainer.getMeeting(anyInt())).thenReturn(null);
        Meeting actualMeeting = aContactManager.getMeeting(id);
        verify(aMeetingContainer).getMeeting(id);

        assertEquals(null, actualMeeting);
    }

    /**
     * Tests For addNewPastMeeting()
     * Note to self: refactor.
     */
    @Test
    public void shouldBeAbleToAddNewPastMeeting() throws IllegalMeetingException {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);

        aSetOfContacts.add(aContact);
        when(aMeetingContainer.checkForPast(date)).thenReturn(true);
        when(aContactContainer.checkForValidSetOfContacts(aSetOfContacts)).thenReturn(true);

        aContactManager.addNewPastMeeting(aSetOfContacts, date, notes);
        verify(aMeetingContainer).addPastMeeting(anySet(), eq(date), anyString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfListOfContactsIsEmpty() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        aContactManager.addNewPastMeeting(aSetOfContacts, date , notes);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfContactDoesNotExist() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        when(aContactContainer.checkForValidSetOfContacts(anySet())).thenReturn(false);
        aContactManager.addNewPastMeeting(aSetOfContacts, date, notes);
    }

    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfSetOfContactsArgumentIsNull () {
        Calendar date = Calendar.getInstance();

        when(aContactContainer.checkForValidName(anyString())).thenReturn(false);
        aContactManager.addNewPastMeeting(null, date, notes);
    }

    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfDateArgumentIsNull () {
        Set<Contact> aSetOfContacts = new HashSet<>();

        when(aContactContainer.checkForValidName(anyString())).thenReturn(false);
        aContactManager.addNewPastMeeting(aSetOfContacts, null, notes);
    }

    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfNotesArgumentIsNull () {
        Calendar date = Calendar.getInstance();
        Set<Contact> aSetOfContacts = new HashSet<>();

        when(aContactContainer.checkForValidName(anyString())).thenReturn(false);
        aContactManager.addNewPastMeeting(aSetOfContacts, date, null);
    }

    /**
     * Test for getPastMeeting() starts here
     */
    @Test
    public void shouldReturnPastMeetingWithTheRequestedId() {
        int id = 1;
        when(aMeetingContainer.getPastMeeting(anyInt())).thenReturn(aPastMeeting);

        PastMeeting actualPastMeeting = aContactManager.getPastMeeting(id);
        verify(aMeetingContainer).getPastMeeting(id);

        assertEquals(aPastMeeting, actualPastMeeting);
    }

    @Test
    public void shouldReturnNullIfIdDoesNotCorrespondAPastMeeting() {
        int id = 1;
        when(aMeetingContainer.getPastMeeting(anyInt())).thenReturn(null);

        PastMeeting actualPastMeeting = aContactManager.getPastMeeting(id);
        verify(aMeetingContainer).getPastMeeting(id);

        assertEquals(null, actualPastMeeting);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfThereIsAMeetingWithThatIdHappeningInTheFuture() {
        int id = 0;
        Calendar date = Calendar.getInstance();

        when(aPastMeeting.getDate()).thenReturn(date);
        when(aMeetingContainer.getPastMeeting(anyInt())).thenReturn(aPastMeeting);
        when(aMeetingContainer.checkForFuture(eq(date))).thenReturn(true);
        aContactManager.getPastMeeting(id);
    }

    /**
     * Test for addMeetingNotes() starts here
     */
    @Test
    public void shouldBeAbleToAddMeetingNotes() {
        Calendar date = Calendar.getInstance();
        String notes = "Some notes...";

        when(aMeetingContainer.getMeeting(anyInt())).thenReturn(aFutureMeeting);
        when(aMeetingContainer.checkForFuture(eq(date))).thenReturn(false);
        when(aFutureMeeting.getDate()).thenReturn(date);

        aContactManager.addMeetingNotes(0, notes);
        verify(aMeetingContainer).convertToPastMeeting(aFutureMeeting, notes);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfTheMeetingDoesNotExist() {
        String notes = "Some notes...";

        when(aMeetingContainer.getMeeting(anyInt())).thenReturn(null);
        aContactManager.addMeetingNotes(0, notes);
    }

    @Test (expected = IllegalStateException.class)
    public void  IllegalStateExceptionIfTheMeetingIsSetForADateInTheFuture() {
        Calendar date = Calendar.getInstance();
        String notes = "Some notes...";

        when(aMeetingContainer.getMeeting(anyInt())).thenReturn(aFutureMeeting);
        when(aMeetingContainer.checkForFuture(eq(date))).thenReturn(true);
        when(aFutureMeeting.getDate()).thenReturn(date);

        aContactManager.addMeetingNotes(0, notes);
    }

    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfTheNotesAreNull() {
        aContactManager.addMeetingNotes(0, null);
    }
}
