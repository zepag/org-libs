package org.org.usurper.dummydomain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.org.usurper.dummydomain.DummyVOWithEnum.EnumProperty;

/**
 * @author pagregoire
 */
public class DummyVOWithArrays {

    private Integer[] integerArray;

    private int[] intArray;
    private short[] shortArray;
    private long[] longArray;
    private boolean[] booleanArray;
    private byte[] byteArray;
    private char[] charArray;
    private float[] floatArray;
    private double[] doubleArray;

    private EnumProperty[] enumProperties;

    public EnumProperty[] getEnumProperties() {
        return this.enumProperties;
    }

    public void setEnumProperties(EnumProperty[] enumProperties) {
        this.enumProperties = enumProperties;
    }

    /**
     * @return Returns the intArray.
     */
    public int[] getIntArray() {
        return this.intArray;
    }

    /**
     * @param intArray
     *            The intArray to set.
     */
    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    /**
     * @return Returns the integerArray.
     */
    public Integer[] getIntegerArray() {
        return this.integerArray;
    }

    /**
     * @param integerArray
     *            The integerArray to set.
     */
    public void setIntegerArray(Integer[] integerArray) {
        this.integerArray = integerArray;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("intArray", this.intArray).append("enumProperties", this.enumProperties).append("integerArray", this.integerArray).toString();
    }

    public boolean[] getBooleanArray() {
        return booleanArray;
    }

    public void setBooleanArray(boolean[] booleanArray) {
        this.booleanArray = booleanArray;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public char[] getCharArray() {
        return charArray;
    }

    public void setCharArray(char[] charArray) {
        this.charArray = charArray;
    }

    public double[] getDoubleArray() {
        return doubleArray;
    }

    public void setDoubleArray(double[] doubleArray) {
        this.doubleArray = doubleArray;
    }

    public float[] getFloatArray() {
        return floatArray;
    }

    public void setFloatArray(float[] floatArray) {
        this.floatArray = floatArray;
    }

    public long[] getLongArray() {
        return longArray;
    }

    public void setLongArray(long[] longArray) {
        this.longArray = longArray;
    }

    public short[] getShortArray() {
        return shortArray;
    }

    public void setShortArray(short[] shortArray) {
        this.shortArray = shortArray;
    }

}