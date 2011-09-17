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

/**
 * 
 */
package org.org.usurper.handlers;

import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;

/**
 * This interface defines the contract for all the handlers.<br>
 * NOT SUPPOSED TO BE DIRECTLY IMPLEMENTED, PREFER THE IMPLEMENTATION OF ITS ABSTRACT BASIC SUBCLASSES.
 * @author pagregoire
 */
public interface IHandler {
    /**
     * This method allows the generation of a properties' value.
     * 
     * @param handledBeanProperty
     *            The bean property's info
     * @return the generated value.
     * @see HandledBeanProperty
     */
    public Object handle(HandledBeanProperty handledBeanProperty);

    /**
     * This method allows the generation of a constructor argument.
     * 
     * @param handledConstructorArg
     *            The constructor's arg's info
     * @return the generated value.
     * @see HandledConstructorArg
     */
    public Object handle(HandledConstructorArg handledConstructorArg);
}