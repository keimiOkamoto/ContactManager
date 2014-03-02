package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.FutureMeeting;
import com.keimi.okamoto.app.items.Meeting;
import com.keimi.okamoto.app.items.PastMeeting;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.ContactFactoryImpl;

import java.util.*;


public class ContactManagerImpl implements ContactManager {
    private ContactsContainer aContactsContainer;

    /**
     * @param aContactsContainer A container that holds contacts
     */
    public ContactManagerImpl(ContactsContainer aContactsContainer) {
        this.aContactsContainer = aContactsContainer;
    }

    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        return 0;
    }

    @Override
    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    @Override
    public FutureMeeting getFutureMeeting(int id) {
        return null;
    }

    @Override
    public Meeting getMeeting(int id) {
        return null;
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
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {

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
     * @param ids an arbitrary number of contact IDs (variable argument)
     * @return a Set of contacts based on ID parameter.
     */
    @Override
    public Set<Contact> getContacts(int... ids) {
        Set<Contact> result = new HashSet<>();
        for (int id : ids) {
            result.add(aContactsContainer.getContact(id));
        }
        return result;
    }

    @Override
    public Set<Contact> getContacts(String name) {
        return null;
    }
}
