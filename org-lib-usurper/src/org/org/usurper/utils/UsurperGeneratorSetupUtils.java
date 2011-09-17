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
package org.org.usurper.utils;

import java.util.SortedSet;
import java.util.TreeSet;

import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.setup.IUsurperGeneratorSetup;
/**
 * This class is an UsurperGeneratorSetup utility class
 * 
 * @author pagregoire
 */
public final class UsurperGeneratorSetupUtils {

    private UsurperGeneratorSetupUtils() {
    }

    /**
     * This method builds a String representation of an IUsurperGeneratorSetup instance.
     * 
     * @param usurperGeneratorSetup the usurper generator setup
     * 
     * @return the string
     */
    public static String buildStringRepresentation(IUsurperGeneratorSetup usurperGeneratorSetup) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------------------------------------\n");
        stringBuilder.append("Usurper Generator setup:" + usurperGeneratorSetup.getClass().getName() + "\n");
        stringBuilder.append("---------\n");
        stringBuilder.append("Array handler: " + usurperGeneratorSetup.getArrayHandler().getClass().getName() + "\n");
        stringBuilder.append("Enum handler: " + usurperGeneratorSetup.getEnumHandler().getClass().getName() + "\n");
        stringBuilder.append("Count callback: " + usurperGeneratorSetup.getCountCallback().getClass().getName() + "\n");
        stringBuilder.append("On missing handlers: " + usurperGeneratorSetup.getOnMissingHandlers().toString() + "\n");
        stringBuilder.append("Property writing mechanism: " + usurperGeneratorSetup.getPropertyWritingMechanism().toString() + "\n");
        stringBuilder.append("---------\n");
        SortedSet<String> sortedSet = new TreeSet<String>();
        for (PropertyTypeDefinition handledProperty : usurperGeneratorSetup.getPropertyTypeHandlersMap().keySet()) {
            sortedSet.add(handledProperty + ": " + usurperGeneratorSetup.getPropertyTypeHandlersMap().get(handledProperty).getClass().getName() + "\n");
        }
        for (String message : sortedSet) {
            stringBuilder.append(message);
        }
        stringBuilder.append("---------\n");
        sortedSet = new TreeSet<String>();
        for (AbstractSpecificPropertyHandler abstractSpecificPropertyHandler : usurperGeneratorSetup.getSpecificPropertyHandlersMap().values()) {
            sortedSet.add(abstractSpecificPropertyHandler.getTargetProperty().getPropertyPathString() + ": " + abstractSpecificPropertyHandler.getClass().getName() + "\n");
        }
        for (String message : sortedSet) {
            stringBuilder.append(message);
        }
        stringBuilder.append("--------------------------------------------\n");
        return stringBuilder.toString();
    }

}