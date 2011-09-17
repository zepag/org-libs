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

import java.util.Collections;
import java.util.HashMap;
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
import org.org.usurper.utils.UsurperGeneratorSetupUtils;

/**
 * This is the immutable version of the class setting up the behaviour of the UsurperGenerator. 
 * 
 * @author pagregoire
 * 
 */
public class ImmutableUsurperGeneratorSetup implements IUsurperGeneratorSetup {
    private Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> propertyTypeHandlersMap;
    private Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> specificPropertyHandlersMap;
    private ArrayHandler arrayHandler;
    private EnumHandler enumHandler;
    private OnMissingHandlers onMissingHandlers;
    private PropertyWritingMechanism propertyWritingMechanism;
    private ICountCallback countCallback;

    /**
     * Instantiates a new immutable usurper generator setup.
     * 
     * @param propertyTypeHandlersMap the property type handlers map
     * @param specificPropertyHandlersMap the specific property handlers map
     * @param arrayHandler the array handler
     * @param enumHandler the enum handler
     * @param onMissingHandlers the on missing handlers
     * @param propertyWritingMechanism the property writing mechanism
     * @param countCallback the count callback
     */
    public ImmutableUsurperGeneratorSetup(Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> propertyTypeHandlersMap, Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> specificPropertyHandlersMap, ArrayHandler arrayHandler, EnumHandler enumHandler, OnMissingHandlers onMissingHandlers, PropertyWritingMechanism propertyWritingMechanism, ICountCallback countCallback) {
        super();
        this.propertyTypeHandlersMap = Collections.unmodifiableMap(propertyTypeHandlersMap);
        this.specificPropertyHandlersMap = Collections.unmodifiableMap(specificPropertyHandlersMap);
        this.arrayHandler = arrayHandler;
        this.enumHandler = enumHandler;
        this.onMissingHandlers = onMissingHandlers;
        this.propertyWritingMechanism = propertyWritingMechanism;
        this.countCallback = countCallback;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getPropertyTypeHandlersMap()
     */
    public Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> getPropertyTypeHandlersMap() {
        return propertyTypeHandlersMap;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getSpecificPropertyHandlersMap()
     */
    public Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> getSpecificPropertyHandlersMap() {
        return specificPropertyHandlersMap;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getArrayHandler()
     */
    public ArrayHandler getArrayHandler() {
        return arrayHandler;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getEnumHandler()
     */
    public EnumHandler getEnumHandler() {
        return enumHandler;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getOnMissingHandlers()
     */
    public OnMissingHandlers getOnMissingHandlers() {
        return onMissingHandlers;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getPropertyWritingMechanism()
     */
    public PropertyWritingMechanism getPropertyWritingMechanism() {
        return propertyWritingMechanism;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getCountCallback()
     */
    public ICountCallback getCountCallback() {
        return countCallback;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getAllHandlers()
     */
    public Map<ITargetDefinition, IHandler> getAllHandlers() {
        Map<ITargetDefinition, IHandler> result = new HashMap<ITargetDefinition, IHandler>();
        result.putAll(specificPropertyHandlersMap);
        result.putAll(propertyTypeHandlersMap);
        return Collections.unmodifiableMap(result);
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#hasPropertyTypeHandler(org.org.usurper.model.PropertyTypeDefinition)
     */
    public boolean hasPropertyTypeHandler(PropertyTypeDefinition propertyTypeDefinition) {
        return this.propertyTypeHandlersMap.containsKey(propertyTypeDefinition);
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getPropertyTypeHandler(org.org.usurper.model.PropertyTypeDefinition)
     */
    public AbstractPropertyTypeHandler getPropertyTypeHandler(PropertyTypeDefinition propertyTypeDefinition) {
        return this.propertyTypeHandlersMap.get(propertyTypeDefinition);
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#hasSpecificPropertyHandler(org.org.usurper.model.SpecificPropertyDefinition)
     */
    public boolean hasSpecificPropertyHandler(SpecificPropertyDefinition specificPropertyDefinition) {
        return this.specificPropertyHandlersMap.containsKey(specificPropertyDefinition);
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getSpecificPropertyHandler(org.org.usurper.model.SpecificPropertyDefinition)
     */
    public AbstractSpecificPropertyHandler getSpecificPropertyHandler(SpecificPropertyDefinition specificPropertyDefinition) {
        return this.specificPropertyHandlersMap.get(specificPropertyDefinition);
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#toStringRepresentation()
     */
    public String toStringRepresentation() {
        return UsurperGeneratorSetupUtils.buildStringRepresentation(this);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return toStringRepresentation();
    }
}
