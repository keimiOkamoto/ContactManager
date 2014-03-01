/**
 * Implementation for unique number generator. As the meeting and contact
 * both have unique numbers to avoid code repetition a tool package
 * was made.
 */
package com.keimi.okamoto.app.utils;

public class UniqueNumberGeneratorImpl implements UniqueNumberGenerator {
    private static UniqueNumberGenerator singleton;
    private static int id = 0;

    /**
     * Private method to stop other classes from
     * instantiating this object.
     */
    private UniqueNumberGeneratorImpl() {
    }

    /**
     * Static method that allows people to reach the single
     * instance.
     *
     * @return the single instance.
     */
    public static UniqueNumberGenerator getInstance() {
        if (singleton == null) {
            singleton = new UniqueNumberGeneratorImpl();
        }
        return singleton;
    }

    /**
     * Method that gets a unique number.
     *
     * @return a unique number
     */
    @Override
    public int getUniqueNumber() {
        return UniqueNumberGeneratorImpl.id++;
    }
}
