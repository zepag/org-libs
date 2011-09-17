package org.org.usurper.dummydomain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pagregoire
 */
public class DummyWithChildVO {
    private SonOfDummyWithChildVO sonOfDummyVO;

    private Integer integerField;

    private Float floatField;

    private Double doubleField;

    private String stringField;

    private Date dateField;

    private Long longField;

    private Short shortField;

    private Boolean booleanField;

    private int primitiveIntField;

    private short primitiveShortField;

    private long primitiveLongField;

    private boolean primitiveBooleanField;

    private byte primitiveByteField;

    private char primitiveCharField;

    private float primitiveFloatField;

    private double primitiveDoubleField;

    public Boolean getBooleanField() {
        return booleanField;
    }

    public void setBooleanField(Boolean booleanField) {
        this.booleanField = booleanField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public Double getDoubleField() {
        return doubleField;
    }

    public void setDoubleField(Double doubleField) {
        this.doubleField = doubleField;
    }

    public Float getFloatField() {
        return floatField;
    }

    public void setFloatField(Float floatField) {
        this.floatField = floatField;
    }

    public Integer getIntegerField() {
        return integerField;
    }

    public void setIntegerField(Integer integerField) {
        this.integerField = integerField;
    }

    public Long getLongField() {
        return longField;
    }

    public void setLongField(Long longField) {
        this.longField = longField;
    }

    public boolean isPrimitiveBooleanField() {
        return primitiveBooleanField;
    }

    public void setPrimitiveBooleanField(boolean primitiveBooleanField) {
        this.primitiveBooleanField = primitiveBooleanField;
    }

    public byte getPrimitiveByteField() {
        return primitiveByteField;
    }

    public void setPrimitiveByteField(byte primitiveByteField) {
        this.primitiveByteField = primitiveByteField;
    }

    public char getPrimitiveCharField() {
        return primitiveCharField;
    }

    public void setPrimitiveCharField(char primitiveCharField) {
        this.primitiveCharField = primitiveCharField;
    }

    public double getPrimitiveDoubleField() {
        return primitiveDoubleField;
    }

    public void setPrimitiveDoubleField(double primitiveDoubleField) {
        this.primitiveDoubleField = primitiveDoubleField;
    }

    public float getPrimitiveFloatField() {
        return primitiveFloatField;
    }

    public void setPrimitiveFloatField(float primitiveFloatField) {
        this.primitiveFloatField = primitiveFloatField;
    }

    public int getPrimitiveIntField() {
        return primitiveIntField;
    }

    public void setPrimitiveIntField(int primitiveIntField) {
        this.primitiveIntField = primitiveIntField;
    }

    public long getPrimitiveLongField() {
        return primitiveLongField;
    }

    public void setPrimitiveLongField(long primitiveLongField) {
        this.primitiveLongField = primitiveLongField;
    }

    public short getPrimitiveShortField() {
        return primitiveShortField;
    }

    public void setPrimitiveShortField(short primitiveShortField) {
        this.primitiveShortField = primitiveShortField;
    }

    public Short getShortField() {
        return shortField;
    }

    public void setShortField(Short shortField) {
        this.shortField = shortField;
    }

    public SonOfDummyWithChildVO getSonOfDummyVO() {
        return sonOfDummyVO;
    }

    public void setSonOfDummyVO(SonOfDummyWithChildVO sonOfDummyVO) {
        this.sonOfDummyVO = sonOfDummyVO;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("primitiveDoubleField", this.primitiveDoubleField).append("floatField", this.floatField).append("shortField", this.shortField).append("primitiveFloatField", this.primitiveFloatField).append("primitiveCharField", this.primitiveCharField).append("primitiveLongField", this.primitiveLongField).append("integerField", this.integerField).append("stringField", this.stringField).append("sonOfDummyVO", this.sonOfDummyVO).append("doubleField", this.doubleField).append("longField", this.longField).append("booleanField", this.booleanField).append("primitiveBooleanField", this.primitiveBooleanField).append("primitiveIntField", this.primitiveIntField).append("dateField", this.dateField).append("primitiveShortField", this.primitiveShortField).append("primitiveByteField", this.primitiveByteField).toString();
    }

}
