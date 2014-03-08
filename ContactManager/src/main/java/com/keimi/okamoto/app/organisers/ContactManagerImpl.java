package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;

import java.util.*;


public class ContactManagerImpl implements ContactManager {
    private ContactsContainer aContactsContainer;
    private MeetingContainer aMeetingContainer;

    /**
     * Constructor for ContactManagerImpl
     * @param aContactsContainer A container that holds contacts
     * @param aMeetingContainer A container that holds meetings
     */
    public ContactManagerImpl(ContactsContainer aContactsContainer, MeetingContainer aMeetingContainer) {
        this.aContactsContainer = aContactsContainer;
        this.aMeetingContainer = aMeetingContainer;
    }

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException, IllegalMeetingException {
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

    /**
     * contactsManager care about the sorting of the
     * @param contact one of the user’s contacts
     * @return
     */
    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        if (!aContactsContainer.checkForValidName(contact.getName())) throw new IllegalArgumentException();

        List<Integer> meetingIds = aMeetingContainer.getMeetingIdListBy(contact);
        List<Meeting> meetings = new ArrayList<>();


        for (int id : meetingIds) {
            Meeting aMeeting = aMeetingContainer.getMeeting(id);

                if (aMeeting instanceof FutureMeeting) {
                    meetings.add(aMeeting);
                }
        }
        return meetings;
    }

    @Override
    public List<Meeting> getFutureMeetingList(Calendar date) {
        return null;
    }

    @Override
    public List<PastMeeting> getPastMeetingList(Contact contact) {
        return null;
    }

    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String notes) throws IllegalArgumentException, NullPointerException {
        if (contacts == null || date == null || notes == null ) throw new NullPointerException();
        if (contacts.isEmpty() || !aContactsContainer.checkForValidSetOfContacts(contacts)) throw new IllegalArgumentException();

        try {
            aMeetingContainer.addPastMeeting(contacts, date, notes);
        } catch (IllegalMeetingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMeetingNotes(int id, String text) throws NullPointerException, IllegalArgumentException, IllegalStateException {
        if (text == null) throw new NullPointerException();
        Meeting aMeeting = aMeetingContainer.getMeeting(id);
        if (aMeeting == null) throw new IllegalArgumentException();
        if (aMeetingContainer.checkForFuture(aMeeting.getDate())) throw new IllegalStateException();

        aMeetingContainer.convertToPastMeeting(aMeeting, text);
    }

    /**
     * Adds a new contact to a HashMap
     *
     * @param name  the name of the contact.
     * @param notes notes to be added about the contact.
     * @throws NullPointerException
     */
    @Override
    public void addNewContact(String name, String notes) throws NullPointerException {
        if (name == null || notes == null) throw new NullPointerException();

        aContactsContainer.addContact(name, notes);
    }

    /**
     * Gets a Set of contacts based on ID parameter.
     *
     * @param ids an arbitrary number of contact IDs (variable argument)
     * @return a Set of contacts based on ID parameter.
     * @throws java.lang.IllegalArgumentException if ID does not exist
     */
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

    /**
     * Gets a Set containing the relevant contacts
     * from contact container.
     *
     * @param name the string to search for
     * @return a Set of contacts retrieved by name
     * @throws NullPointerException if the name is equal to null
     */
    @Override
    public Set<Contact> getContacts(String name) throws NullPointerException {
        if (!aContactsContainer.checkForValidName(name)) {
            throw new NullPointerException();
        }
        return aContactsContainer.getContacts(name);
    }
}
