/**
 * ContactsContainerTest class.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ContactsContainerTest {
    ContactsContainer aContactContainer;

    @Before
    public void buildUp() {
        aContactContainer = new ContactsContainerImpl();
    }

    /**
     * Test that takes in a contact from ContactManager
     * and adds it to a HashMap.
     */
    @Test
    public void shouldBeAbleToAddContact() {
        String nameExpected = "Adam";
        String notesExpected = "Some notes goes here...";

        aContactContainer.addContact(nameExpected, notesExpected);

        Contact actual = aContactContainer.getContact(0);
        String actualName = actual.getName();

        assertEquals(actualName, nameExpected);
    }
}
