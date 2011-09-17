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

package org.org.usurper.handlers.defaults;

import java.util.HashSet;
import java.util.Set;

import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.utils.RandomUtils;

/**
 * This handler returns a random Byte value.<br>
 * It handles the Byte type and the primitive byte type.
 * @author pagregoire
 */
public class BytePropertyTypeHandler extends AbstractPropertyTypeHandler {
    
    /** The handled types. */
    private static Set<PropertyTypeDefinition> handledTypes;
    static {
        handledTypes = new HashSet<PropertyTypeDefinition>();
        handledTypes.add(new PropertyTypeDefinition(Byte.class));
        handledTypes.add(new PropertyTypeDefinition(byte.class));
    }

    
    /**
     * Instantiates a new Byte property type handler.
     */
    public BytePropertyTypeHandler() {
        super(handledTypes);
    }

    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledBeanProperty)
     */
    public Object handle(HandledBeanProperty handledBeanProperty) {
        return RandomUtils.nextByte();
    }

    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledConstructorArg)
     */
    public Object handle(HandledConstructorArg handledConstructorArg) {
        return RandomUtils.nextByte();
    }
}
