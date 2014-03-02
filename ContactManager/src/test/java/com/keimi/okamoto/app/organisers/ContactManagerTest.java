/**
 * Test for ContactManager
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


public class ContactManagerTest {
    private String notes;
    private String name;
    private ContactsContainer aContactContainer;
    private ContactManager aContactManager;
    private Contact aContact;

    /**
     * Just builds up a new ContactsContainerImpl
     */
    @Before
    public void buildUp() {
        aContactContainer = mock(ContactsContainer.class);
        aContact = mock(Contact.class);
        aContactManager = new ContactManagerImpl(aContactContainer);
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
    @Test (expected = IllegalArgumentException.class)
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
    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfParameterIsNull() {
        String name = null;
        when(aContactContainer.checkForValidName(anyString())).thenReturn(false);
        aContactManager.getContacts(name);
    }
}
