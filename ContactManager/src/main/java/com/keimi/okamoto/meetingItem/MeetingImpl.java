/**
 * A class to represent meetings
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 */
package com.keimi.okamoto.meetingItem;

import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {
    private int id = 0;
    private Calendar date;
    private Set<Contact> aListOfContacts;

    public MeetingImpl(int id, Calendar date, Set<Contact> aListOfContacts) throws IllegalMeetingException {
        if (aListOfContacts.size() < 1) throw new IllegalMeetingException();
        this.id = id;
        this.date = date;
        this.aListOfContacts = aListOfContacts;
    }

    /**
     * Returns the id of the
     * @return the id of the
     */
    public int getId() {
        return id;
    }

    /**
     * Return the date of the meeting.
     * @return the date of the meeting.
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Return the details of people that attended the meeting.
     * The list contains a minimum of one contact (if there were
     * just two people: the user and the contact) and may contain an
     * arbitraty number of them.
     * @return the details of people that attended the meeting.
     */
    public Set<Contact> getContacts() {
        return aListOfContacts;
    }
}
