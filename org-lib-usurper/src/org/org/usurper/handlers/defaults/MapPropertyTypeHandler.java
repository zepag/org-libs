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
package org.org.usurper.handlers.defaults;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.EnumHandler;
import org.org.usurper.handlers.exceptions.NoHandlerDefinedException;
import org.org.usurper.handlers.exceptions.PropertyTypeHandlingException;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.utils.ReflectionUtils;

/**
 * This handler deals with Map Types.<br>
 * The Map must be a generic type (Usurper cannot generate Maps of objects without knowing about the types to be generated) and must be declared using the Map interface.<br>
 * This handler then delegates to the handlers defined in the generator setup for the proper generation.<br>
 * It also delegates to the CountCallback defined in the generator setup to determine the number of key/value pairs the Map will contain.
 * 
 * @author pagregoire
 */
public class MapPropertyTypeHandler extends AbstractPropertyTypeHandler {

    private final static Integer KEY_TYPE_ARGUMENTS_INDEX = 0;

    private final static Integer VALUE_TYPE_ARGUMENTS_INDEX = 1;

    private static Set<PropertyTypeDefinition> handledTypes;

    static {
        handledTypes = new HashSet<PropertyTypeDefinition>();
        handledTypes.add(new PropertyTypeDefinition(Map.class));
    }

    /**
     * Instantiates a new map property type handler.
     */
    public MapPropertyTypeHandler() {
        super(handledTypes);
    }

    /**
     * @see org.org.usurper.handlers.basic.AbstractPropertyTypeHandler#handle()
     */
    @SuppressWarnings("unchecked")
    public Object handle(HandledBeanProperty handledBeanProperty) {
        Map result = null;
        final Integer MAP_ENTRIES = handledBeanProperty.getUsurperGeneratorSetup().getCountCallback().determineCount(handledBeanProperty);
        try {
            Field attribute = ReflectionUtils.getField(handledBeanProperty.getTargetObject(), handledBeanProperty.getPropertyName());
            Type[] usurpedClasses = ReflectionUtils.getGenericTypes(attribute);
            Class keyGenericType = (Class) usurpedClasses[KEY_TYPE_ARGUMENTS_INDEX];
            Class valueGenericType = (Class) usurpedClasses[VALUE_TYPE_ARGUMENTS_INDEX];
            int entryNumber = MAP_ENTRIES;
            result = new LinkedHashMap(entryNumber);
            HandledBeanProperty handledMapKeyItem = new HandledBeanProperty(handledBeanProperty.getTargetObject(), keyGenericType, handledBeanProperty.getPropertyName(), handledBeanProperty.getUsurperGeneratorSetup());
            HandledBeanProperty handledMapValueItem = new HandledBeanProperty(handledBeanProperty.getTargetObject(), valueGenericType, handledBeanProperty.getPropertyName(), handledBeanProperty.getUsurperGeneratorSetup());
            for (int i = 0; i < entryNumber; i++) {
                Object key = null;
                Object value = null;
                if (handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(keyGenericType)) != null) {
                    key = handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(keyGenericType)).handle(handledMapKeyItem);
                } else {
                    if (keyGenericType.isEnum()) {
                        key = new EnumHandler().handle(handledMapKeyItem);
                    } else {
                        throw new NoHandlerDefinedException("no handler found for Map attribute <" + attribute.getName() + "> of Class <" + keyGenericType.getName() + ">.");
                    }
                }
                if (handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(valueGenericType)) != null) {
                    value = handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(valueGenericType)).handle(handledMapValueItem);
                } else {
                    if (valueGenericType.isEnum()) {
                        value = new EnumHandler().handle(handledMapValueItem);
                    } else {
                        throw new NoHandlerDefinedException("no handler found for Map attribute <" + attribute.getName() + "> of Class <" + valueGenericType.getName() + ">.");
                    }
                }
                result.put(key, value);
            }
        } catch (NoSuchFieldException e) {
            throw new PropertyTypeHandlingException("Unable to handle field <" + handledBeanProperty.getPropertyName() + "(" + handledBeanProperty.getPropertyClass().getName() + ")> from object " + handledBeanProperty.getTargetObject(), e);
        }
        return result;
    }

    /**
     * @see org.org.usurper.handlers.basic.AbstractPropertyTypeHandler#handle()
     */
    @SuppressWarnings("unchecked")
    public Object handle(HandledConstructorArg handledConstructorArg) {
        Map result = null;
        final Integer MAP_ENTRIES = handledConstructorArg.getUsurperGeneratorSetup().getCountCallback().determineCount(handledConstructorArg);
        Type[] usurpedClasses = ReflectionUtils.getGenericTypes(handledConstructorArg.getTargetConstructor(), handledConstructorArg.getConstructorArgOrderingNumber());
        Class keyGenericType = (Class) usurpedClasses[KEY_TYPE_ARGUMENTS_INDEX];
        Class valueGenericType = (Class) usurpedClasses[VALUE_TYPE_ARGUMENTS_INDEX];
        int entryNumber = MAP_ENTRIES;
        result = new LinkedHashMap(entryNumber);
        HandledConstructorArg handledMapKeyItem = new HandledConstructorArg(handledConstructorArg.getTargetConstructor(), keyGenericType, handledConstructorArg.getConstructorArgOrderingNumber(), handledConstructorArg.getUsurperGeneratorSetup());
        HandledConstructorArg handledMapValueItem = new HandledConstructorArg(handledConstructorArg.getTargetConstructor(), valueGenericType, handledConstructorArg.getConstructorArgOrderingNumber(), handledConstructorArg.getUsurperGeneratorSetup());
        for (int i = 0; i < entryNumber; i++) {
            Object key = null;
            Object value = null;
            if (handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(keyGenericType)) != null) {
                key = handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(keyGenericType)).handle(handledMapKeyItem);
            } else {
                if (keyGenericType.isEnum()) {
                    key = new EnumHandler().handle(handledMapKeyItem);
                } else {
                    throw new NoHandlerDefinedException("no handler found for Map Key's Class <" + new PropertyTypeDefinition(keyGenericType) + ">.");
                }
            }
            if (handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(valueGenericType)) != null) {
                value = handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(valueGenericType)).handle(handledMapValueItem);
            } else {
                if (valueGenericType.isEnum()) {
                    value = new EnumHandler().handle(handledMapValueItem);
                } else {
                    throw new NoHandlerDefinedException("no handler found for Map Value's Class  <" + new PropertyTypeDefinition(valueGenericType) + ">.");
                }
            }
            result.put(key, value);
        }

        return result;
    }
}