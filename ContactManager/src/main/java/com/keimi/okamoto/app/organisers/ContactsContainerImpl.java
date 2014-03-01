/**
 * ContactsContainer class created to take the implementation
 * responsibilities away from ContactManager.
 */
package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.ContactImpl;
import com.keimi.okamoto.app.utils.UniqueNumberGenerator;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContactsContainerImpl implements ContactsContainer {
    private Map<Integer, Contact> contactMap;
    private Set<Contact>aSet = new HashSet<>();

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
