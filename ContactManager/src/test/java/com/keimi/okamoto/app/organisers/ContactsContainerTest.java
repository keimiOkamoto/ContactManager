/**
 * ContactsContainerTest class.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.ContactImpl;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorUtilities;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * Test for a ContactsContainer
 */
public class ContactsContainerTest {
    private ContactsContainer aContactContainer;
    private UniqueNumberGeneratorUtilities aUniqueNumberGeneratorUtilities;
    private ContactFactory aContactFactory;
    private Contact aContact;

    @Before
    public void buildUp() {
        aContact = mock(Contact.class);
        aContactFactory = mock(ContactFactory.class);
        aUniqueNumberGeneratorUtilities = mock(UniqueNumberGeneratorUtilities.class);

        aContactContainer = new ContactsContainerImpl(aContactFactory, aUniqueNumberGeneratorUtilities);

        when(aUniqueNumberGeneratorUtilities.getUniqueNumber()).thenReturn(1, 2, 3, 4);
        aContactContainer.addContact("Adam", "Some notes go here");
        aContactContainer.addContact("Barry", "Some notes about Barry...");
        aContactContainer.addContact("Carl", "Some notes about Carl...");
        aContactContainer.addContact("Derek", "Some notes about Derek...");
    }

    /*
     * Test for addContact(String name, String notes)
     * Starts here:
     */
    @Test
    public void shouldBeAbleToAddContact() {
        String nameExpected = "Adam";
        String notesExpected = "Some notes goes here...";
        int id = 5;

        when(aUniqueNumberGeneratorUtilities.getUniqueNumber()).thenReturn(id);
        when(aContact.getName()).thenReturn(nameExpected);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(aContact);

        aContactContainer.addContact(nameExpected, notesExpected);

        Contact actual = aContactContainer.getContact(id);
        String actualName = actual.getName();

        assertEquals(nameExpected, actualName);
    }

    @Test
    public void shouldBeAbleToCheckForValidId() {
        assertFalse(aContactContainer.checkForValidId(100));

        assertFalse(aContactContainer.checkForValidId(100, 200));

        when(aUniqueNumberGeneratorUtilities.getUniqueNumber()).thenReturn(100);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(aContact);

        assertTrue(aContactContainer.checkForValidId(1));

        assertFalse(aContactContainer.checkForValidId(1, 2, 260));
    }

    /*
     * Test for getContact(int contactId)
     * Starts here:
     */
    @Test
    public void shouldBeAbleToGetContactsByName() {
        String name = "Adam";
        int id = 0;
        String notes = "Some notes about Adam...";

        addContact(name, id, notes);

        Set<Contact> actualSet = aContactContainer.getContacts(name);
        String actual = actualSet.toArray(new Contact[0])[0].getName();

        assertEquals(name, actual);

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

    /*
     * Test for checkForValidName()
     * is working.
     */
    @Test
    public void shouldBeAbleToCheckForValidName() {
        addContact("Adam", 1 , "Some notes about Adam...");
        assertTrue(aContactContainer.checkForValidName("Adam"));

        assertFalse(aContactContainer.checkForValidName("Terry"));
    }

    /*
     * Test for checkForValidSetOfContacts(Set<Contact> aSetOfContacts)
     * Starts here:
     */
    @Test
    public void shouldBeAbleToCheckForValidSetOfContacts() {
        assertFalse(aContactContainer.checkForValidSetOfContacts(null));

        String name1 = "Adam";
        String name2 = "Benny";
        int id1 = 1;
        int id2 =2;

        Set<Contact> contactSet = new HashSet<>();
        contactSet.add(new ContactImpl(name1, id1));
        contactSet.add(new ContactImpl(name2, id2));

        addContact(name1, id1, "Some notes about Adam...");
        addContact(name2, id2 , "Some notes about Benny...");

        assertTrue(aContactContainer.checkForValidSetOfContacts(contactSet));
    }

    /*
     * Method that helps the test
     * Adds a contact
     */
    private void addContact(String name, int id, String notes) {
        Contact mContact = mock(Contact.class);
        when(mContact.getName()).thenReturn(name);
        when(mContact.getId()).thenReturn(id);
        when(mContact.getNotes()).thenReturn(notes);
        when(aUniqueNumberGeneratorUtilities.getUniqueNumber()).thenReturn(id);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(mContact);

        aContactContainer.addContact(name, notes);
    }
}
