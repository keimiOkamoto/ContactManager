/**
 * A simple factory to control the creationg of a contact.
 * Factories help maitain organised code and hides the
 * implementation. Factories allow flexible checkForFuture modification.
 */
package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;


public interface ContactFactory {
    /**
     * Single method that creates contact
     * @param id id of a contact
     * @param name name of a contact
     * @param note note for the contact
     * @return a new contact
     */
    Contact createContact(int id, String name, String note);
}
