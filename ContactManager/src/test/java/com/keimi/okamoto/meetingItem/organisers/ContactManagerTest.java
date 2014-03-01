package com.keimi.okamoto.meetingItem.organisers;

import com.keimi.okamoto.meetingItem.Contact;

import com.keimi.okamoto.meetingItem.ContactImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class ContactManagerTest {
    ContactManager aContactManager;
    Set<Contact> contactSet;
    Calendar date;

    /*
    @Before
    public void buildUp() {
        contactSet = new HashSet<>();
        aContactManager = new ContactManagerImpl();

        date = Calendar.getInstance();

        //make sure meeting has at lease one contact or illegalMeeting
        //MeetingContainer.makeFutureMeeting(); //generate id for meeting and
    }
    */

    @Test
    public void shouldBeAbleToAddContact() {
        ContactsContainer aContactContainer = new ContactsContainerImpl();
        aContactContainer.addContact("Adam", "Some notes go here");
    }

    /*
    @Test
    public void shouldBeAbleToAddFutureMeeting() {
        aContactManager.addFutureMeeting(contactSet, date);

        //make sure that meeting has been added.
    }
    */
}
