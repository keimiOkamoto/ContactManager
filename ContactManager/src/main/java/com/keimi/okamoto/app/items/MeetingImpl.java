/**
 * A class to represent meetings
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 */
package com.keimi.okamoto.app.items;

import com.keimi.okamoto.app.utils.IllegalMeetingException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting, Serializable {
    private int id = 0;
    private Calendar date;
    private Set<Contact> aListOfContacts;

    /**
     * Constructor for MeetingImpl
     *
     * @param id              an id for a meeting
     * @param date            a date for the meeting
     * @param aListOfContacts a list of contacts
     * @throws com.keimi.okamoto.app.utils.IllegalMeetingException if there is not more than one contact in the list
     */
    public MeetingImpl(int id, Calendar date, Set<Contact> aListOfContacts) throws IllegalMeetingException {
        if (aListOfContacts.size() < 1) throw new IllegalMeetingException();
        this.id = id;
        this.date = date;
        this.aListOfContacts = aListOfContacts;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Calendar getDate() {
        return date;
    }


    @Override
    public Set<Contact> getContacts() {
        return aListOfContacts;
    }
}
