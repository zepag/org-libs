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

package org.org.usurper;

/**
 * The Class UsurperException.
 * 
 * @author pagregoire
 */
public class UsurperException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -669882880114685543L;

    /**
     * Instantiates a new usurper exception.
     */
    public UsurperException() {
        super();

    }

    /**
     * The Constructor.
     * 
     * @param message the message
     */
    public UsurperException(String message) {
        super(message);

    }

    /**
     * The Constructor.
     * 
     * @param message the message
     * @param cause the cause
     */
    public UsurperException(String message, Throwable cause) {
        super(message, cause);

    }

    /**
     * The Constructor.
     * 
     * @param cause the cause
     */
    public UsurperException(Throwable cause) {
        super(cause);

    }
}
