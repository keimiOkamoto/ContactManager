package com.keimi.okamoto.meetingItem.organisers;

import com.keimi.okamoto.meetingItem.Contact;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by keimiokamoto on 01/03/2014.
 */
public class ContactsContainerTest {

    /**
     * Test that takes in a contact from ContactManager
     * and adds it to a HashMap.
     */
    @Test
    public void shouldBeAbleToAddContact() {
        String nameExpected = "Adam";
        String notesExpected = "Some notes goes here...";

        ContactsContainer aContactContainer = new ContactsContainerImpl();
        aContactContainer.addContact(nameExpected, notesExpected);

        Contact actual = aContactContainer.getContact();
        String actualName = actual.getName();

        assertEquals(actualName, nameExpected);
    }
}
