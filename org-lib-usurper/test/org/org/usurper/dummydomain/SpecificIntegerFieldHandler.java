package org.org.usurper.dummydomain;

import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.SpecificPropertyDefinition;

public class SpecificIntegerFieldHandler extends AbstractSpecificPropertyHandler {

    public SpecificIntegerFieldHandler(SpecificPropertyDefinition specificPropertyDefinition) {
        super(specificPropertyDefinition);
    }

    private static final int LUCKY_VALUE = 13;

    public Object handle(HandledBeanProperty handledBeanProperty) {
        return LUCKY_VALUE;
    }

    public Object handle(HandledConstructorArg handledConstructorArg) {
        return LUCKY_VALUE;
    }

}
