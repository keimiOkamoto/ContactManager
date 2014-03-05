/**
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;

import java.util.*;

public class ContactsContainerImpl implements ContactsContainer {
    private Map<Integer, Contact> contactMap;
    private ContactFactory aContactFactory;
    private UniqueNumberGenerator aUniqueNumberGenerator;

    /**
     * Constructor that makes a HashMap.
     * Constructor dependency injection to inject aContactFactory
     * so a contactFactor must be passed through.
     * Dependency injection for unique number.
     */
    public ContactsContainerImpl(ContactFactory aContactFactory, UniqueNumberGenerator aUniqueNumberGenerator) {
        contactMap = new HashMap<>();
        this.aContactFactory = aContactFactory;
        this.aUniqueNumberGenerator = aUniqueNumberGenerator;
    }

    /**
     * Method that adds a contact to a HashMap
     *
     * @param name  Contacts name
     * @param notes Contact note
     */
    @Override
    public void addContact(String name, String notes) {
        int uniqueID = aUniqueNumberGenerator.getUniqueNumber();

        Contact aNewContact = aContactFactory.createContact(uniqueID, name, notes);
        contactMap.put(uniqueID, aNewContact);
    }

    /**
     * Gets a contact from HashMap using the contacts ID.
     *
     * @param contactId contact's ID
     * @return a contact
     */
    @Override
    public Contact getContact(int contactId) {
        return contactMap.get(contactId);
    }

    /**
     * Checks for a valid id.
     *
     * @param ids Contact id
     * @return true if the contact exists.
     */
    @Override
    public boolean checkForValidId(int... ids) {
        boolean result = true;
        for (int id : ids) {
            if (!contactMap.containsKey(id)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Get's contact by name.
     *
     * @param name of contact
     * @return a Set if contacts by valid names
     */
    @Override
    public Set<Contact> getContacts(String name) {
        Set<Contact> resultSet = new HashSet<>();
        for (Contact c : contactMap.values()) {
            if (c != null && c.getName().equals(name)) {
                resultSet.add(c);
            }
        }
        return resultSet;

    }

    /**
     * check for valid name, if not valid returns
     * false.
     *
     * @param name of contact
     * @return false if name does not exist
     */
    @Override
    public boolean checkForValidName(String name) {
        boolean result = false;
        for (Contact c : contactMap.values()) {
            if (c != null && c.getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Method checks that all contacts exist or is not null
     *
     * @param aSetOfContacts a list of contacts
     * @return false if the contact doesn't exist or is a null Set
     */
    @Override
    public boolean checkForValidSetOfContacts(Set<Contact> aSetOfContacts) {
        boolean result = false;

        for (Contact c : aSetOfContacts) {
            if (checkForValidId(c.getId())) {
                result = true;
            }
        }
        return result;
    }
}
