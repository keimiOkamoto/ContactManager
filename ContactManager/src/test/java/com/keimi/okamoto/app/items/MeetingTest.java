/**
 * Test class for Meeting.
 * Meetings have unique IDs, scheduled date and a list of participating contacts.
 */
package com.keimi.okamoto.app.items;

import org.junit.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class MeetingTest {
    private Meeting aMeeting;
    private Calendar date;
    private Set<Contact> aContactSet;

    /**
     * Builds a contact set utilising private method contacts().
     * Builds a Calender holding the current date.
     * Builds a MeetingImpl object to be utilised.
     *
     * @throws com.keimi.okamoto.app.items.IllegalMeetingException if the set does not contain at least one contact.
     */
    @Before
    public void buildUp() throws IllegalMeetingException {
        aContactSet = contacts();
        date = Calendar.getInstance();
        aMeeting = new MeetingImpl(0, date, aContactSet);
    }

    /**
     * Test for getId() method that returns the ID of
     * the meeting.
     */
    @Test
    public void shouldBeAbleToGetMeetingId() {
        int actual = aMeeting.getId();
        int expected = 0;

        assertEquals(expected, actual);
    }

    /**
     * Test for the getDate() method that returns the
     * current date.
     */
    @Test
    public void shouldBeAbleToGetDate() {
        Calendar actual = aMeeting.getDate();
        Calendar expected = date;

        assertSame(expected, actual);
    }

    /**
     * Tests for a set with at least one contact in the set.
     */
    @Test
    public void shouldBeAbleToGetContacts() {
        Set<Contact> actual = aMeeting.getContacts();
        Set<Contact> expected = aContactSet;

        assertSame(expected, actual);
    }

    /**
     * Test that drives the creation of IllegalMeetingExpression.
     * If the contact manager was to try to create a set with no
     * contacts an exception will be thrown with an message.
     *
     * @throws IllegalMeetingException if the set does not contain at least one contact.
     */
    @Test(expected = IllegalMeetingException.class)
    public void shouldOnlyBeAbleToCreateMeetingWithAtLeastOneContact() throws IllegalMeetingException {
        new MeetingImpl(0, date, new HashSet<Contact>());
    }

    /**
     * A method that helps the shouldBeAbleToGetContacts test.
     *
     * @return the set containing one contact.
     */
    private Set<Contact> contacts() {
        Contact user = new ContactImpl("user1", 0);
        Set<Contact> aSet = new HashSet<>();
        aSet.add(user);

        return aSet;
    }
}


