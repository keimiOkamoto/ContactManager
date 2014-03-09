package com.keimi.okamoto.app.organisers;

/**
 * Created by keimiokamoto on 09/03/2014.
 */
public interface DiskWriter {
    void writeToDisk(ContactsContainer aContactsContainer, MeetingContainer aMeetingContainer);

    boolean checkIfDataExists();

    void readDisk();

    ContactsContainer getContactContainer();

    MeetingContainer getMeetingContainer();
}
