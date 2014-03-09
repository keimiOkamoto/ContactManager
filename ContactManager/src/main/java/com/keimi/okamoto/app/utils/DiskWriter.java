package com.keimi.okamoto.app.utils;


import com.keimi.okamoto.app.organisers.ContactsContainer;
import com.keimi.okamoto.app.organisers.MeetingContainer;

/**
 * Disk writer is a class that is responsible for the data
 * to be written to disk. Reads the date from disk.
 */
public interface DiskWriter {
    /**
     * Writes data to disk. Data is serialized here.
     *
     * @param aContactsContainer a ContactsContainer
     * @param aMeetingContainer  a MeetingContainer
     */
    void writeToDisk(ContactsContainer aContactsContainer, MeetingContainer aMeetingContainer);

    /**
     * Checks of the data exists
     *
     * @return true if data exists.
     */
    boolean checkIfDataExists();

    /**
     * Reads data from disk
     */
    void readDisk();

    /**
     * Returns a ContactsContainer
     *
     * @return a ContactsContainer
     */
    ContactsContainer getContactContainer();

    /**
     * Returns a MeetingContainer
     *
     * @return a MeetingContainer
     */
    MeetingContainer getMeetingContainer();
}
