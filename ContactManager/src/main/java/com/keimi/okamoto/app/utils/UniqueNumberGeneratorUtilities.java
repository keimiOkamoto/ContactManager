package com.keimi.okamoto.app.utils;

/**
 * A unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
public interface UniqueNumberGeneratorUtilities {
    /**
     * Method that gets a unique number;
     *
     * @return a unique number
     */
    int getUniqueNumber();
}
