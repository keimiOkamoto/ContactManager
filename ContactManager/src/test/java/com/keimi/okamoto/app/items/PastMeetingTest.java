/**
 * Test for pastMeeting
 * A meeting that was held in the past.
 * It includes your notes about what happened and what was agreed.
 */
package com.keimi.okamoto.app.items;

import com.keimi.okamoto.app.utils.IllegalMeetingException;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class PastMeetingTest {

    /**
     * Test for to get correct notes of a past meeting
     * even if the note is empty.
     *
     * @throws com.keimi.okamoto.app.utils.IllegalMeetingException if the set does not contain at least one contact.
     */
    @Test
    public void shouldBeAbleToGetNotesEvenIfEmpty() throws IllegalMeetingException {
        int id = 0;
        Calendar date = Calendar.getInstance();
        Set<Contact> aListOfContacts = new HashSet<>();
        aListOfContacts.add(new ContactImpl("Adam", 0));

        PastMeeting aPastMeeting = new PastMeetingImpl(id, date, aListOfContacts, null);
        String actual = aPastMeeting.getNotes();
        String expected = "";

        assertEquals(expected, actual);
    }
}
