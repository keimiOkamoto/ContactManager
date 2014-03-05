package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.IllegalMeetingException;
import com.keimi.okamoto.app.items.Meeting;
import com.keimi.okamoto.app.utils.MeetingFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;


public class MeetingContainerImpl implements MeetingContainer {
    private Map<Integer, Meeting> aFutureMeetingMap;
    private ContactsContainer contactsContainer;
    private UniqueNumberGenerator aUniqueNumberGenerator;
    private MeetingFactory aMeetingFactory;

    public MeetingContainerImpl(MeetingFactory aMeetingFactory, UniqueNumberGenerator aUniqueNumberGenerator) {
        this.aMeetingFactory = aMeetingFactory;
        this.aUniqueNumberGenerator = aUniqueNumberGenerator;
    }
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
        int uniqueID = aUniqueNumberGenerator.getUniqueNumber();

        if (future(date) && contactsContainer.checkForValidSetOfContacts(aSetOfContacts)) {
            FutureMeeting aNewMeeting = null;
            try {
                aNewMeeting = aMeetingFactory.createFutureMeeting(uniqueID, date, aSetOfContacts);
            } catch (IllegalMeetingException e) {
                e.printStackTrace();
            }
            aFutureMeetingMap.put(uniqueID, aNewMeeting);
        }
        return uniqueID;
    }

    /**
     * method that checks if the date is in the past
     * @param date A date
     * @return true if date is in the future
     */
    @Override
    public boolean future(Calendar date) {
        Calendar now = Calendar.getInstance();
        if (date.before(now)) {
            return false;
        }
        return true;
    }

    /**
     * method that checks if the date is in the past
     *
     * @param date A date
     * @return
     */
    @Override
    public boolean checkForPast(Calendar date) {
        Calendar now = Calendar.getInstance();
        if (date.after(now)) {
            return false;
        }
        return true;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {
        return null;
    }
}
