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
 * This handler returns a random Character value.<br>
 * It handles the Character type and the primitive character type.
 * 
 * @author pagregoire
 */
public class CharacterPropertyTypeHandler extends AbstractPropertyTypeHandler {
    
    /** The handled types. */
    private static Set<PropertyTypeDefinition> handledTypes;
    static {
        handledTypes = new HashSet<PropertyTypeDefinition>();
        handledTypes.add(new PropertyTypeDefinition(Character.class));
        handledTypes.add(new PropertyTypeDefinition(char.class));
    }


    /**
     * Instantiates a new character property type handler.
     */
    public CharacterPropertyTypeHandler() {
        super(handledTypes);
    }

    /**
     * Handle.
     * 
     * @param handledBeanProperty the handled bean property
     * 
     * @return the object
     * 
     * @see org.org.zepag.vogenerator.handlers.AbstractPropertyTypeHandler#handle()
     */
    public Object handle(HandledBeanProperty handledBeanProperty) {
        return RandomUtils.nextCharacter();
    }

    /**
     * Handle.
     * 
     * @param handledConstructorArg the handled constructor arg
     * 
     * @return the object
     * 
     * @see org.org.zepag.vogenerator.handlers.AbstractPropertyTypeHandler#handle()
     */
    public Object handle(HandledConstructorArg handledConstructorArg) {
        return RandomUtils.nextCharacter();
    }

}
