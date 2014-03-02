/**
 * ContactsContainerTest class.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.ContactFactoryImpl;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactsContainerTest {
    private ContactsContainer aContactContainer;
    private UniqueNumberGenerator aUniqueNumberGenerator;
    private ContactFactory aContactFactory;
    private Contact aContact;

    @Before
    public void buildUp() {
        aContact = mock(Contact.class);
        aContactFactory = mock(ContactFactory.class);
        aUniqueNumberGenerator = mock(UniqueNumberGenerator.class);
        aContactContainer = new ContactsContainerImpl(aContactFactory, aUniqueNumberGenerator);
    }

    /**
     * Test that takes in a contact from ContactManager
     * and adds it to a HashMap.
     */
    @Test
    public void shouldBeAbleToAddContact() {
        String nameExpected = "Adam";
        String notesExpected = "Some notes goes here...";
        int id = 5;

        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(id);
        when(aContact.getName()).thenReturn(nameExpected);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(aContact);

        aContactContainer.addContact(nameExpected, notesExpected);

        Contact actual = aContactContainer.getContact(id);
        String actualName = actual.getName();

        assertEquals(actualName, nameExpected);
    }

    /**
     * Test to check that the valid Id exists
     */
    @Test
    public void shouldBeAbleToCheckForValidId() {
        assertFalse(aContactContainer.checkForValidId(0));

        assertFalse(aContactContainer.checkForValidId(1, 2));

        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(1);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(aContact);
        aContactContainer.addContact("Adam", "Some notes go here");

        assertTrue(aContactContainer.checkForValidId(1));

        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(2, 3, 4);
        aContactContainer.addContact("Barry", "Some notes about Barry...");
        aContactContainer.addContact("Carl", "Some notes about Carl...");
        aContactContainer.addContact("Derek", "Some notes about Derek...");

        assertFalse(aContactContainer.checkForValidId(1, 2, 26));
    }


}
