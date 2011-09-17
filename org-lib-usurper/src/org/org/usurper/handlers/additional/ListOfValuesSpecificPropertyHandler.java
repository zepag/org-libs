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

import java.util.Iterator;
import java.util.List;

import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.handlers.exceptions.SpecificPropertyHandlingException;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.SpecificPropertyDefinition;

/**
 * This handler will give a specific property a value amongst a list of values.<br>
 * This value will be picked in a parameterized list of values.<br>
 * The handler will iterate the list until every value is attributed and will then start again from the beginning.
 * 
 * @author pagregoire
 */
public class ListOfValuesSpecificPropertyHandler extends AbstractSpecificPropertyHandler {

    private List<?> values;

  
    private Iterator<?> valuesIterator;


    /**
     * @param specificPropertyDefinition
     * @param values
     */
    public ListOfValuesSpecificPropertyHandler(SpecificPropertyDefinition specificPropertyDefinition, List<?> values) {
        super(specificPropertyDefinition);
        if (values == null || values.size() == 0) {
            throw new SpecificPropertyHandlingException("The list of values should not be null nor empty!");
        }
        this.values = values;
    }

    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledBeanProperty)
     */
    public Object handle(HandledBeanProperty handledBeanProperty) {
        return doHandle();
    }

    /**
     * @see org.org.usurper.handlers.IHandler#handle(org.org.usurper.model.HandledConstructorArg)
     */
    public Object handle(HandledConstructorArg handledBeanProperty) {
        return doHandle();
    }

    /**
     * @return
     */
    private Object doHandle() {
        Object result = null;
        if (this.valuesIterator == null) {
            this.valuesIterator = this.values.iterator();
        }
        if (!this.valuesIterator.hasNext()) {
            this.valuesIterator = null;
            this.valuesIterator = this.values.iterator();
        }
        result = valuesIterator.next();
        return result;
    }

}
