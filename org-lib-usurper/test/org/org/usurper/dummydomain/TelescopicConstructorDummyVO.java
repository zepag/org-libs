package org.org.usurper.dummydomain;

import java.io.InputStream;

/**
 * @author David Dossot (david@dossot.net)
 */
public class TelescopicConstructorDummyVO {

    private final InputStream inputStream;

    private final String string;

    private final Integer integer;

    public TelescopicConstructorDummyVO(InputStream inputStream) {
        this(inputStream, null, null);
    }

    public TelescopicConstructorDummyVO(String string, Integer integer) {
        this(null, string, integer);
    }

    public TelescopicConstructorDummyVO(InputStream inputStream, String string, Integer integer) {
        this.inputStream = inputStream;
        this.string = string;
        this.integer = integer;
    }

    /**
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * @return the integer
     */
    public Integer getInteger() {
        return integer;
    }

    /**
     * @return the string
     */
    public String getString() {
        return string;
    }

}
