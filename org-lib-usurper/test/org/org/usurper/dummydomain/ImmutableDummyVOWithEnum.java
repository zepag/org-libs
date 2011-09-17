package org.org.usurper.dummydomain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author David Dossot (david@dossot.net)
 */
public class ImmutableDummyVOWithEnum {
    public enum EnumProperty {
        VALUE1, VALUE2, VALUE3, VALUE4
    }

    private final EnumProperty enumProperty;

    public ImmutableDummyVOWithEnum(EnumProperty enumProperty) {
        this.enumProperty = enumProperty;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("enumProperty", this.enumProperty).toString();
    }

    public EnumProperty getEnumProperty() {
        return enumProperty;
    }
}
