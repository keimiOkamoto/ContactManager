package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;

import java.util.Set;

/**
 * A ContactContainer.
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
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
     * @param ids variable number of Contact ids
     * @return true if the contact exists.
     */
    boolean checkForValidId(int... ids);

    /**
     * Gets a set of contacts by name
     *
     * @param name a name of a contact
     * @return a Set of contacts by name
     */
    Set<Contact> getContacts(String name);

    /**
     * boolean that returns true if the name is valid
     *
     * @param name a name of a contact
     * @return boolean that returns true if the name is valid
     */
    boolean checkForValidName(String name);

    /**
     * checks that contacts are valid
     *
     * @param aSetOfContacts a List of contacts
     * @return false if set of contacts in not valid
     */
    boolean checkForValidSetOfContacts(Set<Contact> aSetOfContacts);
}
