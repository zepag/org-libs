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
package org.org.usurper.setup.constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.defaults.BooleanPropertyTypeHandler;
import org.org.usurper.handlers.defaults.BytePropertyTypeHandler;
import org.org.usurper.handlers.defaults.CharacterPropertyTypeHandler;
import org.org.usurper.handlers.defaults.DatePropertyTypeHandler;
import org.org.usurper.handlers.defaults.DoublePropertyTypeHandler;
import org.org.usurper.handlers.defaults.FloatPropertyTypeHandler;
import org.org.usurper.handlers.defaults.IntegerPropertyTypeHandler;
import org.org.usurper.handlers.defaults.ListAndSetPropertyTypeHandler;
import org.org.usurper.handlers.defaults.LongPropertyTypeHandler;
import org.org.usurper.handlers.defaults.MapPropertyTypeHandler;
import org.org.usurper.handlers.defaults.ShortPropertyTypeHandler;
import org.org.usurper.handlers.defaults.StringPropertyTypeHandler;
import org.org.usurper.handlers.sql.TimestampPropertyTypeHandler;

/**
 * This is a final non-instanciable class referencing the constants used for Usurper setup.
 */
public final class UsurperGeneratorConstants {

    private UsurperGeneratorConstants() {
    }

    /** Default entries count for arrays and collections. */
    public static final Integer DEFAULT_ENTRIES_COUNT = 10;

    /** Default property handlers for basic types. */
    public static final Set<AbstractPropertyTypeHandler> DEFAULT_PROPERTY_HANDLERS;

    static {
        Set<AbstractPropertyTypeHandler> temporarySet = new HashSet<AbstractPropertyTypeHandler>();
        temporarySet.add(new IntegerPropertyTypeHandler());
        temporarySet.add(new FloatPropertyTypeHandler());
        temporarySet.add(new DoublePropertyTypeHandler());
        temporarySet.add(new LongPropertyTypeHandler());
        temporarySet.add(new ShortPropertyTypeHandler());
        temporarySet.add(new BooleanPropertyTypeHandler());
        temporarySet.add(new BytePropertyTypeHandler());
        temporarySet.add(new CharacterPropertyTypeHandler());
        temporarySet.add(new StringPropertyTypeHandler());
        temporarySet.add(new DatePropertyTypeHandler());
        temporarySet.add(new ListAndSetPropertyTypeHandler());
        temporarySet.add(new MapPropertyTypeHandler());
        temporarySet.add(new org.org.usurper.handlers.sql.DatePropertyTypeHandler());
        temporarySet.add(new TimestampPropertyTypeHandler());
        DEFAULT_PROPERTY_HANDLERS = Collections.unmodifiableSet(temporarySet);
    }
}
