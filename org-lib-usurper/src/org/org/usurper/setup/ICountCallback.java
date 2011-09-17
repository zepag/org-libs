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

import org.org.usurper.model.IHandledEntity;
/**
 * Implementations of this interface can provide a way to define the entries count for generated collections and arrays.
 */
public interface ICountCallback {
    
    /**
     * Determine count.
     * 
     * @param handledEntity the handled entity as a context
     * 
     * @return the integer
     */
    public Integer determineCount(IHandledEntity handledEntity);
}