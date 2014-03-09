package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;
import com.keimi.okamoto.app.utils.MeetingFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorUtilities;

import java.io.Serializable;
import java.util.*;


public class MeetingContainerImpl implements MeetingContainer, Serializable {
    private Map<Integer, Meeting> aMeetingMap;
    private UniqueNumberGeneratorUtilities aUniqueNumberGeneratorUtilities;
    private MeetingFactory aMeetingFactory;
    private Map <Integer, Set<Integer>> contactMeetingMap;

    /**
     * Constructor method for MeetingContainerImpl
     *
     * @param aMeetingFactory a Factory in charge of creation of meetings
     * @param aUniqueNumberGeneratorUtilities a unique number generator
     */
    public MeetingContainerImpl(MeetingFactory aMeetingFactory, UniqueNumberGeneratorUtilities aUniqueNumberGeneratorUtilities) {
        aMeetingMap = new HashMap<>();
        this.aMeetingFactory = aMeetingFactory;
        this.aUniqueNumberGeneratorUtilities = aUniqueNumberGeneratorUtilities;
        contactMeetingMap = new HashMap<>();
    }

    @Override
    public int addFutureMeeting(Set<Contact> aSetOfContacts, Calendar date) throws IllegalArgumentException {
        if (checkForPast(date)) throw new IllegalArgumentException();

        int uniqueID = aUniqueNumberGeneratorUtilities.getUniqueNumber();
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

    @Override
    public boolean checkForFuture(Calendar date) throws IllegalArgumentException {
        if (date == null) throw new IllegalArgumentException();
        Calendar now = Calendar.getInstance();
        return date.after(now);
    }

    @Override
    public boolean checkForPast(Calendar date) throws IllegalArgumentException {
        return !checkForFuture(date);
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {
        return (FutureMeeting)aMeetingMap.get(id);
    }

    @Override
    public Meeting getMeeting(int id) {
        return aMeetingMap.get(id);
    }

    @Override
    public void addPastMeeting(Set<Contact> aSetOfContacts, Calendar date, String notes) throws IllegalArgumentException {
        if (checkForFuture(date) || aSetOfContacts == null || notes == null) throw new IllegalArgumentException();
        Meeting aNewMeeting = null;
        int id = aUniqueNumberGeneratorUtilities.getUniqueNumber();
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
    public void convertToPastMeeting(Meeting aMeeting, String notes) throws IllegalArgumentException {
        if (aMeeting == null || notes == null) throw new IllegalArgumentException();

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
    public Set<Integer> getMeetingIdListBy(Contact contact) throws IllegalArgumentException {
        if (contact == null) throw new IllegalArgumentException();
        return contactMeetingMap.get(contact.getId());
    }

    @Override
    public Set<Integer> getMeetingIdListBy(Calendar date) throws IllegalArgumentException {
        if (date == null) throw new IllegalArgumentException();

        Set<Integer> meetingIds = new HashSet<>();
        for (Meeting meeting : aMeetingMap.values()) {
            if (meeting instanceof FutureMeeting && meeting.getDate() == date) {
                meetingIds.add(meeting.getId());
            }
        }
        return meetingIds;
    }
}
