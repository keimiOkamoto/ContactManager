package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;
import com.keimi.okamoto.app.utils.MeetingFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MeetingContainerImpl implements MeetingContainer {
    private Map<Integer, Meeting> aMeetingMap;
    private UniqueNumberGenerator aUniqueNumberGenerator;
    private MeetingFactory aMeetingFactory;

    public MeetingContainerImpl(MeetingFactory aMeetingFactory, UniqueNumberGenerator aUniqueNumberGenerator) {
        aMeetingMap = new HashMap<>();
        this.aMeetingFactory = aMeetingFactory;
        this.aUniqueNumberGenerator = aUniqueNumberGenerator;
    }

    /**
     * A method to add a checkForFuture meeting, taking in a Set of contacts
     * and the date.
     *
     * @param aSetOfContacts contacts that will attend the meeting
     * @param date of the meeting
     * @return the meeting id
     */
    @Override
    public int addFutureMeeting(Set<Contact> aSetOfContacts, Calendar date) {
        int uniqueID = aUniqueNumberGenerator.getUniqueNumber();

        if (checkForFuture(date)) {
            FutureMeeting aNewMeeting = null;
            try {
                aNewMeeting = aMeetingFactory.createFutureMeeting(uniqueID, date, aSetOfContacts);
            } catch (IllegalMeetingException e) {
                e.printStackTrace();
            }
            aMeetingMap.put(uniqueID, aNewMeeting);
        }
        return uniqueID;
    }

    /**
     * method that checks if the date is in the past
     * @param date A date
     * @return true if date is in the checkForFuture
     */
    @Override
    public boolean checkForFuture(Calendar date) {
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
     * @return true if the date is in the past
     */
    @Override
    public boolean checkForPast(Calendar date) {
        Calendar now = Calendar.getInstance();
        if (date.after(now)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the FutureMeeting corresponding to that id in
     * aMeetingMAp and null if there is none.
     *
     * @param id checkForFuture meeting ID
     * @return FutureMeeting if ID exists null if id doesn't exist
     */
    @Override
    public FutureMeeting getFutureMeeting(int id) {
        return (FutureMeeting)aMeetingMap.get(id);
    }

    /**
     *
     * @param id meeting ID
     * @return Meeting if ID exists null if id doesn't exist
     */
    @Override
    public Meeting getMeeting(int id) {
        return aMeetingMap.get(id);
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    @Override
    public void addPastMeeting(Set<Contact> aSetOfContacts, Calendar date, String notes) {
        Calendar now = Calendar.getInstance();
        Meeting aNewMeeting = null;

        if (date.before(now)) {
            aNewMeeting = aMeetingFactory.createPastMeeting(aSetOfContacts, date, note);
            aMeetingMap.put(aNewMeeting.getId(), aNewMeeting);
        }
    }
}
