package src.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactTest {
    Contact aContact = new ContactImpl();
    Contact aContact1 = new ContactImpl();
    Contact aContact2 = new ContactImpl();

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

    }

}
