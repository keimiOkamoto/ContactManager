/**
 * Test class for Contact
 * A contact is a person we are making business with or may do in the checkForFuture.
 * Contacts have an ID (unique), a name (probably unique, but maybe
 * not), and notes that the user may want to save about them.
 */
package com.keimi.okamoto.app.items;

import org.junit.*;

import static org.junit.Assert.*;


public class ContactTest {
    private Contact aContact;

    /**
     * Build a contact object.
     */
    @Before
    public void buildUp() {
        aContact = new ContactImpl("Adam", 0);
    }

    /**
     * Test for getId(), method should return an
     * ID. (Note: The responsibility of it being
     * a unique id will be passed to the object
     * that will be in charge of creating the
     * contact objects.)
     */
    @Test
    public void shouldBeAbleToGetId() {
        int actual = aContact.getId();
        int expected = 0;

        assertEquals(expected, actual);
    }

    /**
     * Test that should return the correct contact
     * name.
     */
    @Test
    public void shouldBeAbleToGetName() {
        String actual = aContact.getName();
        String expected = "Adam";

        assertEquals(expected, actual);
    }

    /**
     * Test that should return the correct notes about
     * a particular contact.
     */
    @Test
    public void shouldBeAbleToGetNotesIfContactHasNotes() {
        aContact.addNotes("Works for company A as a manager in department A");
        String actual = aContact.getNotes();
        String expected = "Works for company A as a manager in department A";

        assertEquals(actual, expected);
    }

    /**
     * Test that should return an empty string if no notes
     * have been entered.
     */
    @Test
    public void shouldBeAbleToGetEmptyStringIfContactHasNoNotes() {
        aContact.addNotes("");
        String actual = "";
        String expected = "";

        assertEquals(actual, expected);
    }
}
