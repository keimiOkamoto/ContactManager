/**
 * Test for ContactFactory.
 */
package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ContactFactoryTest {
    /**
     * Test that the correct contact is being made.
     */
    @Test
    public void shouldBeAbleToCreateAFutureMeeting() {
        int id = 0;
        String name = "Adam";
        String note = "Some noes go here...";

        ContactFactory aContactFactory = new ContactFactoryImpl();
        Contact actual = aContactFactory.createContact(id, name, note);

        int expectedId = id;
        String expectedName = name;
        String expectedNote = note;

        assertEquals(actual.getId(), expectedId);
        assertEquals(actual.getName(), expectedName);
        assertEquals(actual.getNotes(), expectedNote);
    }
}
