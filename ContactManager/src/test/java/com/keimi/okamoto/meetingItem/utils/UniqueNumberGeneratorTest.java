/**
 * Test for unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
package com.keimi.okamoto.meetingItem.utils;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class UniqueNumberGeneratorTest {

    /**
     * Test to make sure the method is returning a
     * unique number.
     */
    @Test
    public void shouldBeAbleToGenerateUniqueNumber() {
        UniqueNumberGenerator aUniqueNumberGenerator = UniqueNumberGeneratorImpl.getInstance();
        int actual = aUniqueNumberGenerator.getUniqueNumber();
        int expected = 0;

        assertEquals(expected, actual);

        actual = aUniqueNumberGenerator.getUniqueNumber();
        expected = 1;

        assertEquals(expected, actual);
    }
}
