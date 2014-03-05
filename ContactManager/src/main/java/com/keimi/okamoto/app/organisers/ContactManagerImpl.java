package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.*;

import java.util.*;


public class ContactManagerImpl implements ContactManager {
    private ContactsContainer aContactsContainer;
    private MeetingContainer aMeetingContainer;

    /**
     * @param aContactsContainer A container that holds contacts
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

    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
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
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws IllegalArgumentException {
        if (!aContactsContainer.checkForValidSetOfContacts(contacts)) {
            throw new IllegalArgumentException();
        }
        aMeetingContainer.addPastMeeting(contacts, date, text);

    }

    @Override
    public void addMeetingNotes(int id, String text) {

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
