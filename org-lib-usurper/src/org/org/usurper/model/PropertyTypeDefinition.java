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
 * The Class PropertyTypeDefinition is an immutable value object describing a target property type.
 */
public class PropertyTypeDefinition implements ITargetDefinition {

    private final Class<?> targetClass;

    /**
     * Instantiates a new PropertyTypeDefinition from a Class object.
     * 
     * @param targetClass the target class
     */
    public PropertyTypeDefinition(final Class<?> targetClass) {
        super();
        this.targetClass = targetClass;
    }

    /**
     * Instantiates a new PropertyTypeDefinition from the name of a Class.
     * 
     * @param targetDescription the target description
     */
    public PropertyTypeDefinition(final String targetDescription) {
        super();
        String targetClassName = targetDescription;
        try {
            this.targetClass = Class.forName(targetClassName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Impossible to resolve class in: " + targetDescription, e);
        }
    }

    /**
     * Gets the target class.
     * 
     * @return the target class
     */
    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public String toString() {
        return targetClass.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        // object must be Test at this point
        PropertyTypeDefinition other = (PropertyTypeDefinition) obj;
        return targetClass.equals(other.targetClass);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (null == targetClass ? 0 : targetClass.hashCode());
        return hash;
    }
}
