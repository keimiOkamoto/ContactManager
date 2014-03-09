package com.keimi.okamoto.app.organisers;

import java.io.*;

/**
 * Created by keimiokamoto on 09/03/2014.
 */
public class DiskWriterImpl implements DiskWriter {
    private static final String filename = "contacts.txt";
    private ContactsContainer contactsContainer;
    private MeetingContainer meetingContainer;

    @Override
    public void writeToDisk(ContactsContainer aContactsContainer, MeetingContainer aMeetingContainer) {
        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(DiskWriterImpl.filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(aContactsContainer);
            out.writeObject(aMeetingContainer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkIfDataExists() {
        File file = new File(DiskWriterImpl.filename);
        return file.exists();
    }

    @Override
    public void readDisk() {
        FileInputStream fis;
        ObjectInputStream in;

        try {
            fis = new FileInputStream(DiskWriterImpl.filename);
            in = new ObjectInputStream(fis);
            contactsContainer = (ContactsContainer) in.readObject();
            meetingContainer = (MeetingContainer) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactsContainer getContactContainer() {
        return contactsContainer;
    }

    @Override
    public MeetingContainer getMeetingContainer() {
        return meetingContainer;
    }
}
