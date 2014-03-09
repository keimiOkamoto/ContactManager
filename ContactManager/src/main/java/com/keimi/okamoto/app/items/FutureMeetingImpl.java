package com.keimi.okamoto.app.items;

import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
    /**
     * Constructor method
     * @param id an id for a future meeting
     * @param date a date for the meeting
     * @param aListOfContacts a list of contacts
     * @throws IllegalMeetingException if there is not more than one contact in the list
     */
    public FutureMeetingImpl(int id, Calendar date, Set<Contact> aListOfContacts) throws IllegalMeetingException {
        super(id, date, aListOfContacts);
    }
}
