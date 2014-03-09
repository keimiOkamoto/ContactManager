package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.Meeting;
import com.keimi.okamoto.app.items.PastMeeting;
import com.keimi.okamoto.app.utils.*;

import java.util.*;


public class ContactManagerImpl implements ContactManager {
    private ContactsContainer aContactsContainer;
    private MeetingContainer aMeetingContainer;
    private DiskWriter aDiskWriter;

    /**
     * Constructor for ContactManagerImpl
     *
     * @param aContactsContainer A container that holds contacts
     * @param aMeetingContainer  A container that holds meetings
     */
    public ContactManagerImpl(ContactsContainer aContactsContainer, MeetingContainer aMeetingContainer, DiskWriter aDiskWriter) {
        if (aDiskWriter.checkIfDataExists()) {
            aDiskWriter.readDisk();
            this.aContactsContainer = aDiskWriter.getContactContainer();
            this.aMeetingContainer = aDiskWriter.getMeetingContainer();
        } else {
            this.aContactsContainer = aContactsContainer;
            this.aMeetingContainer = aMeetingContainer;
        }
        this.aDiskWriter = aDiskWriter;
        addShutdownHook();
    }

    /**
     * This method adds a shutdown hook
     */
    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                flush();
            }
        });
    }

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
        if (!aMeetingContainer.checkForFuture(date) || !aContactsContainer.checkForValidSetOfContacts(contacts)) {
            throw new IllegalArgumentException();
        }
        return aMeetingContainer.addFutureMeeting(contacts, date);
    }

    @Override
    public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {
        PastMeeting pastMeeting = aMeetingContainer.getPastMeeting(id);
        if (pastMeeting != null && aMeetingContainer.checkForFuture(pastMeeting.getDate())) {
            throw new IllegalArgumentException();
        }
        return pastMeeting;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) throws IllegalArgumentException {
        FutureMeeting futureMeeting = aMeetingContainer.getFutureMeeting(id);
        if (futureMeeting != null && aMeetingContainer.checkForPast(futureMeeting.getDate())) {
            throw new IllegalArgumentException();
        }
        return futureMeeting;
    }

    @Override
    public Meeting getMeeting(int id) {
        return aMeetingContainer.getMeeting(id);
    }

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
        if (!aContactsContainer.checkForValidName(contact.getName())) throw new IllegalArgumentException();

        Set<Integer> meetingIds = aMeetingContainer.getMeetingIdListBy(contact);
        List<Meeting> meetings = new ArrayList<>();
        if (meetingIds != null) {
            for (int id : meetingIds) {
                Meeting aMeeting = aMeetingContainer.getMeeting(id);
                if (aMeeting instanceof FutureMeeting) {
                    meetings.add(aMeeting);
                }
            }
        }
        return sortInChronologicalOrder(meetings);
    }

    /**
     * A private method that sorts meeting in chronological order.
     *
     * @param meetings a list of meetings
     * @return a list of meeting sorted in chronological order
     */
    private List<Meeting> sortInChronologicalOrder(List<Meeting> meetings) {
        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return meetings;
    }

    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) {
        Set<Integer> meetingIds = aMeetingContainer.getMeetingIdListBy(date);
        List<Meeting> meetings = new ArrayList<>();
        if (meetingIds != null) {
            for (int id : meetingIds) {
                Meeting aMeeting = aMeetingContainer.getMeeting(id);
                if (aMeeting instanceof FutureMeeting) {
                    meetings.add(aMeeting);
                }
            }
        }
        return sortInChronologicalOrder(meetings);
    }

    @Override
    public List<PastMeeting> getPastMeetingList(Contact contact) {
        if (!aContactsContainer.checkForValidName(contact.getName())) throw new IllegalArgumentException();

        Set<Integer> meetingIds = aMeetingContainer.getMeetingIdListBy(contact);
        List<Meeting> meetings = new ArrayList<>();
        if (meetingIds != null) {
            for (int id : meetingIds) {
                Meeting aMeeting = aMeetingContainer.getMeeting(id);
                if (aMeeting instanceof PastMeeting) {
                    meetings.add(aMeeting);
                }
            }
        }
        return (List<PastMeeting>) (List<?>) sortInChronologicalOrder(meetings);
    }

    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String notes) throws IllegalArgumentException, NullPointerException {
        if (contacts == null || date == null || notes == null) throw new NullPointerException();
        if (contacts.isEmpty() || !aContactsContainer.checkForValidSetOfContacts(contacts))
            throw new IllegalArgumentException();

        aMeetingContainer.addPastMeeting(contacts, date, notes);

    }

    @Override
    public void addMeetingNotes(int id, String text) throws NullPointerException, IllegalArgumentException, IllegalStateException {
        if (text == null) throw new NullPointerException();
        Meeting aMeeting = aMeetingContainer.getMeeting(id);
        if (aMeeting == null) throw new IllegalArgumentException();
        if (aMeetingContainer.checkForFuture(aMeeting.getDate())) throw new IllegalStateException();

        aMeetingContainer.convertToPastMeeting(aMeeting, text);
    }

    @Override
    public void addNewContact(String name, String notes) throws NullPointerException {
        if (name == null || notes == null) throw new NullPointerException();

        aContactsContainer.addContact(name, notes);
    }

    @Override
    public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
        if (!aContactsContainer.checkForValidId(ids)) {
            throw new IllegalArgumentException();
        }
        Set<Contact> result = new HashSet<>();
        for (int id : ids) {
            result.add(aContactsContainer.getContact(id));
        }
        return result;
    }

    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException {
        if (!aContactsContainer.checkForValidName(name)) throw new NullPointerException();

        return aContactsContainer.getContacts(name);
    }

    @Override
    public void flush() {
        aDiskWriter.writeToDisk(aContactsContainer, aMeetingContainer);
    }
}
