package org.org.usurper.dummydomain;

import java.math.BigInteger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pagregoire
 */
public class FcknVO {
    private BigInteger bigIntField;

    private BigInteger bigIntField2;

    public BigInteger getBigIntField2() {
        return bigIntField2;
    }

    public void setBigIntField2(BigInteger bigIntField2) {
        this.bigIntField2 = bigIntField2;
    }

    public BigInteger getBigIntField() {
        return bigIntField;
    }

    public void setBigIntField(BigInteger bigIntField) {
        this.bigIntField = bigIntField;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("bigIntField2", this.bigIntField2).append("bigIntField", this.bigIntField).toString();
    }

}
