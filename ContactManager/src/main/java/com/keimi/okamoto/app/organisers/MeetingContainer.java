package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by keimiokamoto on 03/03/2014.
 */
public interface MeetingContainer {

    /**
     * A method to add a future meeting, taking in a Set of contacts
     * and the date.
     *
     * @param aSetOfContacts contacts that will attend the meeting
     * @param date of the meeting
     * @return the meeting id
     */
    int addFutureMeeting(Set<Contact> aSetOfContacts, Calendar date);

    boolean checkForValidDate(Calendar date);
}
