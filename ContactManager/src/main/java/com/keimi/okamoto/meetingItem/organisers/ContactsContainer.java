/**
 * Interface for ContactContainer.
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 *
 */
package com.keimi.okamoto.meetingItem.organisers;

import com.keimi.okamoto.meetingItem.Contact;

public interface ContactsContainer {

    void addContact(String name, String notes);

    Contact getContact();
}
