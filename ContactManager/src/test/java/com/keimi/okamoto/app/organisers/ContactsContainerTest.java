/**
 * ContactsContainerTest class.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.ContactImpl;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.ContactFactoryImpl;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
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

        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(1, 2, 3, 4);
        aContactContainer.addContact("Adam", "Some notes go here");
        aContactContainer.addContact("Barry", "Some notes about Barry...");
        aContactContainer.addContact("Carl", "Some notes about Carl...");
        aContactContainer.addContact("Derek", "Some notes about Derek...");
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
        assertFalse(aContactContainer.checkForValidId(100));

        assertFalse(aContactContainer.checkForValidId(100, 200));

        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(100);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(aContact);

        assertTrue(aContactContainer.checkForValidId(1));

        assertFalse(aContactContainer.checkForValidId(1, 2, 260));
    }

    /**
     * checking if get contacts returns a Set of contacts
     * using relevant name
     */
    @Test
    public void shouldBeAbleToGetContactsByName() {
        String name = "Adam";
        int id = 0;
        String notes = "Some notes about Adam...";

        addContact(name, id, notes);

        Set<Contact> actualSet = aContactContainer.getContacts(name);
        String actual = actualSet.toArray(new Contact[0])[0].getName();

        assertEquals(actual, name);

        addContact("Benny", 1, notes);
        addContact(name, 2, notes);

        actualSet = aContactContainer.getContacts(name);
        Contact[] aContactArray = actualSet.toArray(new Contact[0]);
        String actual1 = aContactArray[0].getName();
        String actual2 = aContactArray[1].getName();

        assertEquals(actual1, name);
        assertEquals(actual2, name);

        assertTrue(actualSet.size() == 2);
    }

    /**
     * A method that helps add a contact to avoid
     * repeating code.
     *
     * @param name name of a contact
     * @param id id of a id
     * @param notes notes of a id
     */
    private void addContact(String name, int id, String notes) {
        Contact mContact = mock(Contact.class);
        when(mContact.getName()).thenReturn(name);
        when(mContact.getId()).thenReturn(id);
        when(mContact.getNotes()).thenReturn(notes);
        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(id);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(mContact);

        aContactContainer.addContact(name, notes);
    }

}
