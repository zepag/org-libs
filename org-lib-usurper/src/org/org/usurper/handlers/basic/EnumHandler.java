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

package org.org.usurper.handlers.basic;

import java.util.Random;

import org.org.usurper.handlers.IHandler;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;

/**
 * This type returns a random value of a given enum type.
 * 
 * @author pagregoire
 */
public final class EnumHandler implements IHandler {

    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledBeanProperty)
     */
    public Object handle(HandledBeanProperty handledBeanProperty) {
        return doHandle(handledBeanProperty.getPropertyClass());
    }

    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledConstructorArg)
     */
    public Object handle(HandledConstructorArg handledConstructorArg) {
        return doHandle(handledConstructorArg.getConstructorArgClass());
    }

    /**
     * Does the handling for a given class.
     * 
     * @param entityClass
     *            the entity class
     * 
     * @return the object
     */
    private Object doHandle(Class<?> entityClass) {
        Object[] enumValues = entityClass.getEnumConstants();
        int random = new Random().nextInt(enumValues.length);
        return enumValues[random];
    }

}
