package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * A MeetingContainer.
 * MeetingsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
public interface MeetingContainer {

    /**
     * A method to add a future meeting, taking in a Set of contacts
     * and the date.
     *
     * @param aSetOfContacts contacts that will attend the meeting
     * @param date date of a meeting
     * @return the meeting id
     */
    int addFutureMeeting(Set<Contact> aSetOfContacts, Calendar date);

    /**
     * A method that checks if the date is in the future.
     *
     * @param date a date
     * @return true if it is set in the future
     */
    boolean checkForFuture(Calendar date);

    /**
     * A method that checks if the date is in the past.
     *
     * @param date a date
     * @return true if it is set in the past
     */
    boolean checkForPast(Calendar date);

    /**
     * A method that retrieves a future meeting by meeting id
     *
     * @param id an id of a meeting
     * @return a FutureMeeting
     */
    FutureMeeting getFutureMeeting(int id);

    /**
     * A method that retrieves a meeting by a meeting id
     *
     * @param id an id of a meeting
     * @return a Meeting
     */
    Meeting getMeeting(int id);

    /**
     * Adds a past meeting to map.
     *
     * @param aSetOfContacts a set of contacts who attended the meeting
     * @param date the date the meeting was held
     * @param notes some notes about the meeting
     */
    void addPastMeeting(Set<Contact> aSetOfContacts, Calendar date, String notes);

    /**
     * Retrieves the past meeting by id.
     *
     * @param id an id of a meeting
     * @return a PastMeeting
     */
    PastMeeting getPastMeeting(int id);

    /**
     * A method that converts a meeting to a meeting that
     * happened in the past.
     *
     * @param aMeeting A meeting to be converted to a PastMeeting
     * @param notes some notes about the meeting
     */
    void convertToPastMeeting(Meeting aMeeting, String notes);

    /**
     * Gets a set of meeting id's with the specified
     * contact.
     *
     * @param contact a contact
     * @return a set of meeting ids
     */
    Set<Integer> getMeetingIdListBy(Contact contact);

    /**
     * Gets a set of meeting id's with the specified
     * date.
     *
     * @param date a date the meeting was held
     * @return a set of meeting ids
     */
    Set<Integer> getMeetingIdListBy(Calendar date);
}
