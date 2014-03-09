package com.keimi.okamoto.app.utils;


import com.keimi.okamoto.app.items.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;


public class MeetingFactoryImpl implements MeetingFactory, Serializable {

    @Override
    public FutureMeeting createFutureMeeting(int id, Calendar date, Set<Contact> aListOfContacts) throws IllegalMeetingException {
        return new FutureMeetingImpl(id, date, aListOfContacts);
    }

    @Override
    public PastMeeting createPastMeeting(int id, Set<Contact> aSetOfContacts, Calendar date, String notes) throws IllegalArgumentException, IllegalMeetingException {
        return new PastMeetingImpl(id, date, aSetOfContacts, notes);
    }
}
