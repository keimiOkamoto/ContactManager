/**
 * Test for ContactFactory.
 */
package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ContactFactoryTest {
    /**
     * Test that the correct contact is being made.
     * <p/>
     * spy() is a Mockito method that allows you to
     * create spies of real objects.
     * When you use the spy then the real methods are
     * called (unless a method was stubbed)
     */
    @Test
    public void shouldBeAbleToCreateAFutureMeeting() {
        int id = 1;
        String name = "Adam";
        String note = "Some noes go here...";
        ContactFactory aContactFactory = new ContactFactoryImpl();
        Contact aContact = mock(Contact.class);

        ContactFactory spy = spy(aContactFactory);
        doReturn(aContact).when(spy).createContact(anyInt(), anyString(), anyString());
        Contact actual = spy.createContact(id, name, note);

        when(aContact.getId()).thenReturn(id);
        when(aContact.getName()).thenReturn(name);
        when(aContact.getNotes()).thenReturn(note);

        assertEquals(actual.getId(), id);
        assertEquals(actual.getName(), name);
        assertEquals(actual.getNotes(), note);
        assertEquals(aContact, actual);
    }
}
