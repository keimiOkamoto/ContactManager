package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.IllegalMeetingException;
import com.keimi.okamoto.app.items.PastMeeting;

import java.util.Calendar;
import java.util.Set;


public interface MeetingFactory {
    /**
     * A method that creates meetings
     * @param id
     * @param date
     * @param aSetOfContacts
     * @return
     */
    FutureMeeting createFutureMeeting(int id, Calendar date, Set<Contact> aSetOfContacts) throws IllegalMeetingException;

    PastMeeting createPastMeeting(Set<Contact> aSetOfContacts, Calendar date, String notes);
}
