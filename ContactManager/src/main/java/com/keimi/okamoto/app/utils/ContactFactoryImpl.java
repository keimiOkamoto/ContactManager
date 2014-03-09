/**
 * A simple factory to control the creation of a contact.
 * Factories help maintain organised code and hides the
 * implementation. Factories allow flexible checkForFuture modification.
 */
package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.ContactImpl;

import java.io.Serializable;


public class ContactFactoryImpl implements ContactFactory, Serializable {
    /**
     * Single method that creates contact
     * @param id id of a contact
     * @param name name of a contact
     * @param note note for the contact
     * @return a new contact
     */
    @Override
    public Contact createContact(int id, String name, String note) {
        Contact aNewContact = new ContactImpl(name, id);
        aNewContact.addNotes(note);
        return aNewContact;
    }
}
