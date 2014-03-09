/**
 * A unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
package com.keimi.okamoto.app.utils;

public interface UniqueNumberGeneratorUtilities {
    /**
     * Method that gets a unique number;
     * @return a unique number
     */
    int getUniqueNumber();
}
