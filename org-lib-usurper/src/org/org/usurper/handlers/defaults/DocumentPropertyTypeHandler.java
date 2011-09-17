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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.exceptions.PropertyTypeHandlingException;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.utils.RandomUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author pagregoire
 * @deprecated this is a poor handler...please create your own one. ;)
 */
public class DocumentPropertyTypeHandler extends AbstractPropertyTypeHandler {
    private static Set<PropertyTypeDefinition> handledTypes;
    static {
        handledTypes = new HashSet<PropertyTypeDefinition>();
        handledTypes.add(new PropertyTypeDefinition(Document.class));
    }

    /**
     * @param handledType
     */
    public DocumentPropertyTypeHandler() {
        // FIXME implement a Document generator based on a Schema or a DTD
        super(handledTypes);
    }

    /**
     * @see org.org.zepag.vogenerator.handlers.AbstractPropertyTypeHandler#handle()
     */
    public Object handle(HandledBeanProperty handledBeanProperty) {
        Document document = null;
        try {
            document = doHandle();
        } catch (ParserConfigurationException e) {
            throw new PropertyTypeHandlingException("Unable to handle field <" + handledBeanProperty.getPropertyName() + "(" + handledBeanProperty.getPropertyClass().getName() + ")> from object " + handledBeanProperty.getTargetObject(), e);
        } catch (FactoryConfigurationError e) {
            throw new PropertyTypeHandlingException("Unable to handle field <" + handledBeanProperty.getPropertyName() + "(" + handledBeanProperty.getPropertyClass().getName() + ")> from object " + handledBeanProperty.getTargetObject(), e);
        }
        return document;
    }

    /**
     * @see org.org.zepag.vogenerator.handlers.AbstractPropertyTypeHandler#handle()
     */
    public Object handle(HandledConstructorArg handledConstructorArg) {
        Document document = null;
        try {
            document = doHandle();
        } catch (ParserConfigurationException e) {
            throw new PropertyTypeHandlingException("Unable to handle constructor arg <#" + handledConstructorArg.getConstructorArgOrderingNumber() + "(" + handledConstructorArg.getConstructorArgClass().getName() + ")> from constructor " + handledConstructorArg.getTargetConstructor().getName(), e);
        } catch (FactoryConfigurationError e) {
            throw new PropertyTypeHandlingException("Unable to handle constructor arg <#" + handledConstructorArg.getConstructorArgOrderingNumber() + "(" + handledConstructorArg.getConstructorArgClass().getName() + ")> from constructor " + handledConstructorArg.getTargetConstructor().getName(), e);
        }
        return document;
    }

    /**
     * @return
     * @throws ParserConfigurationException
     * @throws DOMException
     */
    private Document doHandle() throws ParserConfigurationException, DOMException {
        Document document;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.newDocument();
        Node node = document.appendChild(document.createElement(RandomUtils.nextString(5)));
        node.appendChild(document.createElement(RandomUtils.nextString(5)));
        return document;
    }

}
