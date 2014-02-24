package com.keimi.okamoto.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;


public class ContactTest {
    Contact aContact;
    Contact aContact1;
    Contact aContact2;

    @Before
    public void buildUp() {
        aContact = new ContactImpl("Adam");
        aContact1 = new ContactImpl("Barry");
        aContact2 = new ContactImpl("Clare");
    }

    @Test
    public void shouldBeAbleToGetUniqueId() {
        int actual = aContact.getId();
        int expected = 0;

        assertEquals(expected, actual);

        int actual1 = aContact1.getId();
        int expected1 = 1;

        assertEquals(expected1, actual1);

        int actual2 = aContact2.getId();
        int expected2 = 2;

        assertEquals(expected2, actual2);
    }

    @Test
    public void shouldBeAbleToGetName() {
        String actual = aContact.getName();
        String expected = "Adam";

        assertEquals(expected,actual);

        String actual1 = aContact1.getName();
        String expected1 = "Barry";

        assertEquals(actual1, expected1);

        String actual2 = aContact2.getName();
        String expected2 = "Clare";

        assertEquals(actual2, expected2);
    }

    @Test
    public void shouldBeAbleToGetNotesIfContactHasNotes() {
        aContact.addNotes("Works for company A as a manager in department A");
        String actual = aContact.getNotes();
        String expected = "Works for company A as a manager in department A";

        assertEquals(actual, expected);

        aContact1.addNotes("Barry works for company B as a business analyst.");
        String actual1 = aContact1.getNotes();
        String expected1 = "Barry works for company B as a business analyst.";

        assertEquals(actual1, expected1);
    }

    @Test
    public void shouldBeAbleToGetEmptyStringIfContactHasNoNotes() {
        aContact.addNotes("");
        String actual = "";
        String expected = "";

        assertEquals(actual, expected);
    }
}
