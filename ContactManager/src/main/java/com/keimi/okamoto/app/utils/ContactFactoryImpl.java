package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.ContactImpl;

import java.io.Serializable;


public class ContactFactoryImpl implements ContactFactory, Serializable {

    @Override
    public Contact createContact(int id, String name, String note) {
        Contact aNewContact = new ContactImpl(name, id);
        aNewContact.addNotes(note);
        return aNewContact;
    }
}
