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
package org.org.usurper.springframework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.org.usurper.handlers.IHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.handlers.basic.ArrayHandler;
import org.org.usurper.handlers.basic.EnumHandler;
import org.org.usurper.model.ITargetDefinition;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.model.SpecificPropertyDefinition;
import org.org.usurper.setup.ICountCallback;
import org.org.usurper.setup.IUsurperGeneratorSetup;
import org.org.usurper.setup.UsurperGeneratorSetup;
import org.org.usurper.setup.constants.OnMissingHandlers;
import org.org.usurper.setup.constants.PropertyWritingMechanism;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * The Class UsurperGeneratorSetupFactoryBean is a Spring compliant FactoryBean and InitializingBean.
 * As such, it can be used as any other Spring Factory Bean.
 * It generates an UsurperGeneratorSetup.
 */
public class UsurperGeneratorSetupFactoryBean implements FactoryBean, InitializingBean {

    private ArrayHandler arrayHandler;
    private ICountCallback countCallback;
    private EnumHandler enumHandler;
    private OnMissingHandlers onMissingHandlers=null;
    private PropertyWritingMechanism propertyWritingMechanism=null;
    private Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> propertyTypeHandlersMap;
    private Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> specificPropertyHandlersMap;
    private UsurperGeneratorSetup usurperGeneratorSetup;
    private IUsurperGeneratorSetup parentSetup;

    public UsurperGeneratorSetupFactoryBean() {
        super();
    }

    public Object getObject() throws Exception {
        return usurperGeneratorSetup;
    }

    @SuppressWarnings("unchecked")
    public Class getObjectType() {
        return IUsurperGeneratorSetup.class;
    }

    public boolean isSingleton() {
        return false;
    }

    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        if (parentSetup != null) {
            usurperGeneratorSetup = new UsurperGeneratorSetup(parentSetup);
        } else {
            usurperGeneratorSetup = new UsurperGeneratorSetup();
        }
        if (arrayHandler != null) {
            usurperGeneratorSetup.setArrayHandler(arrayHandler);
        }
        if (enumHandler != null) {
            usurperGeneratorSetup.setArrayHandler(arrayHandler);
        }
        if (onMissingHandlers != null) {
            usurperGeneratorSetup.onMissingHandlers(onMissingHandlers);
        }
        if (propertyWritingMechanism != null) {
            usurperGeneratorSetup.usePropertyWritingMechanism(propertyWritingMechanism);
        }
        if (countCallback != null) {
            usurperGeneratorSetup.setCountCallback(countCallback);
        }
        Map<ITargetDefinition, IHandler> handlers = new HashMap<ITargetDefinition, IHandler>();
        if (propertyTypeHandlersMap != null) {
            handlers.putAll(propertyTypeHandlersMap);
        }
        if (specificPropertyHandlersMap != null) {
            handlers.putAll(specificPropertyHandlersMap);
        }
        usurperGeneratorSetup.setAllHandlers(handlers);
    }

    public void setArrayHandlerName(String arrayHandler) {
        try {
            this.arrayHandler = (ArrayHandler) Class.forName(arrayHandler).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Impossible to instanciate class " + arrayHandler, e);
        }
    }

    public void setArrayHandler(ArrayHandler arrayHandler) {
        this.arrayHandler = arrayHandler;
    }

    public void setCountCallback(ICountCallback countCallback) {
        this.countCallback = countCallback;
    }

    public void setCountCallbackName(String countCallback) {
        try {
            this.countCallback = (ICountCallback) Class.forName(countCallback).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Impossible to instanciate class " + countCallback, e);
        }
    }

    public void setConstructorParameterTypes(List<String> constructorParameterTypesNames) {
        Class<?>[] constructorParameterTypes = new Class<?>[constructorParameterTypesNames.size()];
        int i = 0;
        for (String constructorParameterType : constructorParameterTypesNames) {
            try {
                constructorParameterTypes[i++] = Class.forName(constructorParameterType);
            } catch (Exception e) {
                throw new IllegalArgumentException("Impossible to find class " + constructorParameterType, e);
            }
        }
    }

    public void setEnumHandlerName(String enumHandler) {
        try {
            this.enumHandler = (EnumHandler) Class.forName(enumHandler).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Impossible to instanciate class " + enumHandler, e);
        }
    }

    public void setEnumHandler(EnumHandler enumHandler) {
        this.enumHandler = enumHandler;
    }

    public void setOnMissingHandlers(String onMissingHandlers) {
        this.onMissingHandlers = OnMissingHandlers.valueOf(onMissingHandlers.toUpperCase());
    }

    public void setPropertyTypeHandlers(Set<AbstractPropertyTypeHandler> propertyTypeHandlers) {
        initPropertyTypeHandlersMap();
        for (AbstractPropertyTypeHandler propertyTypeHandler : propertyTypeHandlers) {
            for (PropertyTypeDefinition propertyTypeDefinition : propertyTypeHandler.getHandledTypes()) {
                this.propertyTypeHandlersMap.put(propertyTypeDefinition, propertyTypeHandler);
            }
        }
    }

    public void setSpecificPropertyHandlers(Set<AbstractSpecificPropertyHandler> specificPropertyHandlers) {
        initSpecificPropertyHandlersMap();
        for (AbstractSpecificPropertyHandler specificPropertyHandler : specificPropertyHandlers) {
            this.specificPropertyHandlersMap.put(specificPropertyHandler.getTargetProperty(), specificPropertyHandler);
        }
    }

    public void setPropertyTypeHandlersClassNames(Map<Class<?>, Class<?>> propertyTypeHandlersMap) {
        initPropertyTypeHandlersMap();
        Map<PropertyTypeDefinition, AbstractPropertyTypeHandler> result = new HashMap<PropertyTypeDefinition, AbstractPropertyTypeHandler>();
        for (Class<?> key : propertyTypeHandlersMap.keySet()) {
            try {
                Set<PropertyTypeDefinition> propertyTypeDefinitions = new HashSet<PropertyTypeDefinition>();
                PropertyTypeDefinition propertyTypeDefinition = new PropertyTypeDefinition(key);
                propertyTypeDefinitions.add(propertyTypeDefinition);
                AbstractPropertyTypeHandler value = (AbstractPropertyTypeHandler) propertyTypeHandlersMap.get(key).getConstructor(Set.class).newInstance(new Object[] { propertyTypeDefinitions });
                result.put(new PropertyTypeDefinition(key), value);
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        this.propertyTypeHandlersMap.putAll(result);
    }

    public void setSpecificPropertyHandlersClassNames(Map<String, Class<?>> specificPropertyHandlersMap) {
        initSpecificPropertyHandlersMap();
        Map<SpecificPropertyDefinition, AbstractSpecificPropertyHandler> result = new HashMap<SpecificPropertyDefinition, AbstractSpecificPropertyHandler>();
        for (String key : specificPropertyHandlersMap.keySet()) {
            SpecificPropertyDefinition specificPropertyDefinition = new SpecificPropertyDefinition(key);
            try {
                AbstractSpecificPropertyHandler value = (AbstractSpecificPropertyHandler) specificPropertyHandlersMap.get(key).getConstructor(SpecificPropertyDefinition.class).newInstance(specificPropertyDefinition);
                result.put(specificPropertyDefinition, value);
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        this.specificPropertyHandlersMap.putAll(result);
    }

    private synchronized void initPropertyTypeHandlersMap() {
        if(propertyTypeHandlersMap==null)propertyTypeHandlersMap = new HashMap<PropertyTypeDefinition, AbstractPropertyTypeHandler>();
    }
    
    private synchronized void initSpecificPropertyHandlersMap() {
        if(specificPropertyHandlersMap==null)specificPropertyHandlersMap = new HashMap<SpecificPropertyDefinition, AbstractSpecificPropertyHandler>();
    }
    
    public void setPropertyWritingMechanism(String propertyWritingMechanism) {
        this.propertyWritingMechanism = PropertyWritingMechanism.valueOf(propertyWritingMechanism.toUpperCase());
    }

    public void onMissingHandlers(OnMissingHandlers onMissingHandlers) {
        this.onMissingHandlers = onMissingHandlers;
    }

    public void usePropertyWritingMechanism(PropertyWritingMechanism propertyWritingMechanism) {
        this.propertyWritingMechanism = propertyWritingMechanism;
    }

    public void setParentSetup(IUsurperGeneratorSetup parentSetup) {
        this.parentSetup = parentSetup;
    }

}
