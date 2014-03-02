/**
 * Interface for ContactContainer.
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.utils.ContactFactory;

import java.util.Set;


public interface ContactsContainer {
    /**
     * Method that adds a contact to a HashMap
     *
     * @param name  Contacts name
     * @param notes Contact note
     */
    void addContact(String name, String notes);

    /**
     * Gets a contact from HashMap using the contacts ID.
     *
     * @param contactId contact's ID
     * @return a contact
     */
    Contact getContact(int contactId);

    /**
     * Checks for a valid id.
     *
     * @param ids Contact id
     * @return true if the contact exists.
     */
    boolean checkForValidId(int... ids);

    Set<Contact> getContacts(String name);

    boolean checkForValidName(String name);
}
