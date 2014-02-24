package com.keimi.okamoto.meetingItem.utils;

/**
 * Created by keimiokamoto on 24/02/2014.
 */
public class UniqueNumberGeneratorImpl implements UniqueNumberGenerator {
    private static int id = 0;

    @Override
    public int getUniqueNumber() {
        return UniqueNumberGeneratorImpl.id++;
    }
}
