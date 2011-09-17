package org.org.usurper.dummydomain;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author David Dossot (david@dossot.net)
 */
public class ImmutableDummyVO implements IImmutableDummyVO {
    public static final String SHORT_CONTRUCTOR = "shortContructor";

    private final Integer integerField;

    private final Float floatField;

    private final Double doubleField;

    private final String stringField;

    private final Date dateField;

    private final Long longField;

    private final Short shortField;

    private final Boolean booleanField;

    private final int primitiveIntField;

    private final short primitiveShortField;

    private final long primitiveLongField;

    private final boolean primitiveBooleanField;

    private final byte primitiveByteField;

    private final char primitiveCharField;

    private final float primitiveFloatField;

    private final double primitiveDoubleField;

    private final java.sql.Date sqlDateField;

    private final Timestamp timestampField;

    public ImmutableDummyVO(Integer integerField, Float floatField, Double doubleField, String stringField, Date dateField, Long longField, Short shortField, Boolean booleanField, int primitiveIntField, short primitiveShortField, long primitiveLongField, boolean primitiveBooleanField, byte primitiveByteField, char primitiveCharField, float primitiveFloatField, double primitiveDoubleField, Timestamp timestampField, java.sql.Date sqlDateField) {
        this.integerField = integerField;
        this.floatField = floatField;
        this.doubleField = doubleField;
        this.stringField = stringField;
        this.dateField = dateField;
        this.longField = longField;
        this.shortField = shortField;
        this.booleanField = booleanField;
        this.primitiveIntField = primitiveIntField;
        this.primitiveShortField = primitiveShortField;
        this.primitiveLongField = primitiveLongField;
        this.primitiveBooleanField = primitiveBooleanField;
        this.primitiveByteField = primitiveByteField;
        this.primitiveCharField = primitiveCharField;
        this.primitiveFloatField = primitiveFloatField;
        this.primitiveDoubleField = primitiveDoubleField;
        this.timestampField = timestampField;
        this.sqlDateField = sqlDateField;
    }

    public ImmutableDummyVO(Integer integerField) {
        this.integerField = integerField;
        this.floatField = 0f;
        this.doubleField = 0d;
        this.stringField = SHORT_CONTRUCTOR;
        this.dateField = new Date();
        this.longField = 0l;
        this.shortField = 0;
        this.booleanField = false;
        this.primitiveIntField = 0;
        this.primitiveShortField = 0;
        this.primitiveLongField = 0;
        this.primitiveBooleanField = false;
        this.primitiveByteField = 0;
        this.primitiveCharField = 0;
        this.primitiveFloatField = 0;
        this.primitiveDoubleField = 0;
        this.timestampField = new Timestamp(new Date().getTime());
        this.sqlDateField = new java.sql.Date(new Date().getTime());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getBooleanField()
     */
    public Boolean getBooleanField() {
        return booleanField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getDateField()
     */
    public Date getDateField() {
        return dateField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getDoubleField()
     */
    public Double getDoubleField() {
        return doubleField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getFloatField()
     */
    public Float getFloatField() {
        return floatField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getIntegerField()
     */
    public Integer getIntegerField() {
        return integerField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getLongField()
     */
    public Long getLongField() {
        return longField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#isPrimitiveBooleanField()
     */
    public boolean isPrimitiveBooleanField() {
        return primitiveBooleanField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getPrimitiveByteField()
     */
    public byte getPrimitiveByteField() {
        return primitiveByteField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getPrimitiveCharField()
     */
    public char getPrimitiveCharField() {
        return primitiveCharField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getPrimitiveDoubleField()
     */
    public double getPrimitiveDoubleField() {
        return primitiveDoubleField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getPrimitiveFloatField()
     */
    public float getPrimitiveFloatField() {
        return primitiveFloatField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getPrimitiveIntField()
     */
    public int getPrimitiveIntField() {
        return primitiveIntField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getPrimitiveLongField()
     */
    public long getPrimitiveLongField() {
        return primitiveLongField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getPrimitiveShortField()
     */
    public short getPrimitiveShortField() {
        return primitiveShortField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getShortField()
     */
    public Short getShortField() {
        return shortField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.org.usurper.IImmutableDummyVO#getStringField()
     */
    public String getStringField() {
        return stringField;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("primitiveDoubleField", this.primitiveDoubleField).append("floatField", this.floatField).append("shortField", this.shortField).append("primitiveFloatField", this.primitiveFloatField).append("primitiveCharField", this.primitiveCharField).append("primitiveLongField", this.primitiveLongField).append("integerField", this.integerField).append("stringField", this.stringField).append("doubleField", this.doubleField).append("longField", this.longField).append("booleanField", this.booleanField).append("primitiveBooleanField", this.primitiveBooleanField).append("primitiveIntField", this.primitiveIntField).append("dateField", this.dateField).append("primitiveShortField", this.primitiveShortField).append("primitiveByteField", this.primitiveByteField).toString();
    }

    public static String getSHORT_CONTRUCTOR() {
        return SHORT_CONTRUCTOR;
    }

    public java.sql.Date getSqlDateField() {
        return sqlDateField;
    }

    public Timestamp getTimestampField() {
        return timestampField;
    }
}
