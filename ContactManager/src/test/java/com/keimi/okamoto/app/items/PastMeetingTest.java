/**
 * Test for pastMeeting
 * A meeting that was held in the past.
 * It includes your notes about what happened and what was agreed.
 */
package com.keimi.okamoto.app.items;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class PastMeetingTest {
    private int id;
    private Calendar date;
    private Set<Contact> aListOfContacts;

    /**
     * Test for to get correct notes of a past meeting
     * even if the note is empty.
     *
     * @throws com.keimi.okamoto.app.items.IllegalMeetingException if the set does not contain at least one contact.
     */
    @Test
    public void shouldBeAbleToGetNotesEvenIfEmpty() throws IllegalMeetingException {
        aListOfContacts = new HashSet<>();
        aListOfContacts.add(new ContactImpl("Adam", 0));

        PastMeeting aPastMeeting = new PastMeetingImpl(id, date, aListOfContacts);
        String actual = aPastMeeting.getNotes();
        String expected = "";

        assertEquals(expected, actual);
    }
}
