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
package org.org.usurper.handlers.additional;

import java.util.Map;

import org.org.usurper.UsurperGenerator;
import org.org.usurper.handlers.IHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.ITargetDefinition;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.setup.UsurperGeneratorSetup;

/**
 * This handler allows for a redundant ;) generation of ValueObjects.
 * Handled Value Object types have to be passed to the constructor(s).
 * 
 * @author pagregoire
 */
public class ValueObjectPropertyTypeHandler extends AbstractPropertyTypeHandler {
    /**
     * This constructor takes a series of Supported types' PropertyTypeDefinitions as a parameter.
     * @param supportedTypes
     */
    public ValueObjectPropertyTypeHandler(PropertyTypeDefinition... supportedTypes) {
        super(supportedTypes);
    }

    /**
     * This constructor takes a series of Supported types' Classes as a parameter.
     * @param supportedTypes
     */
    public ValueObjectPropertyTypeHandler(Class<?>... supportedTypes) {
        super(supportedTypes);
    }

    /**
     * @see org.org.usurper.handlers.basic.AbstractPropertyTypeHandler#handle()
     */
    public Object handle(HandledBeanProperty handledBeanProperty) {
        return doHandle(handledBeanProperty.getPropertyClass(), handledBeanProperty.getUsurperGeneratorSetup().getAllHandlers());
    }

    /**
     * @see org.org.usurper.handlers.basic.AbstractPropertyTypeHandler#handle()
     */
    public Object handle(HandledConstructorArg handledConstructorArg) {
        return doHandle(handledConstructorArg.getConstructorArgClass(), handledConstructorArg.getUsurperGeneratorSetup().getAllHandlers());
    }

    /**
     * This method does the proper handling.
     * @return
     */
    @SuppressWarnings("unchecked")
    private Object doHandle(Class<?> entityClass, Map<ITargetDefinition, IHandler> parentHandlers) {
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.setAllHandlers(parentHandlers);
        UsurperGenerator usurper = new UsurperGenerator(entityClass, usurperGeneratorSetup);
        return usurper.generateUsurper();
    }

}