package com.keimi.okamoto.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class MeetingTest {
    private Meeting aMeeting;
    private Calendar date;
    private Set<Contact> aContactSet;

    @Before
    public void buildUp() throws IllegalMeetingException {
        aContactSet = contacts();
        date = Calendar.getInstance();
        aMeeting = new MeetingImpl(0, date, aContactSet);
    }

    @Test
    public void shouldBeAbleToGetMeetingId() {
        int actual = aMeeting.getId();
        int expected = 0;

        assertEquals(expected, actual);
    }

    /**
     *
     */
    @Test
    public void shouldBeAbleToGetDate() {
        Calendar actual = aMeeting.getDate();
        Calendar expected = date;

        assertSame(expected, actual);
    }

    /**
     * Tests for a set with one contact in the set.
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
     * @throws IllegalMeetingException
     */
    @Test(expected = IllegalMeetingException.class)
    public void shouldOnlyBeAbleToCreateMeetingWithAtLeastOneContact() throws IllegalMeetingException {
        new MeetingImpl(0, date, new HashSet<Contact>());
    }

    /**
     * A method that helps the shouldBeAbleToGetContacts test.
     * @return the set containing one contact.
     */
    private Set<Contact> contacts() {
        Contact user = new ContactImpl("user1",0);
        Set<Contact> aSet = new HashSet<>();
        aSet.add(user);
        return aSet;
    }
}


