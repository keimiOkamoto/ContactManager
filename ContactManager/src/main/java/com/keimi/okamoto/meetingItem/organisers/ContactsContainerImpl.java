/**
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
package com.keimi.okamoto.meetingItem.organisers;

import com.keimi.okamoto.meetingItem.Contact;
import com.keimi.okamoto.meetingItem.ContactImpl;
import com.keimi.okamoto.meetingItem.utils.UniqueNumberGenerator;
import com.keimi.okamoto.meetingItem.utils.UniqueNumberGeneratorImpl;

import java.util.HashMap;
import java.util.Map;

public class ContactsContainerImpl implements ContactsContainer {
    private Map<Integer, Contact> contactMap;

    /**
     * Constructor that makes a HashMap.
     */
    public ContactsContainerImpl() {
        contactMap = new HashMap<>();
    }

    /**
     * Method that adds a contact to a HashMap
     *
     * @param name
     * @param notes
     */
    @Override
    public void addContact(String name, String notes) {
        UniqueNumberGenerator aGenarator = UniqueNumberGeneratorImpl.getInstance();
        int uniqueID = aGenarator.getUniqueNumber();

        Contact aNewContact = new ContactImpl(name, uniqueID);
        aNewContact.addNotes(notes);

        contactMap.put(uniqueID, aNewContact);
    }

    /**
     * Gets a contact from HashMap using the contacts ID.
     *
     * @param contactId
     * @return a contact
     */
    @Override
    public Contact getContact(int contactId) {
        Contact aContact = contactMap.get(contactId);
        return aContact;
    }
}
