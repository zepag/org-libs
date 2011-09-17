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
package org.org.usurper.setup;

import java.util.Map;

import org.org.usurper.handlers.IHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.handlers.basic.ArrayHandler;
import org.org.usurper.handlers.basic.EnumHandler;
import org.org.usurper.model.ITargetDefinition;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.model.SpecificPropertyDefinition;
import org.org.usurper.setup.constants.OnMissingHandlers;
import org.org.usurper.setup.constants.PropertyWritingMechanism;
/**
 * This is the interface for the setup of the behavior of the UsurperGenerator.<br>
 * 
 * @author pagregoire
 */
public interface IUsurperGeneratorSetup {
    
    /**
     * Gets the property type handlers map.
     * @return the property type handlers map
     */
    public abstract Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> getPropertyTypeHandlersMap();

    /**
     * Gets the specific property handlers map.
     * @return the specific property handlers map
     */
    public abstract Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> getSpecificPropertyHandlersMap();

    /**
     * Gets the array handler.
     * @return the array handler
     */
    public abstract ArrayHandler getArrayHandler();

    /**
     * Gets the enum handler.
     * @return the enum handler
     */
    public abstract EnumHandler getEnumHandler();

    /**
     * Gets the on missing handlers behavior.
     * @return the on missing handlers
     */
    public abstract OnMissingHandlers getOnMissingHandlers();

    /**
     * Gets the property writing mechanism behavior.
     * 
     * @return the property writing mechanism
     */
    public abstract PropertyWritingMechanism getPropertyWritingMechanism();

    /**
     * Gets the count callback.
     * 
     * @return the count callback
     */
    public abstract ICountCallback getCountCallback();

    /**
     * Gets all handlers.
     * 
     * @return the all handlers
     */
    public Map<ITargetDefinition, IHandler> getAllHandlers();

    /**
     * This method tests if a handler is registered for a given Java Type.
     * 
     * @param propertyTypeDefinition the property type definition
     * 
     * @return true if one is registered, false otherwise
     */
    public boolean hasPropertyTypeHandler(PropertyTypeDefinition propertyTypeDefinition);

    /**
     * This method retrieves the Handler for Properties of a given java type.
     * 
     * @param propertyTypeDefinition the property type definition
     * 
     * @return the registered handler or null if none is defined.
     */
    public AbstractPropertyTypeHandler getPropertyTypeHandler(PropertyTypeDefinition propertyTypeDefinition);

    /**
     * This method tests if a handler is registered for a given Property of a given Class.
     * 
     * @param specificPropertyDefinition the specific property definition
     * 
     * @return true if one is registered, false otherwise
     */
    public boolean hasSpecificPropertyHandler(SpecificPropertyDefinition specificPropertyDefinition);

    /**
     * This method retrieves the Handler for a given Property of a given Class.
     * 
     * @param specificPropertyDefinition the specific property definition
     * 
     * @return the registered property handler or null if none is defined.
     */
    public AbstractSpecificPropertyHandler getSpecificPropertyHandler(SpecificPropertyDefinition specificPropertyDefinition);

    /**
     * The implementation of this method must return a String representation of the setup.
     * 
     * @return the String representation of the setup.
     */
    public String toStringRepresentation();
}