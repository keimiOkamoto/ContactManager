package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;
import com.keimi.okamoto.app.utils.MeetingFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;

import java.util.*;


public class MeetingContainerImpl implements MeetingContainer {
    private Map<Integer, Meeting> aMeetingMap;
    private UniqueNumberGenerator aUniqueNumberGenerator;
    private MeetingFactory aMeetingFactory;
    private Map <Integer, Set<Integer>> contactMeetingMap;

    public MeetingContainerImpl(MeetingFactory aMeetingFactory, UniqueNumberGenerator aUniqueNumberGenerator) {
        aMeetingMap = new HashMap<>();
        this.aMeetingFactory = aMeetingFactory;
        this.aUniqueNumberGenerator = aUniqueNumberGenerator;
        contactMeetingMap = new HashMap<>();
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
            addToContactMeetingMap(aSetOfContacts, aNewMeeting);
            aMeetingMap.put(uniqueID, aNewMeeting);
        }
        return uniqueID;
    }

    /*
     * Helper method to add to a contactMeetingMap
     */
    private void addToContactMeetingMap(Set<Contact> aSetOfContacts, FutureMeeting aNewMeeting) {
        for (Contact contact : aSetOfContacts) {
            Set<Integer> meetingIds = contactMeetingMap.get(contact.getId());
            if (meetingIds == null) {
                meetingIds = new HashSet<>();
                meetingIds.add(aNewMeeting.getId());
                contactMeetingMap.put(contact.getId(), meetingIds);
            } else {
                meetingIds.add(aNewMeeting.getId());
            }
        }
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
    public void addPastMeeting(Set<Contact> aSetOfContacts, Calendar date, String notes) throws IllegalArgumentException {
        Meeting aNewMeeting = null;
        int id = aUniqueNumberGenerator.getUniqueNumber();

        if(!checkForPast(date)) throw new IllegalArgumentException();
        try {
            aNewMeeting = aMeetingFactory.createPastMeeting(id, aSetOfContacts, date, notes);
        } catch (IllegalMeetingException e) {
            e.printStackTrace();
        }
        if (aNewMeeting != null) {
            aMeetingMap.put(aNewMeeting.getId(), aNewMeeting);
        }
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        return (PastMeeting) aMeetingMap.get(id);
    }

    @Override
    public void convertToPastMeeting(Meeting aMeeting, String notes) {
        int futureMeetingId = aMeeting.getId();
        Calendar futureMeetingDate = aMeeting.getDate();
        Set<Contact> futureContactSet = aMeeting.getContacts();

        try {
            PastMeeting pastMeeting = aMeetingFactory.createPastMeeting(futureMeetingId, futureContactSet, futureMeetingDate, notes);
            aMeetingMap.put(futureMeetingId, pastMeeting);
        } catch (IllegalMeetingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Integer> getMeetingIdListBy(Contact contact) {
        return contactMeetingMap.get(contact.getId());
    }
}
