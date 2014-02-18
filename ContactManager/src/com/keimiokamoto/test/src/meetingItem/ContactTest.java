package src.meetingItem;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by keimiokamoto on 18/02/2014.
 */
public class ContactTest {
    @Test
    public void shouldBeAbleToGetId() {
        ContactImpl aContactImpl = new ContactImpl();
        int actual = aContactImpl.getId();
        int expected = 2;

        assertEquals(expected, actual);
    }
}
