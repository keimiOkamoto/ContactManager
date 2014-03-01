/**
 * A meeting that was held in the past.
 * It includes your notes about what happened and what was agreed.
 */
package com.keimi.okamoto.app.items;

import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    private String note = "";

    /**
     *
     * @param id
     * @param date
     * @param aListOfContacts
     * @throws IllegalMeetingException
     */
    public PastMeetingImpl(int id, Calendar date, Set<Contact> aListOfContacts) throws IllegalMeetingException {
        super(id, date, aListOfContacts);
    }

    /**
     * Returns the notes from the meeting. *
     * If there are no notes, the empty string is returned. *
     *
     * @return the notes from the meeting.
     */
    @Override
    public String getNotes() {
        return note;
    }

}
