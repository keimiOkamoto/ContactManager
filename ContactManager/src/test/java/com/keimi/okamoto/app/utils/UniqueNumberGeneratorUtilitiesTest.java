/**
 * Test for unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
package com.keimi.okamoto.app.utils;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class UniqueNumberGeneratorUtilitiesTest {

    /**
     * Test to make sure the method is returning a
     * unique number.
     */
    @Test
    public void shouldBeAbleToGenerateUniqueNumber() {
        UniqueNumberGeneratorUtilities aUniqueNumberGeneratorUtilities = UniqueNumberGeneratorUtilitiesImpl.getInstance();
        int actual = aUniqueNumberGeneratorUtilities.getUniqueNumber();
        int expected = 0;

        assertEquals(expected, actual);

        actual = aUniqueNumberGeneratorUtilities.getUniqueNumber();
        expected = 1;

        assertEquals(expected, actual);
    }
}
