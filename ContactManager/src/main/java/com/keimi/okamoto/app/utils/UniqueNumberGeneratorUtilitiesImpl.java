package com.keimi.okamoto.app.utils;

import java.io.Serializable;

public class UniqueNumberGeneratorUtilitiesImpl implements UniqueNumberGeneratorUtilities, Serializable {
    private static UniqueNumberGeneratorUtilities singleton;
    private int id = 0;

    /**
     * Private method to stop other classes from
     * instantiating this object.
     */
    private UniqueNumberGeneratorUtilitiesImpl() {
    }

    /**
     * Static method that allows people to reach the single
     * instance.
     *
     * @return the single instance.
     */
    public static UniqueNumberGeneratorUtilities getInstance() {
        if (singleton == null) {
            singleton = new UniqueNumberGeneratorUtilitiesImpl();
        }
        return singleton;
    }

    @Override
    public int getUniqueNumber() {
        return id++;
    }
}
