package src.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void shouldBeAbleToGetUniqueId() {
        Contact aContact = new ContactImpl();
        int actual = aContact.getId();
        int expected = 0;

        assertEquals(expected, actual);

        Contact aContact1 = new ContactImpl();
        int actual1 = aContact1.getId();
        int expected1 = 1;

        assertEquals(expected1, actual1);

        Contact aContact2 = new ContactImpl();
        int actual2 = aContact2.getId();
        int expected2 = 2;

        assertEquals(expected2, actual2);
    }

}
