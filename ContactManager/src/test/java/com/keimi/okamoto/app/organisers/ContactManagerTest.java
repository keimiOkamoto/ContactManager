/**
 * Test for ContactManager
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


public class ContactManagerTest {
    private String notes;
    private String name;
    private ContactsContainer aContactContainer;
    private ContactManager aContactManager;

    /**
     * Just builds up a new ContactsContainerImpl
     */
    @Before
    public void buildUp() {
        aContactContainer = mock(ContactsContainer.class);

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
        when(aContactContainer.getContact(anyInt())).thenReturn(mock(Contact.class), mock(Contact.class), mock(Contact.class), mock(Contact.class));
        Set<Contact> contactSet = aContactManager.getContacts(1, 2, 3, 4);
        verify(aContactContainer, times(4)).getContact(anyInt());

        assertEquals(contactSet.size(), 4);
    }

    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfParameterIsNull() {
        Integer num = null;
        aContactManager.getContacts(num);
    }
}
