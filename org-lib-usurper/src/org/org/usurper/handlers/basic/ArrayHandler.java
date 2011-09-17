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
package org.org.usurper.handlers.basic;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.org.usurper.handlers.IHandler;
import org.org.usurper.handlers.exceptions.NoHandlerDefinedException;
import org.org.usurper.handlers.exceptions.PropertyTypeHandlingException;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.utils.ReflectionUtils;

/**
 * This is the generic handler for Arrays of types.
 * 
 * @author pagregoire
 */
public final class ArrayHandler implements IHandler {

   
    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledBeanProperty)
     */
    public Object handle(HandledBeanProperty handledBeanProperty) {
        Object result = null;
        final Integer ARRAY_ENTRIES = handledBeanProperty.getUsurperGeneratorSetup().getCountCallback().determineCount(handledBeanProperty);
        try {
            Field attribute = ReflectionUtils.getField(handledBeanProperty.getTargetObject(), handledBeanProperty.getPropertyName());
            Class<?> usurpedClass = attribute.getType().getComponentType();
            result = Array.newInstance(ReflectionUtils.toNotPrimitiveType(usurpedClass), ARRAY_ENTRIES);
            HandledBeanProperty handledArrayItem = new HandledBeanProperty(handledBeanProperty.getTargetObject(), usurpedClass, handledBeanProperty.getPropertyName(), handledBeanProperty.getUsurperGeneratorSetup());
            for (int i = 0; i < ARRAY_ENTRIES; i++) {
                if (handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)) != null) {
                    Array.set(result, i, handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)).handle(handledArrayItem));
                } else {
                    if (usurpedClass.isEnum()) {
                        Array.set(result, i, new EnumHandler().handle(handledArrayItem));
                    } else {
                        throw new NoHandlerDefinedException("no handler found for Array property <" + handledArrayItem.getPropertyName() + "> of Class <" + usurpedClass.getName() + ">.");
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            throw new PropertyTypeHandlingException("Unable to handle property <" + handledBeanProperty.getPropertyName() + "(" + handledBeanProperty.getPropertyClass().getName() + ")> from object " + handledBeanProperty.getTargetObject(), e);
        }
        return result;
    }

    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledConstructorArg)
     */
    public Object handle(HandledConstructorArg handledConstructorArg) {
        final Integer ARRAY_ENTRIES = handledConstructorArg.getUsurperGeneratorSetup().getCountCallback().determineCount(handledConstructorArg);

        Class<?> usurpedClass = handledConstructorArg.getConstructorArgClass().getComponentType();
        Object result = Array.newInstance(usurpedClass, ARRAY_ENTRIES);
        HandledConstructorArg handledArrayItem = new HandledConstructorArg(handledConstructorArg.getTargetConstructor(), usurpedClass, handledConstructorArg.getConstructorArgOrderingNumber(), handledConstructorArg.getUsurperGeneratorSetup());
        for (int i = 0; i < ARRAY_ENTRIES; i++) {
            if (handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)) != null) {
                Array.set(result, i, handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)).handle(handledArrayItem));
            } else {
                if (usurpedClass.isEnum()) {
                    Array.set(result, i, new EnumHandler().handle(handledArrayItem));
                } else {
                    throw new NoHandlerDefinedException("no handler found for Array constructor arg <#" + handledArrayItem.getConstructorArgOrderingNumber() + "> of Class <" + usurpedClass.getName() + ">.");
                }
            }
        }
        return result;
    }

}