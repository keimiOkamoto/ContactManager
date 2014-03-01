/**
 * Interface for ContactContainer.
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;

import java.util.Set;

public interface ContactsContainer {

    void addContact(String name, String notes);

    Contact getContact(int contactID);

}
