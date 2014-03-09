/**
 * Test class for IllegalMeetingException.
 */
package com.keimi.okamoto.app.utils;

import com.keimi.okamoto.app.utils.IllegalMeetingException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class IllegalMeetingExceptionTest {

    /**
     * ExpectedException Rules allows in-test specification of
     * expected exception types and messages.
     * (Note: As assertEquals will not test for the right string
     * so junit Rule can be used.)
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Tests for the correct exception and message to be printed if a
     * IllegalMeetingException is thrown.
     *
     * @throws com.keimi.okamoto.app.utils.IllegalMeetingException if the set does not contain at least one contact.
     */
    @Test
    public void shouldHaveAppropriateMessage() throws IllegalMeetingException {
        thrown.expect(IllegalMeetingException.class);
        thrown.expectMessage("Meeting must contain at least one contact.");

        throw new IllegalMeetingException();
    }
}
