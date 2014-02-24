package com.keimi.okamoto.meetingItem.utils;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class UniqueNumberGeneratorTest {

    @Test
    public void shouldBeAbleToGenerateUniqueNumber() {
        UniqueNumberGenerator aUniqueNumberGenerator = new UniqueNumberGeneratorImpl();
        int actual = aUniqueNumberGenerator.getUniqueNumber();
        int expected = 0;

        assertEquals(expected, actual);

        actual = aUniqueNumberGenerator.getUniqueNumber();
        expected = 1;

        assertEquals(expected, actual);
    }
}
