package org.org.usurper.dummydomain;

import java.util.Date;

/**
 * @author David Dossot (david@dossot.net)
 */
public class PartiallyImmutableDummyVO {
    private final Integer integerField;
    private Float floatField;
    private final Double doubleField;
    private String stringField;
    private final Date dateField;
    private Long longField;
    private final Short shortField;
    private Boolean booleanField;
    private final int primitiveIntField;
    private short primitiveShortField;
    private final long primitiveLongField;
    private boolean primitiveBooleanField;
    private final byte primitiveByteField;
    private char primitiveCharField;
    private final float primitiveFloatField;
    private double primitiveDoubleField;

    public PartiallyImmutableDummyVO(Integer integerField, Double doubleField, Date dateField, Short shortField, int primitiveIntField, long primitiveLongField, byte primitiveByteField, float primitiveFloatField) {
        this.integerField = integerField;
        this.doubleField = doubleField;
        this.dateField = dateField;
        this.shortField = shortField;
        this.primitiveIntField = primitiveIntField;
        this.primitiveLongField = primitiveLongField;
        this.primitiveByteField = primitiveByteField;
        this.primitiveFloatField = primitiveFloatField;
    }

    /**
     * @return the booleanField
     */
    public Boolean getBooleanField() {
        return booleanField;
    }

    /**
     * @param booleanField
     *            the booleanField to set
     */
    public void setBooleanField(Boolean booleanField) {
        this.booleanField = booleanField;
    }

    /**
     * @return the dateField
     */
    public Date getDateField() {
        return dateField;
    }

    /**
     * @return the doubleField
     */
    public Double getDoubleField() {
        return doubleField;
    }

    /**
     * @return the floatField
     */
    public Float getFloatField() {
        return floatField;
    }

    /**
     * @param floatField
     *            the floatField to set
     */
    public void setFloatField(Float floatField) {
        this.floatField = floatField;
    }

    /**
     * @return the integerField
     */
    public Integer getIntegerField() {
        return integerField;
    }

    /**
     * @return the longField
     */
    public Long getLongField() {
        return longField;
    }

    /**
     * @param longField
     *            the longField to set
     */
    public void setLongField(Long longField) {
        this.longField = longField;
    }

    /**
     * @return the primitiveBooleanField
     */
    public boolean isPrimitiveBooleanField() {
        return primitiveBooleanField;
    }

    /**
     * @param primitiveBooleanField
     *            the primitiveBooleanField to set
     */
    public void setPrimitiveBooleanField(boolean primitiveBooleanField) {
        this.primitiveBooleanField = primitiveBooleanField;
    }

    /**
     * @return the primitiveByteField
     */
    public byte getPrimitiveByteField() {
        return primitiveByteField;
    }

    /**
     * @return the primitiveCharField
     */
    public char getPrimitiveCharField() {
        return primitiveCharField;
    }

    /**
     * @param primitiveCharField
     *            the primitiveCharField to set
     */
    public void setPrimitiveCharField(char primitiveCharField) {
        this.primitiveCharField = primitiveCharField;
    }

    /**
     * @return the primitiveDoubleField
     */
    public double getPrimitiveDoubleField() {
        return primitiveDoubleField;
    }

    /**
     * @param primitiveDoubleField
     *            the primitiveDoubleField to set
     */
    public void setPrimitiveDoubleField(double primitiveDoubleField) {
        this.primitiveDoubleField = primitiveDoubleField;
    }

    /**
     * @return the primitiveFloatField
     */
    public float getPrimitiveFloatField() {
        return primitiveFloatField;
    }

    /**
     * @return the primitiveIntField
     */
    public int getPrimitiveIntField() {
        return primitiveIntField;
    }

    /**
     * @return the primitiveLongField
     */
    public long getPrimitiveLongField() {
        return primitiveLongField;
    }

    /**
     * @return the primitiveShortField
     */
    public short getPrimitiveShortField() {
        return primitiveShortField;
    }

    /**
     * @param primitiveShortField
     *            the primitiveShortField to set
     */
    public void setPrimitiveShortField(short primitiveShortField) {
        this.primitiveShortField = primitiveShortField;
    }

    /**
     * @return the shortField
     */
    public Short getShortField() {
        return shortField;
    }

    /**
     * @return the stringField
     */
    public String getStringField() {
        return stringField;
    }

    /**
     * @param stringField
     *            the stringField to set
     */
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

}
