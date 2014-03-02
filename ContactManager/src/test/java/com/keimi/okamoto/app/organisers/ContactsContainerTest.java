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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactsContainerTest {
    private ContactsContainer aContactContainer;
    private UniqueNumberGenerator aUniqueNumberGenerator;
    private ContactFactory aContactFactory;

    @Before
    public void buildUp() {
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

        Contact aContact = mock(Contact.class);
        when(aUniqueNumberGenerator.getUniqueNumber()).thenReturn(id);
        when(aContact.getName()).thenReturn(nameExpected);
        when(aContactFactory.createContact(anyInt(), anyString(), anyString())).thenReturn(aContact);

        aContactContainer.addContact(nameExpected, notesExpected);

        Contact actual = aContactContainer.getContact(id);
        String actualName = actual.getName();

        assertEquals(actualName, nameExpected);
    }
}
