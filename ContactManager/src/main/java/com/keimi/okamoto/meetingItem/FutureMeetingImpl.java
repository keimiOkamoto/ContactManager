package com.keimi.okamoto.meetingItem;

import java.util.Calendar;
import java.util.Set;

class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

    public FutureMeetingImpl(int id, Calendar date, Set<Contact> aListOfContacts) throws IllegalMeetingException {
        super(id, date, aListOfContacts);
    }
}
