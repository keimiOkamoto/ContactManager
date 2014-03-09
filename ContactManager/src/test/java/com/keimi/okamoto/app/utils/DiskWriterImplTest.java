package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.items.Contact;
import com.keimi.okamoto.app.items.ContactImpl;
import com.keimi.okamoto.app.organisers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

/*
 * Test for DiskWriterImpl
 */
public class DiskWriterImplTest {

    private ContactsContainer contactsContainer;
    private MeetingContainer meetingContainer;
    private DiskWriterImpl diskWriter;
    private UniqueNumberGeneratorUtilities uniqueNumberGeneratorUtilities;
    private String pathname;

    @Before
    public void buildUp() {
        pathname = "contacts.txt";
        uniqueNumberGeneratorUtilities = mock(UniqueNumberGeneratorUtilities.class, withSettings().serializable());
        ContactFactory contactFactory = new ContactFactoryImpl();
        MeetingFactory meetingFactory = new MeetingFactoryImpl();

        contactsContainer = new ContactsContainerImpl(contactFactory, uniqueNumberGeneratorUtilities);
        meetingContainer = new MeetingContainerImpl(meetingFactory, uniqueNumberGeneratorUtilities);
        diskWriter = new DiskWriterImpl();
    }

    @Test
    public void shouldBeAbleToWriteToDisk() {
        int id = 2;
        when(uniqueNumberGeneratorUtilities.getUniqueNumber()).thenReturn(id);
        String adam = "Adam";
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, 2);

        String notes = "notes...";
        contactsContainer.addContact(adam, notes);
        Set<Contact> contactSet = new HashSet<>();
        String benny = "Benny";
        contactSet.add(new ContactImpl(benny, id));
        meetingContainer.addFutureMeeting(contactSet, date);

        diskWriter.writeToDisk(contactsContainer, meetingContainer);

        assertTrue(diskWriter.checkIfDataExists());

        diskWriter.readDisk();
        ContactsContainer cc1Expected = diskWriter.getContactContainer();
        MeetingContainer mc1Expected = diskWriter.getMeetingContainer();

        assertTrue(cc1Expected.checkForValidName(adam));
        assertEquals(id, mc1Expected.getFutureMeeting(id).getId());
    }

    /*
     * Make sure that the file is removed after the text is ran.
     */
    @After
    public void cleanUp() {
        new File(pathname).deleteOnExit();
    }
}
