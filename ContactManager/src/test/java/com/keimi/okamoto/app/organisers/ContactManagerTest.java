/**
 * Test for ContactManager
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;

import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.ContactFactoryImpl;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ContactManagerTest {
    private String notes;
    private String name;
    private ContactsContainer aContactContainer;
    private ContactManager aContactManager;
    private UniqueNumberGenerator aUniqueNumberGenerator;

    /**
     * Just builds up a new ContactsContainerImpl
     */
    @Before
    public void buildUp() {
        ContactFactory aContactFactory = new ContactFactoryImpl();
        aUniqueNumberGenerator = UniqueNumberGeneratorImpl.getInstance();

        aContactContainer = new ContactsContainerImpl(aContactFactory,aUniqueNumberGenerator);
        aContactManager = new ContactManagerImpl(aContactContainer);
        notes = "Some notes go here";
        name = "Adam";
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
     * Test that should get a set of contacts.
     * First it will test fot the correct number contacts using size()
     * after the contact manager has added contacts.
     * Lastly it will test that the correct contacts are in the HashSet
     * for a more accurate test.
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

        Set<Contact> aSetOfContacts = aContactManager.getContacts(0, 1, 4);
        int actualSize = aSetOfContacts.size();
        int expectedSize = 3;

        assertEquals(expectedSize, actualSize);

        Set<String> contactNameActual = new HashSet<>();
        for (Contact x : aSetOfContacts) {
            contactNameActual.add(x.getName());
        }

        Set<String> contactsNameExpected = new HashSet<>();
        contactsNameExpected.add(name0);
        contactsNameExpected.add(name1);
        contactsNameExpected.add(name4);

        assertTrue(contactsNameExpected.equals(contactNameActual));
    }
}
