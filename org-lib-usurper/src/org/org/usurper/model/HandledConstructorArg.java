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
package org.org.usurper.model;

import java.lang.reflect.Constructor;

import org.org.usurper.setup.IUsurperGeneratorSetup;

/**
 * The Class HandledConstructorArg is an immutable value object containing all the necessary elements for the handling of a precise constructor argument of an usurped object.
 */
public class HandledConstructorArg implements IHandledEntity {

    private final Constructor<?> targetConstructor;

    private final Class<?> constructorArgClass;

    private final Integer constructorArgOrderingNumber;

    private final IUsurperGeneratorSetup usurperGeneratorSetup;

    /**
     * This constructor creates the HandledConstructorArg
     * 
     * @param targetConstructor 
     *          the constructor object for the object to be created (can be useful to handle relations between arguments).
     * @param constructorArgClass
     *          the class of the constructor arg to generate.
     * @param constructorArgOrderingNumber the constructor arg ordering number
     * @param usurperGeneratorSetup the usurper generator setup
     */
    public HandledConstructorArg(final Constructor<?> targetConstructor, final Class<?> constructorArgClass, final Integer constructorArgOrderingNumber, final IUsurperGeneratorSetup usurperGeneratorSetup) {
        super();
        this.targetConstructor = targetConstructor;
        this.constructorArgClass = constructorArgClass;
        this.constructorArgOrderingNumber = constructorArgOrderingNumber;
        this.usurperGeneratorSetup = usurperGeneratorSetup;
    }

    /**
     * Gets the constructor used to create the object.
     * 
     * @return the target constructor
     */
    public Constructor<?> getTargetConstructor() {
        return targetConstructor;
    }
    
    /**
     * Gets the class of the constructor arg to be handled. 
     * 
     * @return the constructor arg class
     */
    public Class<?> getConstructorArgClass() {
        return constructorArgClass;
    }

    /**
     * Gets the constructor arg ordering number.
     * 
     * @return the constructor arg ordering number
     */
    public Integer getConstructorArgOrderingNumber() {
        return constructorArgOrderingNumber;
    }

    /**
     * Gets the usurper generator setup.
     * 
     * @return the usurper generator setup
     */
    public IUsurperGeneratorSetup getUsurperGeneratorSetup() {
        return usurperGeneratorSetup;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Target constructor: " + targetConstructor.toGenericString());
        stringBuilder.append("constructor arg: " + constructorArgClass.getName() + " (" + constructorArgOrderingNumber + ")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        // object must be Test at this point
        HandledConstructorArg other = (HandledConstructorArg) obj;
        return targetConstructor.equals(other.targetConstructor) && constructorArgClass.getName().equals(other.constructorArgClass.getName()) && constructorArgOrderingNumber == other.constructorArgOrderingNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (null == targetConstructor ? 0 : targetConstructor.hashCode());
        hash = 31 * hash + (null == constructorArgClass ? 0 : constructorArgClass.hashCode());
        hash = 31 * hash + (null == constructorArgOrderingNumber ? 0 : constructorArgOrderingNumber.hashCode());
        return hash;
    }
}
