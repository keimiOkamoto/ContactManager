package src.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void shouldBeAbleToGetUniqueId() {
        Contact aContact = new ContactImpl(1);
        int actual = aContact.getId();
        int expected = 1;

        assertEquals(expected, actual);

        Contact aContact1 = new ContactImpl(1);
        int actual1 = aContact1.getId();
        int expected1 = 1;

        assertEquals(expected1, actual1);
    }

}
