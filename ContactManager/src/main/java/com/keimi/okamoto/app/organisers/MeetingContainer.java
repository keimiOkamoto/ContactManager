package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by keimiokamoto on 03/03/2014.
 */
public interface MeetingContainer {

    /**
     * A method to add a checkForFuture meeting, taking in a Set of contacts
     * and the date.
     *
     * @param aSetOfContacts contacts that will attend the meeting
     * @param date of the meeting
     * @return the meeting id
     */
    int addFutureMeeting(Set<Contact> aSetOfContacts, Calendar date) throws IllegalMeetingException;

    boolean checkForFuture(Calendar date);

    boolean checkForPast(Calendar date);

    FutureMeeting getFutureMeeting(int id);

    Meeting getMeeting(int id);

    PastMeeting getPastMeeting(int id);

    /**
     * adds a past meeting to map
     *
     * @param aSetOfContacts
     * @param date
     * @param notes
     */
    void addPastMeeting(Set<Contact> aSetOfContacts, Calendar date, String notes);
}
