package org.org.usurper.dummydomain;

import java.math.BigDecimal;
import java.util.Set;

import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;

public class BigDecimalHandler extends AbstractPropertyTypeHandler {

    public BigDecimalHandler(PropertyTypeDefinition... properTypeDefinitions) {
        super(properTypeDefinitions);
    }

    public BigDecimalHandler(Class<?>... handledTypes) {
        super(handledTypes);
    }

    public BigDecimalHandler(Set<PropertyTypeDefinition> handledTypes) {
        super(handledTypes);
    }

    private static final BigDecimal BIG_DECIMAL_LUCKY_VALUE = BigDecimal.valueOf(13);

    public Object handle(HandledBeanProperty handledBeanProperty) {
        return BIG_DECIMAL_LUCKY_VALUE;
    }

    public Object handle(HandledConstructorArg handledConstructorArg) {
        return BIG_DECIMAL_LUCKY_VALUE;
    }

}
