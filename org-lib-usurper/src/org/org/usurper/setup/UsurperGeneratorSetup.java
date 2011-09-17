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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.org.usurper.handlers.IHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.handlers.basic.ArrayHandler;
import org.org.usurper.handlers.basic.EnumHandler;
import org.org.usurper.model.IHandledEntity;
import org.org.usurper.model.ITargetDefinition;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.model.SpecificPropertyDefinition;
import org.org.usurper.setup.constants.OnMissingHandlers;
import org.org.usurper.setup.constants.PropertyWritingMechanism;
import org.org.usurper.setup.constants.UsurperGeneratorConstants;
import org.org.usurper.utils.UsurperGeneratorSetupUtils;

/**
 * This is the mutable version of the class setting up the behaviour of the UsurperGenerator. Default behaviour is to fail if no handler is registered for a given property of the Bean.<br>
 * Default type handlers are already registered (but can be overriden) for :<br>
 * <ul>
 * <li>primitive types (int, boolean,...etc)</li>
 * <li>corresponding types(Integer,Boolean,...etc)</li>
 * <li>some other types (java.lang.String,java.util.Date)</li>
 * </ul>
 * <br>
 * Default ArrayHandler and EnumHandler are the one provided with Usurper. <b>You are not encouraged to modify these two handlers.</b><br>
 * 
 * Default collections count callback returns the default value: 10.<br>
 * 
 * @author pagregoire
 * 
 */
public class UsurperGeneratorSetup implements IUsurperGeneratorSetup {

    Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> propertyTypeHandlersMap;
    Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> specificPropertyHandlersMap;
    private ArrayHandler arrayHandler;
    private EnumHandler enumHandler;
    private OnMissingHandlers onMissingHandlers;
    private PropertyWritingMechanism propertyWritingMechanism;
    private ICountCallback countCallback;

    /**
     * Instantiates a new mutable usurper generator setup using default configuration:<br>
     * <ul>
     *  <li>Using default property handlers for basic types (see default handlers' package), SQL types handlers ,and Collections handlers.
     *  <li>OnMissingHandlers: FAIL
     *  <li>PropertyWritingMechanism.USE_SETTERS
     *  <li>CountCallback: always returns the default entry count defined in {@link UsurperGeneratorConstants}.
     */
    public UsurperGeneratorSetup() {
        this.propertyTypeHandlersMap = new HashMap<PropertyTypeDefinition, AbstractPropertyTypeHandler>(10);
        this.specificPropertyHandlersMap = new HashMap<SpecificPropertyDefinition, AbstractSpecificPropertyHandler>();
        this.arrayHandler = new ArrayHandler();
        this.enumHandler = new EnumHandler();
        this.onMissingHandlers = OnMissingHandlers.FAIL;
        this.propertyWritingMechanism = PropertyWritingMechanism.USE_SETTERS;
        this.registerPropertyTypeHandlers(UsurperGeneratorConstants.DEFAULT_PROPERTY_HANDLERS);
        this.countCallback = new ICountCallback() {

            public Integer determineCount(IHandledEntity handledEntity) {
                return UsurperGeneratorConstants.DEFAULT_ENTRIES_COUNT;
            }

        };
    }

    /**
     * Instantiates a new mutable usurper generator setup using the passed setup as a configuration.
     * 
     * @param immutableSetup the immutable setup
     */
    public UsurperGeneratorSetup(IUsurperGeneratorSetup immutableSetup) {
        this.propertyTypeHandlersMap = new HashMap<PropertyTypeDefinition, AbstractPropertyTypeHandler>(immutableSetup.getPropertyTypeHandlersMap());
        this.specificPropertyHandlersMap = new HashMap<SpecificPropertyDefinition, AbstractSpecificPropertyHandler>(immutableSetup.getSpecificPropertyHandlersMap());
        this.arrayHandler = immutableSetup.getArrayHandler();
        this.enumHandler = immutableSetup.getEnumHandler();
        this.onMissingHandlers = immutableSetup.getOnMissingHandlers();
        this.propertyWritingMechanism = immutableSetup.getPropertyWritingMechanism();
        this.countCallback = immutableSetup.getCountCallback();
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getPropertyTypeHandlersMap()
     */
    public Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> getPropertyTypeHandlersMap() {
        return propertyTypeHandlersMap;
    }

    /**
     * Sets the property type handlers map.
     * 
     * @param propertyTypeHandlersMap the property type handlers map
     */
    public void setPropertyTypeHandlersMap(Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> propertyTypeHandlersMap) {
        this.propertyTypeHandlersMap = propertyTypeHandlersMap;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getSpecificPropertyHandlersMap()
     */
    public Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> getSpecificPropertyHandlersMap() {
        return specificPropertyHandlersMap;
    }

    /**
     * This method allows the user to register a Handler for Properties of determinated java types.<br>
     * Only one handler can be registered for a given java type at a time.<br>
     * Registering a handler when one is already registered for a given type overrides the preceding one.<br>
     * 
     * @param typeHandler the handler to register (AbstractPropertyTypeHandler)
     */
    public void registerPropertyTypeHandler(AbstractPropertyTypeHandler typeHandler) {
        for (PropertyTypeDefinition handledType : typeHandler.getHandledTypes()) {
            propertyTypeHandlersMap.put(handledType, typeHandler);
        }
    }

    /**
     * This method allows the user to register many Handlers for Properties of determinated java types.<br>
     * Only one handler can be registered for a given java type at a time.<br>
     * Registering a handler when one is already registered for a given type overrides the preceding one.<br>
     * 
     * @param typeHandlers the handler to register (Set&lt;AbstractPropertyTypeHandler&gt;)
     */
    public void registerPropertyTypeHandlers(Set<AbstractPropertyTypeHandler> typeHandlers) {
        for (AbstractPropertyTypeHandler typeHandler : typeHandlers) {
            registerPropertyTypeHandler(typeHandler);
        }
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#hasPropertyTypeHandler(org.org.usurper.model.PropertyTypeDefinition)
     */
    public boolean hasPropertyTypeHandler(PropertyTypeDefinition type) {
        return this.propertyTypeHandlersMap.containsKey(type);
    }


    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getPropertyTypeHandler(org.org.usurper.model.PropertyTypeDefinition)
     */
    public AbstractPropertyTypeHandler getPropertyTypeHandler(PropertyTypeDefinition handledType) {
        return this.propertyTypeHandlersMap.get(handledType);
    }

    /**
     * This method allows the user to register a Handler for a given property.<br>
     * Only one handler can be registered for a given property at a time.<br>
     * Registering a handler when one is already registered for a given property overrides the preceding one.<br>
     * 
     * @param propertyHandler the handler to register (AbstractSpecificPropertyHandler)
     */
    public void registerSpecificPropertyHandler(AbstractSpecificPropertyHandler propertyHandler) {
        this.specificPropertyHandlersMap.put(propertyHandler.getTargetProperty(), propertyHandler);
    }

 
    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getAllHandlers()
     */
    public Map<ITargetDefinition, IHandler> getAllHandlers() {
        Map<ITargetDefinition, IHandler> result = new HashMap<ITargetDefinition, IHandler>();
        result.putAll(specificPropertyHandlersMap);
        result.putAll(propertyTypeHandlersMap);
        return result;
    }

    /**
     * Gets the immutable version of this mutable setup.
     * 
     * @return the immutable
     */
    public ImmutableUsurperGeneratorSetup getImmutable() {
        return new ImmutableUsurperGeneratorSetup(propertyTypeHandlersMap, specificPropertyHandlersMap, arrayHandler, enumHandler, onMissingHandlers, propertyWritingMechanism, countCallback);
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getArrayHandler()
     */
    public ArrayHandler getArrayHandler() {
        return arrayHandler;
    }

    /**
     * Sets the array handler.
     * 
     * @param arrayHandler the new array handler
     */
    public void setArrayHandler(ArrayHandler arrayHandler) {
        this.arrayHandler = arrayHandler;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getEnumHandler()
     */
    public EnumHandler getEnumHandler() {
        return enumHandler;
    }

    /**
     * Sets the enum handler.
     * 
     * @param enumHandler the new enum handler
     */
    public void setEnumHandler(EnumHandler enumHandler) {
        this.enumHandler = enumHandler;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getOnMissingHandlers()
     */
    public OnMissingHandlers getOnMissingHandlers() {
        return onMissingHandlers;
    }

    /**
     * Sets On missing handlers behavior using the String values of the enum type.
     * 
     * @param onMissingHandlers the new on missing handlers
     */
    public void setOnMissingHandlers(String onMissingHandlers) {
        this.onMissingHandlers = OnMissingHandlers.valueOf(onMissingHandlers);
    }

    /**
     * Sets On missing handlers behavior using the enum type.
     * 
     * @param onMissingHandlers the on missing handlers
     */
    public void onMissingHandlers(OnMissingHandlers onMissingHandlers) {
        this.onMissingHandlers = onMissingHandlers;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getPropertyWritingMechanism()
     */
    public PropertyWritingMechanism getPropertyWritingMechanism() {
        return propertyWritingMechanism;
    }

    /**
     * Sets the property writing mechanism using the String values of the enum type.
     * 
     * @param propertyWritingMechanism the new property writing mechanism
     */
    public void setPropertyWritingMechanism(String propertyWritingMechanism) {
        this.propertyWritingMechanism = PropertyWritingMechanism.valueOf(propertyWritingMechanism);
    }

    /**
     * Sets the property writing mechanism using the enum type.
     * 
     * @param propertyWritingMechanism the property writing mechanism
     */
    public void usePropertyWritingMechanism(PropertyWritingMechanism propertyWritingMechanism) {
        this.propertyWritingMechanism = propertyWritingMechanism;
    }

    /**
     * @see org.org.usurper.setup.IUsurperGeneratorSetup#getCountCallback()
     */
    public ICountCallback getCountCallback() {
        return countCallback;
    }

    /**
     * Sets the count callback.
     * 
     * @param countCallback the new count callback
     */
    public void setCountCallback(ICountCallback countCallback) {
        this.countCallback = countCallback;
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
     * Sets All the handlers at once.
     * 
     * @param handlers the handlers
     */
    public void setAllHandlers(Map<ITargetDefinition, IHandler> handlers) {
        for (ITargetDefinition key : handlers.keySet()) {
            IHandler handler = handlers.get(key);
            if (key instanceof SpecificPropertyDefinition) {
                if (handler instanceof AbstractSpecificPropertyHandler) {
                    specificPropertyHandlersMap.put((SpecificPropertyDefinition) key, (AbstractSpecificPropertyHandler) handler);
                } else {
                    throw new ClassCastException("With SpecificPropertyDefinition key :<" + key + "> a " + AbstractSpecificPropertyHandler.class.getName() + " implementation should be defined");
                }
            } else if (key instanceof PropertyTypeDefinition) {
                if (handler instanceof AbstractPropertyTypeHandler) {
                    propertyTypeHandlersMap.put((PropertyTypeDefinition) key, (AbstractPropertyTypeHandler) handler);
                } else {
                    throw new ClassCastException("With PropertyTypeDefinition key :<" + key + "> a " + AbstractPropertyTypeHandler.class.getName() + " implementation  should be defined");
                }
            } else {
                throw new ClassCastException("key should be a " + SpecificPropertyDefinition.class.getName() + " or a " + PropertyTypeDefinition.class.getName() + ", not a" + key.getClass().getName());
            }
        }
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