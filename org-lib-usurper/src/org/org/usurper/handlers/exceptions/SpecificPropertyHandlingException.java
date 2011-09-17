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

package org.org.usurper.handlers.exceptions;

/**
 * The Class SpecificPropertyHandlingException.
 * 
 * @author pagregoire
 */
public class SpecificPropertyHandlingException extends RuntimeException {

    /** The Constant serialVersionUID. */

    /**
     * 
     */
    private static final long serialVersionUID = 3639034155033335343L;

    /**
     * Instantiates a new specific property handling exception.
     */
    public SpecificPropertyHandlingException() {
        super();

    }

    /**
     * Instantiates a new specific property handling exception.
     * 
     * @param message the message
     */
    public SpecificPropertyHandlingException(String message) {
        super(message);
    }

    /**
     * Instantiates a new specific property handling exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public SpecificPropertyHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new specific property handling exception.
     * 
     * @param cause the cause
     */
    public SpecificPropertyHandlingException(Throwable cause) {
        super(cause);
    }
}
