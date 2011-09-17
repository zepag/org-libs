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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.org.usurper.handlers.IHandler;
import org.org.usurper.model.PropertyTypeDefinition;

/**
 * This is the class to extend in order to implement a handler for a specific type.
 * 
 * @author pagregoire
 */
public abstract class AbstractPropertyTypeHandler implements IHandler {

    /** The handled types. */
    private Set<PropertyTypeDefinition> handledTypes;

    @SuppressWarnings("unused")
    private AbstractPropertyTypeHandler() {
    }

    /**
     * Instantiates a new abstract property type handler.
     * Takes a series of PropertyTypeDefinition objects as parameter.
     * @param handledTypes
     *            the handled types
     */
    public AbstractPropertyTypeHandler(PropertyTypeDefinition... properTypeDefinitions) {
        this.handledTypes = Collections.unmodifiableSet(new HashSet<PropertyTypeDefinition>(Arrays.asList(properTypeDefinitions)));
    }

    /**
     * Instantiates a new abstract property type handler.
     * Takes a series of Class objects as parameter.
     * @param handledTypes
     *            the handled types
     */
    public AbstractPropertyTypeHandler(Class<?>... propertyTypeDefinitionClasses) {
        Set<PropertyTypeDefinition> handledTypes = new HashSet<PropertyTypeDefinition>();
        for (Class<?> propertyTypeDefinitionClass : propertyTypeDefinitionClasses) {
            handledTypes.add(new PropertyTypeDefinition(propertyTypeDefinitionClass));
        }
        this.handledTypes = Collections.unmodifiableSet(handledTypes);
    }

    /**
     * Instantiates a new abstract property type handler.
     * Takes a Set of PropertyTypeDefinition objects as parameter.
     * @param handledTypes
     *            the handled types
     */
    public AbstractPropertyTypeHandler(Set<PropertyTypeDefinition> handledTypes) {
        this.handledTypes = handledTypes;
    }

    /**
     * Gets the handled types.
     * 
     * @return Returns the handledType.
     */
    public Set<PropertyTypeDefinition> getHandledTypes() {
        return handledTypes;
    }

    /**
     * Sets the handled types.
     * 
     * @param handledTypes
     *            the handled types
     */
    public void setHandledTypes(Set<PropertyTypeDefinition> handledTypes) {
        this.handledTypes = handledTypes;
    }

}
