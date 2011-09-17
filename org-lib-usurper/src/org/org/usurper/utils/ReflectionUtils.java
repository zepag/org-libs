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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * This class is a reflection utility class
 * 
 * @author pagregoire
 */
public final class ReflectionUtils {
    private ReflectionUtils() {
    }
    
    /**
     * Gets the generic types for a given constructor argument (specified by its ordering number).
     * 
     * @param targetConstructor the target constructor
     * @param constructorArgOrderingNumber the constructor arg ordering number
     * 
     * @return the generic types
     */
    public static Type[] getGenericTypes(Constructor<?> targetConstructor, Integer constructorArgOrderingNumber) {
        Type[] genericParameterTypes = targetConstructor.getGenericParameterTypes();
        ParameterizedType type = (ParameterizedType) genericParameterTypes[constructorArgOrderingNumber - 1];
        return type.getActualTypeArguments();
    }

    /**
     * Gets the generic types for a given field.
     * 
     * @param attribute the attribute
     * 
     * @return the generic types
     */
    public static Type[] getGenericTypes(Field attribute) {
        ParameterizedType type = (ParameterizedType) attribute.getGenericType();
        return type.getActualTypeArguments();
    }

    /**
     * This method gets the Field from an Object and an attribute name.
     * 
     * @param object
     * @param attributeName
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    public static Field getField(Object object, String attributeName) throws SecurityException, NoSuchFieldException {
        return object.getClass().getDeclaredField(attributeName);
    }

    /**
     * This method sets a property on an object
     * 
     * @param object
     * @param propertyName
     * @param value
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setProperty(Object object, String propertyName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = getField(object, propertyName);
        field.setAccessible(true);
        if (value.getClass().isArray()) {
            Object[] valueArray = ((Object[]) value);
            Object array = Array.newInstance(field.getType().getComponentType(), valueArray.length);
            for (int i = 0; i < valueArray.length; i++) {
                Array.set(array, i, ((Object[]) value)[i]);
            }
            field.set(object, array);
        } else {
            field.set(object, value);
        }
    }

    /**
     * This method invokes a property's setter on an object
     * 
     * @param object
     * @param propertyDescriptor
     * @param value
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void setProperty(Object object, Method setter, Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (setter != null) {
            if (value.getClass().isArray()) {
                Object[] valueArray = ((Object[]) value);
                Class<?> componentType = setter.getParameterTypes()[0].getComponentType();
                Object array = Array.newInstance(componentType, valueArray.length);
                for (int i = 0; i < valueArray.length; i++) {
                    Array.set(array, i, ((Object[]) value)[i]);
                }
                setter.invoke(object, array);
            } else {
                setter.invoke(object, value);
            }
        }
    }
    /**
     * Turns a primitive type to its Object counterpart.<br>
     * Just returns the passed type as-is if it is not primitive. 
     * @param usurpedClass
     *            the usurped class
     * 
     * @return the not primitive type
     */
    public static Class<?> toNotPrimitiveType(Class<?> usurpedClass) {
        Class<?> result = usurpedClass;
        if (usurpedClass.isPrimitive()) {
            if (usurpedClass == int.class) {
                result = Integer.class;
            } else if (usurpedClass == float.class) {
                result = Float.class;
            } else if (usurpedClass == long.class) {
                result = Long.class;
            } else if (usurpedClass == double.class) {
                result = Double.class;
            } else if (usurpedClass == short.class) {
                result = Short.class;
            } else if (usurpedClass == boolean.class) {
                result = Boolean.class;
            } else if (usurpedClass == byte.class) {
                result = Byte.class;
            } else if (usurpedClass == char.class) {
                result = Character.class;
            }
        }
        return result;
    }
}
