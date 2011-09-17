package org.org.usurper.dummydomain;

import org.org.usurper.model.IHandledEntity;
import org.org.usurper.setup.ICountCallback;

public class DummyCountCallback implements ICountCallback {

    public static final int LUCKY_VALUE = 13;

    public Integer determineCount(IHandledEntity handledEntity) {
        return LUCKY_VALUE;
    }

}
