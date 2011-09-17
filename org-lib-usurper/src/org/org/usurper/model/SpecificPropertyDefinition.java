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
package org.org.usurper.model;

/**
 * The Class SpecificPropertyDefinition is an immutable value object describing a target specific property.
 */
public class SpecificPropertyDefinition implements ITargetDefinition {

    public static final String CLASS_PROPERTY_SEPARATOR = ".";

    private final Class<?> targetClass;
    private final String targetProperty;

    /**
     * Instantiates a new SpecificPropertyDefinition from a Class object and a property name.
     * 
     * @param targetClass
     *            the target class
     * @param targetProperty
     *            the target property
     */
    public SpecificPropertyDefinition(final Class<?> targetClass, final String targetProperty) {
        super();
        this.targetClass = targetClass;
        this.targetProperty = targetProperty;
    }

    /**
     * Instantiates a new SpecificPropertyDefinition from a String description.<br>
     * This String description is made of the class name and a dot ".".<br>
     * example:<br>
     * org.org.usurper.DummyVO.propertyToBeSet where propertyToBeSet is property of the class org.org.usurper.DummyVO.
     * 
     * @param targetPropertyDescription
     *            the target property description
     */
    public SpecificPropertyDefinition(final String targetPropertyDescription) {
        super();
        String targetClassName = targetPropertyDescription.substring(0, targetPropertyDescription.lastIndexOf(CLASS_PROPERTY_SEPARATOR));
        try {
            this.targetClass = Class.forName(targetClassName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Impossible to resolve class in: " + targetPropertyDescription, e);
        }

        String targetPropertyName = targetPropertyDescription.substring(targetPropertyDescription.lastIndexOf(CLASS_PROPERTY_SEPARATOR) + 1, targetPropertyDescription.length());
        this.targetProperty = targetPropertyName;
    }

    /**
     * Gets the target class.
     * 
     * @return the target class
     */
    public Class<?> getTargetClass() {
        return targetClass;
    }

    /**
     * Gets the target property.
     * 
     * @return the target property
     */
    public String getTargetProperty() {
        return targetProperty;
    }

    /**
     * Gets the property path string.
     * 
     * @return the property path string
     */
    public String getPropertyPathString() {
        return buildPropertyPathString(targetClass, targetProperty);
    }

    public static String buildPropertyPathString(Class<?> targetClass, String targetPropertyName) {
        return targetClass.getName() + CLASS_PROPERTY_SEPARATOR + targetPropertyName;
    }

    @Override
    public String toString() {
        return buildPropertyPathString(targetClass, targetProperty);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        // object must be Test at this point
        SpecificPropertyDefinition other = (SpecificPropertyDefinition) obj;
        return targetProperty.equals(other.targetProperty) && targetClass.equals(other.targetClass);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (null == targetClass ? 0 : targetClass.hashCode());
        hash = 31 * hash + (null == targetProperty ? 0 : targetProperty.hashCode());
        return hash;
    }
}
