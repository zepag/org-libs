/*
 ORG Usurper is a random value object generator library 
 Copyright (C) 2007  Pierre-Antoine Grégoire
 
 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.
 
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.
 
 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.org.usurper.utils;

import java.util.Date;

/**
 * The Class RandomUtils provides a series of simple Random generators.<br>
 * It is freely and obviously inspired by code from commons-lang.
 */
public class RandomUtils {

    @SuppressWarnings("unused")
    private RandomUtils() {
    }

    /**
     * Next boolean.
     * 
     * @return the boolean
     */
    public static Boolean nextBoolean() {
        return Boolean.valueOf(Math.random() > 0.5);
    }

    /**
     * Next byte.
     * 
     * @return the byte
     */
    public static Byte nextByte() {
        return Byte.valueOf(("" + Math.random() * Integer.MAX_VALUE).substring(0, 1));
    }

    public static Integer nextInteger() {
        return Integer.valueOf((int) Math.random() * Integer.MAX_VALUE);
    }

    /**
     * Next character.
     * 
     * @return the character
     */
    public static Character nextCharacter() {
        int end = 'z' + 1;
        int start = ' ';

        StringBuilder buffer = new StringBuilder();
        int gap = end - start;

        buffer.append((char) ((int) (Math.random() * gap) + start));
        return Character.valueOf((buffer.toString().charAt(0)));
    }

    /**
     * Next character.
     * 
     * @return the character
     */
    public static String nextString(Integer length) {
        int end = 'z' + 1;
        int start = ' ';

        StringBuilder buffer = new StringBuilder();
        int gap = end - start;
        for (int i = 0; i < length; i++) {
            char ch = (char) ((int) (Math.random() * gap) + start);
            if (Character.isLetter(ch)) {
                buffer.append(ch);
            } else {
                i--;
            }
        }

        return buffer.toString();
    }

    public static Long nextLong() {
        return Long.valueOf((long) (Math.random() * Long.MAX_VALUE));
    }

    public static Date nextDate() {
        long date = RandomUtils.nextLong();
        long maxDate = new Date().getTime();
        return new Date(date > maxDate ? maxDate : date);
    }

    public static Short nextShort() {
        return Short.valueOf((short) (Math.random() * Short.MAX_VALUE));
    }

    public static Double nextDouble() {
        return Double.valueOf((double) (Math.random() * Double.MAX_VALUE));
    }
}