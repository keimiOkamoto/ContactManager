package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.Meeting;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by keimiokamoto on 03/03/2014.
 */
public class MeetingContainerImpl implements MeetingContainer {
    /**
     * A method to add a future meeting, taking in a Set of contacts
     * and the date.
     *
     * @param aSetOfContacts contacts that will attend the meeting
     * @param date of the meeting
     * @return the meeting id
     */
    @Override
    public int addFutureMeeting(Set<Contact> aSetOfContacts, Calendar date) {
    return 0;
    }

    @Override
    public boolean checkForValidDate(Calendar date) {
        return false;
    }
}
