package com.keimi.okamoto.app.utils;


import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.FutureMeetingImpl;
import com.keimi.okamoto.app.items.IllegalMeetingException;

import java.util.Calendar;
import java.util.Set;


public class MeetingFactoryImpl implements MeetingFactory {

    @Override
    public FutureMeeting createFutureMeeting(int id, Calendar date, Set<Contact> aListOfContacts) throws IllegalMeetingException {
        return new FutureMeetingImpl(id, date, aListOfContacts);
    }
}
