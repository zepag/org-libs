package org.org.usurper.dummydomain;

import java.sql.Timestamp;
import java.util.Date;

public interface IImmutableDummyVO {

    /**
     * @return the booleanField
     */
    public abstract Boolean getBooleanField();

    /**
     * @return the dateField
     */
    public abstract Date getDateField();

    /**
     * @return the doubleField
     */
    public abstract Double getDoubleField();

    /**
     * @return the floatField
     */
    public abstract Float getFloatField();

    /**
     * @return the integerField
     */
    public abstract Integer getIntegerField();

    /**
     * @return the longField
     */
    public abstract Long getLongField();

    /**
     * @return the primitiveBooleanField
     */
    public abstract boolean isPrimitiveBooleanField();

    /**
     * @return the primitiveByteField
     */
    public abstract byte getPrimitiveByteField();

    /**
     * @return the primitiveCharField
     */
    public abstract char getPrimitiveCharField();

    /**
     * @return the primitiveDoubleField
     */
    public abstract double getPrimitiveDoubleField();

    /**
     * @return the primitiveFloatField
     */
    public abstract float getPrimitiveFloatField();

    /**
     * @return the primitiveIntField
     */
    public abstract int getPrimitiveIntField();

    /**
     * @return the primitiveLongField
     */
    public abstract long getPrimitiveLongField();

    /**
     * @return the primitiveShortField
     */
    public abstract short getPrimitiveShortField();

    /**
     * @return the shortField
     */
    public abstract Short getShortField();

    /**
     * @return the stringField
     */
    public abstract String getStringField();

    public java.sql.Date getSqlDateField();

    public Timestamp getTimestampField();

}