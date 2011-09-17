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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.exceptions.NoHandlerDefinedException;
import org.org.usurper.handlers.exceptions.PropertyTypeHandlingException;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.utils.ReflectionUtils;

/**
 * This handler deals with Lists and Sets of Types.<br>
 * These Lists and Sets must be generic types (Usurper cannot generate Lists of objects without knowing about the type to be generated) and must be declared using the List and Set interface.<br>
 * This handler then delegates to the handlers defined in the generator setup for the proper generation.<br>
 * It also delegates to the CountCallback defined in the generator setup to determine the number of elements the List or Set will contain.
 * 
 * @author pagregoire
 */
public class ListAndSetPropertyTypeHandler extends AbstractPropertyTypeHandler {

    private final static Integer NUMBER_OF_TYPE_ARGUMENTS = 1;

    private static Set<PropertyTypeDefinition> handledTypes;

    static {
        handledTypes = new HashSet<PropertyTypeDefinition>();
        handledTypes.add(new PropertyTypeDefinition(List.class));
        handledTypes.add(new PropertyTypeDefinition(Set.class));
    }

    /**
     * Instantiates a new list and set property type handler.
     */
    public ListAndSetPropertyTypeHandler() {
        super(handledTypes);
    }

    /**
     * @see org.org.usurper.handlers.basic.AbstractPropertyTypeHandler#handle()
     */
    @SuppressWarnings("unchecked")
    public Object handle(HandledBeanProperty handledBeanProperty) {
        Collection result = null;
        final Integer COLLECTION_ENTRIES = handledBeanProperty.getUsurperGeneratorSetup().getCountCallback().determineCount(handledBeanProperty);
        try {
            Field attribute = ReflectionUtils.getField(handledBeanProperty.getTargetObject(), handledBeanProperty.getPropertyName());
            Class<?> usurpedClass = Object.class;
            Class<?> entityClass = attribute.getType();
            usurpedClass = (Class) ReflectionUtils.getGenericTypes(attribute)[NUMBER_OF_TYPE_ARGUMENTS - 1];
            if (entityClass.isAssignableFrom(List.class)) {
                result = new ArrayList(COLLECTION_ENTRIES);
            } else if (entityClass.isAssignableFrom(Set.class)) {
                result = new HashSet(COLLECTION_ENTRIES);
            }
            HandledBeanProperty handledCollectionItem = new HandledBeanProperty(handledBeanProperty.getTargetObject(), usurpedClass, handledBeanProperty.getPropertyName(), handledBeanProperty.getUsurperGeneratorSetup());
            for (int i = 0; i < COLLECTION_ENTRIES; i++) {
                if (handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)) != null) {
                    result.add(handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)).handle(handledCollectionItem));
                } else {
                    if (usurpedClass.isEnum()) {
                        result.add(handledBeanProperty.getUsurperGeneratorSetup().getEnumHandler().handle(handledCollectionItem));
                    } else {
                        throw new NoHandlerDefinedException("no handler found for Class <" + new PropertyTypeDefinition(usurpedClass) + "> of List / Set.");
                    }
                }
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
        Collection result = null;
        final Integer COLLECTION_ENTRIES = handledConstructorArg.getUsurperGeneratorSetup().getCountCallback().determineCount(handledConstructorArg);
        Class<?> collectionClass = handledConstructorArg.getConstructorArgClass();
        Class<?> usurpedClass = (Class<?>) ReflectionUtils.getGenericTypes(handledConstructorArg.getTargetConstructor(), handledConstructorArg.getConstructorArgOrderingNumber())[NUMBER_OF_TYPE_ARGUMENTS - 1];
        if (collectionClass.isAssignableFrom(List.class)) {
            result = new ArrayList(COLLECTION_ENTRIES);
        } else if (collectionClass.isAssignableFrom(Set.class)) {
            result = new HashSet(COLLECTION_ENTRIES);
        }
        HandledConstructorArg handledCollectionItem = new HandledConstructorArg(handledConstructorArg.getTargetConstructor(), usurpedClass, handledConstructorArg.getConstructorArgOrderingNumber(), handledConstructorArg.getUsurperGeneratorSetup());
        for (int i = 0; i < COLLECTION_ENTRIES; i++) {
            if (handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)) != null) {
                result.add(handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers().get(new PropertyTypeDefinition(usurpedClass)).handle(handledCollectionItem));
            } else {
                if (usurpedClass.isEnum()) {
                    result.add(handledConstructorArg.getUsurperGeneratorSetup().getEnumHandler().handle(handledCollectionItem));
                } else {
                    throw new NoHandlerDefinedException("no handler found for Class <" + usurpedClass.getName() + "> of List / Set.");
                }
            }
        }
        return result;
    }

}