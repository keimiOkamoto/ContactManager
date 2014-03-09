package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.PastMeeting;

import java.util.Calendar;
import java.util.Set;


public interface MeetingFactory {
    /**
     * A method that creates future meetings
     *
     * @param id             id of a meeting
     * @param date           date for the meeting to be held
     * @param aSetOfContacts a set of contacts attending the meeting
     * @return a FutureMeeting
     * @throws IllegalMeetingException if the there is less that one contact attending the meeting
     */
    FutureMeeting createFutureMeeting(int id, Calendar date, Set<Contact> aSetOfContacts) throws IllegalMeetingException;

    /**
     * @param id             id of a meeting
     * @param aSetOfContacts a set of contacts attending the meeting
     * @param date           date for the meeting to be held
     * @param notes          some notes about the meeting
     * @return a PastMeeting
     * @throws IllegalMeetingException if the there is less that one contact attending the meeting
     */
    PastMeeting createPastMeeting(int id, Set<Contact> aSetOfContacts, Calendar date, String notes) throws IllegalArgumentException, IllegalMeetingException;
}
