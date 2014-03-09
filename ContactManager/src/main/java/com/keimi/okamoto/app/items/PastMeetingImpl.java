package com.keimi.okamoto.app.items;

import com.keimi.okamoto.app.utils.IllegalMeetingException;

import java.util.Calendar;
import java.util.Set;


public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    private String note = "";

    /**
     * Constructor for PastMeetingImpl
     *
     * @param id              an id for a PastMeeting
     * @param date            a date for the PastMeeting
     * @param aListOfContacts a list of contacts
     * @throws com.keimi.okamoto.app.utils.IllegalMeetingException if there is not more than one contact in the list
     */
    public PastMeetingImpl(int id, Calendar date, Set<Contact> aListOfContacts, String notes) throws IllegalMeetingException {
        super(id, date, aListOfContacts);
        if (notes == null) {
            this.note = "";
        } else {
            this.note = notes;
        }
    }

    @Override
    public String getNotes() {
        return note;
    }

}
