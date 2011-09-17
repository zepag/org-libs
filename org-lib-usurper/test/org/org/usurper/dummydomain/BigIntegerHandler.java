package org.org.usurper.dummydomain;

import java.math.BigInteger;
import java.util.Set;

import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;

public class BigIntegerHandler extends AbstractPropertyTypeHandler {

    public BigIntegerHandler(PropertyTypeDefinition... properTypeDefinitions) {
        super(properTypeDefinitions);
    }

    public BigIntegerHandler(Class<?>... handledTypes) {
        super(handledTypes);
    }

    public BigIntegerHandler(Set<PropertyTypeDefinition> handledTypes) {
        super(handledTypes);
    }

    private static final BigInteger BIG_DECIMAL_LUCKY_VALUE = BigInteger.valueOf(13);

    public Object handle(HandledBeanProperty handledBeanProperty) {
        return BIG_DECIMAL_LUCKY_VALUE;
    }

    public Object handle(HandledConstructorArg handledConstructorArg) {
        return BIG_DECIMAL_LUCKY_VALUE;
    }

}
