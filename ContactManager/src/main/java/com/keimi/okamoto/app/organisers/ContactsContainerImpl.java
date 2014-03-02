/**
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.ContactImpl;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContactsContainerImpl implements ContactsContainer {
    private Map<Integer, Contact> contactMap;
    private ContactFactory aContactFactory;
    private UniqueNumberGenerator aGenarator;

    /**
     * Constructor that makes a HashMap.
     * Constructor dependency injection to inject aContactFactory
     * so a contactFactor must be passed through.
     * Dependency injection for unique number.
     */
    public ContactsContainerImpl(ContactFactory aContactFactory, UniqueNumberGenerator aGenarator) {
        contactMap = new HashMap<>();
        this.aContactFactory = aContactFactory;
        this.aGenarator = aGenarator;
    }

    /**
     * Method that adds a contact to a HashMap
     *
     * @param name Contacts name
     * @param notes Contact note
     */
    @Override
    public void addContact(String name, String notes) {
        int uniqueID = aGenarator.getUniqueNumber();

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
}
