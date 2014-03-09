package com.keimi.okamoto.app.items;

import java.io.Serializable;

public class ContactImpl implements Contact, Serializable {
    private int id;
    private String name;
    private String note;

    /**
     * Constructor method
     *
     * @param name a name of a contact
     * @param id   an id for a contact
     */
    public ContactImpl(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNotes() {
        return note;
    }

    @Override
    public void addNotes(String note) {
        this.note = note;
    }
}
