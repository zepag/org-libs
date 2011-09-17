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

import org.org.usurper.setup.IUsurperGeneratorSetup;

/**
 * The Class HandledBeanProperty is an immutable value object containing all the necessary elements for the handling of a precise attribute of an usurped object.
 */
public class HandledBeanProperty implements IHandledEntity {

    private final Object targetObject;

    private final Class<?> propertyClass;

    private final String propertyName;

    private final IUsurperGeneratorSetup usurperGeneratorSetup;

    /**
     * This constructor creates the HandledBeanProperty
     * 
     * @param targetObject
     *            the object where the property will be set as a context (can be useful to handle relations between attributes).
     * @param propertyClass
     *            the class of the property to generate.
     * @param propertyName
     *            the name of the property to generate.
     * @param usurperGeneratorSetup the usurper generator setup
     * @return an Object of the type defined by the propertyClass parameter.
     */
    public HandledBeanProperty(final Object targetObject, final Class<?> propertyClass, final String propertyName, final IUsurperGeneratorSetup usurperGeneratorSetup) {
        super();
        this.targetObject = targetObject;
        this.propertyClass = propertyClass;
        this.propertyName = propertyName;
        this.usurperGeneratorSetup = usurperGeneratorSetup;
    }

  
    /**
     * Gets the class of the property to be handled.
     * 
     * @return the property class
     */
    public Class<?> getPropertyClass() {
        return propertyClass;
    }

    /**
     * Gets the property name.
     * 
     * @return the property name
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Gets the object where the property will be set.
     * 
     * @return the target object
     */
    public Object getTargetObject() {
        return targetObject;
    }

    /**
     * Gets the usurper generator setup.
     * 
     * @return the usurper generator setup
     */
    public IUsurperGeneratorSetup getUsurperGeneratorSetup() {
        return usurperGeneratorSetup;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Target object: " + targetObject.getClass().getName() + targetObject.getClass().hashCode());
        stringBuilder.append("property name: " + propertyName);
        stringBuilder.append("property class: " + propertyClass.getName());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        // object must be Test at this point
        HandledBeanProperty other = (HandledBeanProperty) obj;
        return targetObject.equals(other.targetObject) && propertyName.equals(other.propertyName);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (null == targetObject ? 0 : targetObject.hashCode());
        hash = 31 * hash + (null == propertyName ? 0 : propertyName.hashCode());
        return hash;
    }
}
