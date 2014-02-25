package com.keimi.okamoto.meetingItem;


import org.junit.*;
import static org.junit.Assert.*;


public class ContactTest {
    private Contact aContact;

    @Before
    public void buildUp() {
        aContact = new ContactImpl("Adam", 0);
    }

    @Test
    public void shouldBeAbleToGetId() {
        int actual = aContact.getId();
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToGetName() {
        String actual = aContact.getName();
        String expected = "Adam";

        assertEquals(expected,actual);
    }

    @Test
    public void shouldBeAbleToGetNotesIfContactHasNotes() {
        aContact.addNotes("Works for company A as a manager in department A");
        String actual = aContact.getNotes();
        String expected = "Works for company A as a manager in department A";

        assertEquals(actual, expected);
    }

    @Test
    public void shouldBeAbleToGetEmptyStringIfContactHasNoNotes() {
        aContact.addNotes("");
        String actual = "";
        String expected = "";

        assertEquals(actual, expected);
    }
}
