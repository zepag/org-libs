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

import org.org.usurper.handlers.IHandler;
import org.org.usurper.model.SpecificPropertyDefinition;

/**
 * This is the class to extend in order to implement a handler for a specific property of a given class.
 * 
 * @author pagregoire
 */
public abstract class AbstractSpecificPropertyHandler implements IHandler {

    /** The target property class. */
    private SpecificPropertyDefinition specificPropertyDefinition;
    
    /**
     * This constructor takes a SpecificPropertyDefinition as a parameter
     * @param specificPropertyDefinition
     */
    public AbstractSpecificPropertyHandler(SpecificPropertyDefinition specificPropertyDefinition) {
        this.specificPropertyDefinition = specificPropertyDefinition;
    }

    /**
     * This constructor takes a class and a field name as parameters
     * @param valueObjectClass
     * @param handledFieldName
     */
    public AbstractSpecificPropertyHandler(Class<?> valueObjectClass, String handledFieldName) {
        this.specificPropertyDefinition = new SpecificPropertyDefinition(valueObjectClass, handledFieldName);
    }

    /**
     * Gets the handled property name.
     * 
     * @return Returns the handledType.
     * @deprecated prefer access through the specificPropertyDefinition attribute
     */
    public String getHandledPropertyName() {
        return specificPropertyDefinition.getTargetProperty();
    }

    /**
     * Sets the handled property name.
     * 
     * @param handledFieldName
     *            the handled field name
     * @deprecated prefer the constructor
     */
    public void setHandledPropertyName(String handledFieldName) {
        this.specificPropertyDefinition = new SpecificPropertyDefinition(specificPropertyDefinition.getTargetClass(), handledFieldName);
    }

    /**
     * Gets the value object class.
     * 
     * @return Returns the valueObjectClass.
     * @deprecated prefer access through the specificPropertyDefinition attribute
     */
    public Class<?> getValueObjectClass() {
        return this.specificPropertyDefinition.getTargetClass();
    }

    /**
     * Sets the value object class.
     * 
     * @param valueObjectClass
     *            The valueObjectClass to set.
     * @deprecated prefer the constructor
     */
    public void setValueObjectClass(Class<?> valueObjectClass) {
        this.specificPropertyDefinition = new SpecificPropertyDefinition(valueObjectClass, specificPropertyDefinition.getTargetProperty());
    }

    /**
     * Gets the target property definition.
     * 
     * @return the target property
     */
    public SpecificPropertyDefinition getTargetProperty() {
        return specificPropertyDefinition;
    }
}