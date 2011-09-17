package org.org.usurper.dummydomain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pagregoire
 */

public class DummyVOWithEnum {
    public enum EnumProperty {
        VALUE1, VALUE2, VALUE3, VALUE4
    }

    public enum EnumProperty2 {
        VALUE5, VALUE6, VALUE7, VALUE8
    }

    private EnumProperty enumProperty;

    public EnumProperty getEnumProperty() {
        return this.enumProperty;
    }

    public void setEnumProperty(EnumProperty enumProperty) {
        this.enumProperty = enumProperty;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("enumProperty", this.enumProperty).toString();
    }
}
