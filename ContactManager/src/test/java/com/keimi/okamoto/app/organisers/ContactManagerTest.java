/**
 * Test for ContactManager
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ContactManagerTest {
    private ContactsContainer aContactContainer;
    private String notes;
    private String name;
    private ContactManager aContactManager;

    /**
     * Just builds up a new ContactsContainerImpl
     */
    @Before
    public void buildUp() {
        aContactContainer = new ContactsContainerImpl();
        notes = "Some notes go here";
        name = "Adam";
        aContactManager = new ContactManagerImpl();
    }

    /**
     * Test to make sure a new contact can be added.
     */
    @Test
    public void shouldBeAbleToAddNewContact() {
        aContactContainer.addContact(name, notes);
        Contact aContact = aContactContainer.getContact(0);

        String actualName = aContact.getName();
        String expectedName = name;

        assertEquals(actualName, expectedName);
    }

    /**
     * Test to make sure an exception is thrown if the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfNameIsEmpty() throws NullPointerException {
        aContactContainer.addContact(null, notes);
    }

    /**
     * Test to make sure an exception is thrown if the name is null.
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfNotesIsNull() throws NullPointerException {
        aContactContainer.addContact(name, null);
    }

    /**
     * Test that should get a set of contacts
     */
    @Test
    public void shouldBeAbleToGetSetOfContactsDependingOnId() {
        String name0 = "Adam";
        String name1 = "Barry";
        String name2 = "Carl";
        String name3 = "Darren";
        String name4 = "Eric";

        aContactManager.addNewContact(name0, notes);
        aContactManager.addNewContact(name1, notes);
        aContactManager.addNewContact(name2, notes);
        aContactManager.addNewContact(name3, notes);
        aContactManager.addNewContact(name4, notes);

        Set<Contact> aSetOfContacts = aContactManager.getContacts(0,1,4,5);
        int actualSize = aSetOfContacts.size();
        int expectedSize = 4;

        assertEquals(expectedSize, actualSize);
    }
}
