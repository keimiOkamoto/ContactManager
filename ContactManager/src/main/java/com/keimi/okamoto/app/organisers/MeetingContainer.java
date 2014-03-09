package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;

import java.util.Calendar;
import java.util.List;
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
    int addFutureMeeting(Set<Contact> aSetOfContacts, Calendar date);

    boolean checkForFuture(Calendar date);

    boolean checkForPast(Calendar date);

    FutureMeeting getFutureMeeting(int id);

    Meeting getMeeting(int id);


    /**
     *
     * @param aSetOfContacts
     * @param date
     * @param notes
     */
    void addPastMeeting(Set<Contact> aSetOfContacts, Calendar date, String notes);

    PastMeeting getPastMeeting(int id);

    void convertToPastMeeting(Meeting aMeeting, String notes);

    Set<Integer> getMeetingIdListBy(Contact contact);

    Set<Integer> getMeetingIdListBy(Calendar date);
}
