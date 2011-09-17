package org.org.usurper.dummydomain;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pagregoire
 */

public class OtherDummyVO implements IDummyVO {
    private Integer integerField;
    private Float floatField;
    private Double doubleField;
    private String stringField;
    private Date dateField;
    private Long longField;
    private Short shortField;
    private Boolean booleanField;
    private Timestamp timestampField;
    private java.sql.Date sqlDateField;
    private int primitiveIntField;
    private short primitiveShortField;
    private long primitiveLongField;
    private boolean primitiveBooleanField;
    private byte primitiveByteField;
    private char primitiveCharField;
    private float primitiveFloatField;
    private double primitiveDoubleField;

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getBooleanField()
     */
    public Boolean getBooleanField() {
        return booleanField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setBooleanField(java.lang.Boolean)
     */
    public void setBooleanField(Boolean booleanField) {
        this.booleanField = booleanField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getDateField()
     */
    public Date getDateField() {
        return dateField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setDateField(java.util.Date)
     */
    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getDoubleField()
     */
    public Double getDoubleField() {
        return doubleField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setDoubleField(java.lang.Double)
     */
    public void setDoubleField(Double doubleField) {
        this.doubleField = doubleField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getFloatField()
     */
    public Float getFloatField() {
        return floatField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setFloatField(java.lang.Float)
     */
    public void setFloatField(Float floatField) {
        this.floatField = floatField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getIntegerField()
     */
    public Integer getIntegerField() {
        return integerField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setIntegerField(java.lang.Integer)
     */
    public void setIntegerField(Integer integerField) {
        this.integerField = integerField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getLongField()
     */
    public Long getLongField() {
        return longField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setLongField(java.lang.Long)
     */
    public void setLongField(Long longField) {
        this.longField = longField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#isPrimitiveBooleanField()
     */
    public boolean isPrimitiveBooleanField() {
        return primitiveBooleanField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveBooleanField(boolean)
     */
    public void setPrimitiveBooleanField(boolean primitiveBooleanField) {
        this.primitiveBooleanField = primitiveBooleanField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getPrimitiveByteField()
     */
    public byte getPrimitiveByteField() {
        return primitiveByteField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveByteField(byte)
     */
    public void setPrimitiveByteField(byte primitiveByteField) {
        this.primitiveByteField = primitiveByteField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getPrimitiveCharField()
     */
    public char getPrimitiveCharField() {
        return primitiveCharField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveCharField(char)
     */
    public void setPrimitiveCharField(char primitiveCharField) {
        this.primitiveCharField = primitiveCharField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getPrimitiveDoubleField()
     */
    public double getPrimitiveDoubleField() {
        return primitiveDoubleField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveDoubleField(double)
     */
    public void setPrimitiveDoubleField(double primitiveDoubleField) {
        this.primitiveDoubleField = primitiveDoubleField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getPrimitiveFloatField()
     */
    public float getPrimitiveFloatField() {
        return primitiveFloatField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveFloatField(float)
     */
    public void setPrimitiveFloatField(float primitiveFloatField) {
        this.primitiveFloatField = primitiveFloatField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getPrimitiveIntField()
     */
    public int getPrimitiveIntField() {
        return primitiveIntField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveIntField(int)
     */
    public void setPrimitiveIntField(int primitiveIntField) {
        this.primitiveIntField = primitiveIntField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getPrimitiveLongField()
     */
    public long getPrimitiveLongField() {
        return primitiveLongField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveLongField(long)
     */
    public void setPrimitiveLongField(long primitiveLongField) {
        this.primitiveLongField = primitiveLongField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getPrimitiveShortField()
     */
    public short getPrimitiveShortField() {
        return primitiveShortField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setPrimitiveShortField(short)
     */
    public void setPrimitiveShortField(short primitiveShortField) {
        this.primitiveShortField = primitiveShortField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getShortField()
     */
    public Short getShortField() {
        return shortField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setShortField(java.lang.Short)
     */
    public void setShortField(Short shortField) {
        this.shortField = shortField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#getStringField()
     */
    public String getStringField() {
        return stringField;
    }

    /**
     * @see org.org.usurper.dummydomain.IDummyVO#setStringField(java.lang.String)
     */
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("primitiveDoubleField", this.primitiveDoubleField).append("floatField", this.floatField).append("shortField", this.shortField).append("primitiveFloatField", this.primitiveFloatField).append("primitiveCharField", this.primitiveCharField).append("primitiveLongField", this.primitiveLongField).append("integerField", this.integerField).append("stringField", this.stringField).append("doubleField", this.doubleField).append("longField", this.longField).append("booleanField", this.booleanField).append("primitiveBooleanField", this.primitiveBooleanField).append("primitiveIntField", this.primitiveIntField).append("dateField", this.dateField).append("primitiveShortField", this.primitiveShortField).append("primitiveByteField", this.primitiveByteField).toString();
    }

    public java.sql.Date getSqlDateField() {
        return sqlDateField;
    }

    public void setSqlDateField(java.sql.Date sqlDateField) {
        this.sqlDateField = sqlDateField;
    }

    public Timestamp getTimestampField() {
        return timestampField;
    }

    public void setTimestampField(Timestamp timestampField) {
        this.timestampField = timestampField;
    }
}
