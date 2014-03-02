/**
 * ContactsContainerTest class.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.ContactFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ContactsContainerTest {
    private ContactsContainer aContactContainer;

    @Before
    public void buildUp() {
        ContactFactory aContactFactory = new ContactFactoryImpl();
        aContactContainer = new ContactsContainerImpl(aContactFactory);
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
