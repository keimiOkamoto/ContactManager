package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;

/**
 * A simple factory to control the creating of a contact.
 * Factories help maintain organised code and hides the
 * implementation. Factories allow flexible checkForFuture modification.
 */
public interface ContactFactory {
    /**
     * Single method that creates contact
     *
     * @param id   id of a contact
     * @param name name of a contact
     * @param note note for the contact
     * @return a new contact
     */
    Contact createContact(int id, String name, String note);
}
