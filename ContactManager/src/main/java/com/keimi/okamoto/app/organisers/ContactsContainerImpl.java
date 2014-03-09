package com.keimi.okamoto.app.organisers;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.utils.ContactFactory;
import com.keimi.okamoto.app.utils.UniqueNumberGeneratorUtilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContactsContainerImpl implements ContactsContainer, Serializable {
    private Map<Integer, Contact> contactMap;
    private ContactFactory aContactFactory;
    private UniqueNumberGeneratorUtilities aUniqueNumberGeneratorUtilities;

    /**
     * Constructor that makes a HashMap.
     * Constructor dependency injection to inject aContactFactory
     * so a contactFactor must be passed through.
     * Dependency injection for unique number.
     */
    public ContactsContainerImpl(ContactFactory aContactFactory, UniqueNumberGeneratorUtilities aUniqueNumberGeneratorUtilities) {
        contactMap = new HashMap<>();
        this.aContactFactory = aContactFactory;
        this.aUniqueNumberGeneratorUtilities = aUniqueNumberGeneratorUtilities;
    }

    @Override
    public void addContact(String name, String notes) throws IllegalArgumentException {
        if (name == null || notes == null) throw new IllegalArgumentException();
        int uniqueID = aUniqueNumberGeneratorUtilities.getUniqueNumber();

        Contact aNewContact = aContactFactory.createContact(uniqueID, name, notes);
        contactMap.put(uniqueID, aNewContact);
    }

    @Override
    public Contact getContact(int contactId) {
        return contactMap.get(contactId);
    }

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

    @Override
    public Set<Contact> getContacts(String name) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException();

        Set<Contact> resultSet = new HashSet<>();
        for (Contact c : contactMap.values()) {
            if (c != null && c.getName().equals(name)) {
                resultSet.add(c);
            }
        }
        return resultSet;
    }

    @Override
    public boolean checkForValidName(String name) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException();

        boolean result = false;
        for (Contact c : contactMap.values()) {
            if (c != null && c.getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean checkForValidSetOfContacts(Set<Contact> aSetOfContacts) throws IllegalArgumentException {
        if (aSetOfContacts == null) throw new IllegalArgumentException();

        boolean result = false;
        for (Contact c : aSetOfContacts) {
            if (checkForValidId(c.getId())) {
                result = true;
            }
        }
        return result;
    }
}
