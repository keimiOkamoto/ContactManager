/**
 * Test for ContactManager
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
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

    /**
     * Just builds up a new ContactsContainerImpl
     */
    @Before
    public void buildUp() {
        aMeetingContainer = mock(MeetingContainer.class);
        aContactContainer = mock(ContactsContainer.class);
        aContact = mock(Contact.class);
        aContactManager = new ContactManagerImpl(aContactContainer, aMeetingContainer);
        notes = "Some notes go here";
        name = "Adam";
    }

    /**
     * Test to make sure a new contact can be added.
     */
    @Test
    public void shouldBeAbleToAddNewContact() {
        aContactManager.addNewContact(name, notes);
        verify(aContactContainer).addContact(name, notes);
    }

    /**
     * Test to make sure an exception is thrown if the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfNameIsEmpty() throws NullPointerException {
        aContactManager.addNewContact(null, notes);
    }

    /**
     * Test to make sure an exception is thrown if the name is null.
     */
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

        assertEquals(actual, expected);
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
     * Test that a future meeting can be added.
     */
    @Test
    public void shouldBeAbleToAddFutureMeeting() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        when(aMeetingContainer.checkForValidDate(date)).thenReturn(true);

        aContactManager.addFutureMeeting(aSetOfContacts, date);
        verify(aMeetingContainer).addFutureMeeting(aSetOfContacts, date);
    }

    /**
     * Test for Illegal Argument exception if a
     * meeting is set in the past.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDateIsInThePast() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);

        when(aMeetingContainer.checkForValidDate(date)).thenReturn(false);
        aContactManager.addFutureMeeting(aSetOfContacts, date);
    }

    /**
     * contact is unknown - zak
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfContactIsUnknownInSet() {
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
    public void shouldThrowIllegalArgumentExceptionIfContactDoesNotExistInSet() {
        Set<Contact> aSetOfContacts = new HashSet<>();
        Calendar date = Calendar.getInstance();

        when(aContactContainer.checkForValidSetOfContacts(aSetOfContacts)).thenReturn(false);
        aContactManager.addFutureMeeting(null, date);
    }
}
