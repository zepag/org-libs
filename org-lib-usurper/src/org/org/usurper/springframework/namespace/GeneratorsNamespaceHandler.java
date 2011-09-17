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
package org.org.usurper.springframework.namespace;

import java.util.ArrayList;
import java.util.List;

import org.org.usurper.springframework.UsurperFactoryBean;
import org.org.usurper.springframework.UsurperGeneratorSetupFactoryBean;
import org.org.usurper.springframework.UsurperListFactoryBean;
import org.org.usurper.springframework.UsurperMapFactoryBean;
import org.org.usurper.springframework.UsurperSetFactoryBean;
import org.org.usurper.springframework.aop.UsurperMethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.Conventions;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * The Class GeneratorsNamespaceHandler defines the Spring > 2.0 Namespace handler for Usurper.
 * See schema: {@link http://www.org-libs.org/org-lib-usurper/schema/generators/usurper-1.0.0.xsd}
 */
public class GeneratorsNamespaceHandler extends NamespaceHandlerSupport {
    private static final String AOP_GENERATOR_TAG = "aop-generator";
    private static final String MAP_GENERATOR_TAG = "map-generator";
    private static final String SET_GENERATOR_TAG = "set-generator";
    private static final String BEAN_GENERATOR_TAG = "bean-generator";
    private static final String LIST_GENERATOR_TAG = "list-generator";
    private static final String SETUP_TAG = "setup";
    private static final String PROPERTY_TYPE_HANDLER_TAG = "property-type-handler";
    private static final String PROPERTY_TYPE_HANDLER_REF_TAG = "property-type-handler-ref";
    private static final String SPECIFIC_PROPERTY_HANDLER_TAG = "specific-property-handler";
    private static final String SPECIFIC_PROPERTY_HANDLER_REF_TAG = "specific-property-handler-ref";

    private static final String INTERFACE_ATTR = "interface";
    private static final String CLASS_ATTR = "class";
    private static final String VALUE_CLASS_ATTR = "value-class";
    private static final String KEY_CLASS_ATTR = "key-class";
    private static final String SETUP_REF_ATTR = "setup-ref";
    private static final String COUNT_ATTR = "count";

    private static final String SETUP_PARENT_SETUP = "parent-setup";

    private static final String SETUP_ARRAY_HANDLER_ATTR = "array-handler";
    private static final String SETUP_ARRAY_HANDLER_REF_ATTR = "array-handler-ref";
    private static final String SETUP_ENUM_HANDLER_ATTR = "enum-handler";
    private static final String SETUP_ENUM_HANDLER_REF_ATTR = "enum-handler-ref";

    private static final String SETUP_ON_MISSING_HANDLERS_ATTR = "on-missing-handlers";
    private static final String SETUP_PROPERTY_WRITING_MECHANISM_ATTR = "property-writing-mechanism";
    private static final String SETUP_COUNT_CALLBACK_ATTR = "count-callback";
    private static final String SETUP_COUNT_CALLBACK_REF_ATTR = "count-callback-ref";

    private static final String PROPERTY_TYPE_HANDLER_PROPERTY_TYPE_ATTR = "property-type";
    private static final String PROPERTY_TYPE_HANDLER_HANDLER_CLASS_ATTR = "handler-class";
    private static final String PROPERTY_TYPE_HANDLER_HANDLER_REF_ATTR = "handler-ref";
    private static final String SPECIFIC_PROPERTY_HANDLER_TARGET_CLASS_ATTR = "target-class";
    private static final String SPECIFIC_PROPERTY_HANDLER_TARGET_PROPERTY_ATTR = "target-property";
    private static final String SPECIFIC_PROPERTY_HANDLER_HANDLER_CLASS_ATTR = "handler-class";
    private static final String SPECIFIC_PROPERTY_HANDLER_HANDLER_REF_ATTR = "handler-ref";

    private static final String USURPER_GENERATOR_SETUP_PROP = "usurperGeneratorSetup";
    private static final String USURPED_CLASS_NAME_PROP = "usurpedClassName";
    private static final String USURPED_VALUE_CLASS_NAME_PROP = "usurpedValueClassName";
    private static final String USURPED_KEY_CLASS_NAME_PROP = "usurpedKeyClassName";

    private static final String PROXYFACTORYBEAN_INTERCEPTOR_NAMES_PROP = "interceptorNames";
    private static final String PROXYFACTORYBEAN_PROXY_INTERFACES_PROP = "proxyInterfaces";
    private static final String USURPER_INTERCEPTOR_BEAN_ID = "usurperInterceptor";

    public void init() {
        registerBeanDefinitionParser(BEAN_GENERATOR_TAG, new BeanGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser(LIST_GENERATOR_TAG, new ListOfBeansGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser(SET_GENERATOR_TAG, new SetOfBeansGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser(MAP_GENERATOR_TAG, new MapOfBeansGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser(AOP_GENERATOR_TAG, new AopGeneratorBeanDefinitionParser());
        registerBeanDefinitionParser(SETUP_TAG, new SetupBeanDefinitionParser());
    }

    private static class AopGeneratorBeanDefinitionParser extends AbstractBeanDefinitionParser implements BeanDefinitionParser {

        @Override
        protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
            String interfaceName = element.getAttribute(INTERFACE_ATTR);
            String setupRef = element.getAttribute(SETUP_REF_ATTR);

            // Create the Interceptor definition
            RootBeanDefinition interceptorDefinition = new RootBeanDefinition(UsurperMethodInterceptor.class);
            if (StringUtils.hasText(setupRef)) {
                interceptorDefinition.getPropertyValues().addPropertyValue(USURPER_GENERATOR_SETUP_PROP, setupRef);
            }
            try {
                BeanDefinitionHolder holder = new BeanDefinitionHolder(interceptorDefinition, USURPER_INTERCEPTOR_BEAN_ID);
                registerBeanDefinition(holder, parserContext.getRegistry());
                if (shouldFireEvents()) {
                    BeanComponentDefinition componentDefinition = new BeanComponentDefinition(holder);
                    postProcessComponentDefinition(componentDefinition);
                    parserContext.registerComponent(componentDefinition);
                }
            } catch (BeanDefinitionStoreException ex) {
                parserContext.getReaderContext().error(ex.getMessage(), element);
            }
            List<String> interceptorNames = new ArrayList<String>();
            interceptorNames.add(USURPER_INTERCEPTOR_BEAN_ID);
            // Create the target Class definition.
            RootBeanDefinition targetDefinition = new RootBeanDefinition(ProxyFactoryBean.class);
            targetDefinition.setSource(parserContext.extractSource(element));
            targetDefinition.getPropertyValues().addPropertyValue(PROXYFACTORYBEAN_PROXY_INTERFACES_PROP, interfaceName);
            targetDefinition.getPropertyValues().addPropertyValue(PROXYFACTORYBEAN_INTERCEPTOR_NAMES_PROP, interceptorNames);
            return targetDefinition;
        }

    }

    private static class BeanGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser implements BeanDefinitionParser {

        @SuppressWarnings("unchecked")
        @Override
        protected Class getBeanClass(Element element) {
            return UsurperFactoryBean.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String usurpedClass = element.getAttribute(CLASS_ATTR);
            bean.addPropertyValue(USURPED_CLASS_NAME_PROP, usurpedClass);

            String setupRef = element.getAttribute(SETUP_REF_ATTR);
            if (StringUtils.hasText(setupRef)) {
                bean.addPropertyReference(USURPER_GENERATOR_SETUP_PROP, setupRef);
            }

        }
    }

    private static class ListOfBeansGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser implements BeanDefinitionParser {

        @SuppressWarnings("unchecked")
        @Override
        protected Class getBeanClass(Element element) {
            return UsurperListFactoryBean.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String usurpedClass = element.getAttribute(CLASS_ATTR);
            bean.addPropertyValue(USURPED_CLASS_NAME_PROP, usurpedClass);

            String setupRef = element.getAttribute(SETUP_REF_ATTR);
            if (StringUtils.hasText(setupRef)) {
                bean.addPropertyReference(USURPER_GENERATOR_SETUP_PROP, setupRef);
            }
            String count = element.getAttribute(COUNT_ATTR);
            if (StringUtils.hasText(count)) {
                bean.addPropertyValue(COUNT_ATTR, Integer.valueOf(count));
            }

        }
    }

    private static class MapOfBeansGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser implements BeanDefinitionParser {

        @SuppressWarnings("unchecked")
        @Override
        protected Class getBeanClass(Element element) {
            return UsurperMapFactoryBean.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String usurpedKeyClass = element.getAttribute(KEY_CLASS_ATTR);
            bean.addPropertyValue(USURPED_KEY_CLASS_NAME_PROP, usurpedKeyClass);

            String usurpedValueClass = element.getAttribute(VALUE_CLASS_ATTR);
            bean.addPropertyValue(USURPED_VALUE_CLASS_NAME_PROP, usurpedValueClass);

            String setupRef = element.getAttribute(SETUP_REF_ATTR);
            if (StringUtils.hasText(setupRef)) {
                bean.addPropertyReference(USURPER_GENERATOR_SETUP_PROP, setupRef);
            }
            String count = element.getAttribute(COUNT_ATTR);
            if (StringUtils.hasText(count)) {
                bean.addPropertyValue(COUNT_ATTR, Integer.valueOf(count));
            }

        }
    }

    private static class SetOfBeansGeneratorBeanDefinitionParser extends AbstractSingleBeanDefinitionParser implements BeanDefinitionParser {
        @SuppressWarnings("unchecked")
        @Override
        protected Class getBeanClass(Element element) {
            return UsurperSetFactoryBean.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String usurpedClass = element.getAttribute(CLASS_ATTR);
            bean.addPropertyValue(USURPED_CLASS_NAME_PROP, usurpedClass);

            String setupRef = element.getAttribute(SETUP_REF_ATTR);
            if (StringUtils.hasText(setupRef)) {
                bean.addPropertyReference(USURPER_GENERATOR_SETUP_PROP, setupRef);
            }
            String count = element.getAttribute(COUNT_ATTR);
            if (StringUtils.hasText(count)) {
                bean.addPropertyValue(COUNT_ATTR, Integer.valueOf(count));
            }
        }
    }

    private static class SetupBeanDefinitionParser extends AbstractSingleBeanDefinitionParser implements BeanDefinitionParser {
        @SuppressWarnings("unchecked")
        @Override
        protected Class getBeanClass(Element element) {
            return UsurperGeneratorSetupFactoryBean.class;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String parentSetup = element.getAttribute(SETUP_PARENT_SETUP);
            if (StringUtils.hasText(parentSetup)) {
                bean.addPropertyReference(Conventions.attributeNameToPropertyName(SETUP_PARENT_SETUP), parentSetup);
            }

            String arrayHandler = element.getAttribute(SETUP_ARRAY_HANDLER_ATTR);
            String arrayHandlerRef = element.getAttribute(SETUP_ARRAY_HANDLER_REF_ATTR);
            if (StringUtils.hasText(arrayHandler)) {
                bean.addPropertyValue(Conventions.attributeNameToPropertyName(SETUP_ARRAY_HANDLER_ATTR) + "Name", arrayHandler);
            } else if (StringUtils.hasText(arrayHandlerRef)) {
                bean.addPropertyReference(Conventions.attributeNameToPropertyName(SETUP_ARRAY_HANDLER_ATTR), arrayHandlerRef);
            }

            String enumHandler = element.getAttribute(SETUP_ENUM_HANDLER_ATTR);
            String enumHandlerRef = element.getAttribute(SETUP_ENUM_HANDLER_REF_ATTR);
            if (StringUtils.hasText(enumHandler)) {
                bean.addPropertyValue(Conventions.attributeNameToPropertyName(SETUP_ENUM_HANDLER_ATTR + "Name"), enumHandler);
            } else if (StringUtils.hasText(enumHandlerRef)) {
                bean.addPropertyReference(Conventions.attributeNameToPropertyName(SETUP_ENUM_HANDLER_ATTR), enumHandlerRef);
            }

            String onMissingHandlers = element.getAttribute(SETUP_ON_MISSING_HANDLERS_ATTR);
            if (StringUtils.hasText(onMissingHandlers)) {
                bean.addPropertyValue(Conventions.attributeNameToPropertyName(SETUP_ON_MISSING_HANDLERS_ATTR), onMissingHandlers.toUpperCase());
            }

            String propertyWritingMechanism = element.getAttribute(SETUP_PROPERTY_WRITING_MECHANISM_ATTR);
            if (StringUtils.hasText(propertyWritingMechanism)) {
                bean.addPropertyValue(Conventions.attributeNameToPropertyName(SETUP_PROPERTY_WRITING_MECHANISM_ATTR), propertyWritingMechanism.toUpperCase());
            }
            String countCallback = element.getAttribute(SETUP_COUNT_CALLBACK_ATTR);
            String countCallbackRef = element.getAttribute(SETUP_COUNT_CALLBACK_REF_ATTR);
            if (StringUtils.hasText(countCallback)) {
                bean.addPropertyReference(Conventions.attributeNameToPropertyName(SETUP_COUNT_CALLBACK_ATTR + "Name"), countCallback);
            } else if (StringUtils.hasText(countCallbackRef)) {
                bean.addPropertyReference(Conventions.attributeNameToPropertyName(SETUP_COUNT_CALLBACK_ATTR), countCallbackRef);
            }

            List propertyTypeHandlersElements = DomUtils.getChildElementsByTagName(element, PROPERTY_TYPE_HANDLER_TAG);
            ManagedMap propertyTypeHandlersMap = new ManagedMap(propertyTypeHandlersElements.size());
            for (int i = 0; i < propertyTypeHandlersElements.size(); i++) {
                Element propertyTypeHandlerElement = (Element) propertyTypeHandlersElements.get(i);
                String propertyType = propertyTypeHandlerElement.getAttribute(PROPERTY_TYPE_HANDLER_PROPERTY_TYPE_ATTR);
                String handlerClass = propertyTypeHandlerElement.getAttribute(PROPERTY_TYPE_HANDLER_HANDLER_CLASS_ATTR);
                propertyTypeHandlersMap.put(propertyType, handlerClass);
            }
            bean.addPropertyValue(Conventions.attributeNameToPropertyName(PROPERTY_TYPE_HANDLER_TAG) + "sClassNames", propertyTypeHandlersMap);

            List propertyTypeHandlersRefElements = DomUtils.getChildElementsByTagName(element, PROPERTY_TYPE_HANDLER_REF_TAG);
            ManagedSet propertyTypeHandlersRefSet = new ManagedSet(propertyTypeHandlersRefElements.size());
            for (int i = 0; i < propertyTypeHandlersRefElements.size(); i++) {
                Element propertyTypeHandlersRefElement = (Element) propertyTypeHandlersRefElements.get(i);
                // String propertyType = propertyTypeHandlersRefElement.getAttribute(PROPERTY_TYPE_HANDLER_PROPERTY_TYPE_ATTR);
                String handlerRef = propertyTypeHandlersRefElement.getAttribute(PROPERTY_TYPE_HANDLER_HANDLER_REF_ATTR);
                RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(handlerRef);
                propertyTypeHandlersRefSet.add(runtimeBeanReference);
            }
            bean.addPropertyValue(Conventions.attributeNameToPropertyName(PROPERTY_TYPE_HANDLER_TAG) + "s", propertyTypeHandlersRefSet);

            List specificPropertyHandlerElements = DomUtils.getChildElementsByTagName(element, SPECIFIC_PROPERTY_HANDLER_TAG);
            ManagedMap specificPropertyHandlersMap = new ManagedMap(specificPropertyHandlerElements.size());
            for (int i = 0; i < specificPropertyHandlerElements.size(); i++) {
                Element specificPropertyElement = (Element) specificPropertyHandlerElements.get(i);
                String targetClass = specificPropertyElement.getAttribute(SPECIFIC_PROPERTY_HANDLER_TARGET_CLASS_ATTR);
                String targetProperty = specificPropertyElement.getAttribute(SPECIFIC_PROPERTY_HANDLER_TARGET_PROPERTY_ATTR);
                String handlerClass = specificPropertyElement.getAttribute(SPECIFIC_PROPERTY_HANDLER_HANDLER_CLASS_ATTR);
                specificPropertyHandlersMap.put(targetClass + "." + targetProperty, handlerClass);
            }
            bean.addPropertyValue(Conventions.attributeNameToPropertyName(SPECIFIC_PROPERTY_HANDLER_TAG) + "sClassNames", specificPropertyHandlersMap);

            List specificPropertyHandlerRefElements = DomUtils.getChildElementsByTagName(element, SPECIFIC_PROPERTY_HANDLER_REF_TAG);
            ManagedSet specificPropertyHandlersRefSet = new ManagedSet(specificPropertyHandlerRefElements.size());
            for (int i = 0; i < specificPropertyHandlerRefElements.size(); i++) {
                Element specificPropertyRefElement = (Element) specificPropertyHandlerRefElements.get(i);
                // String targetClass = specificPropertyRefElement.getAttribute(SPECIFIC_PROPERTY_HANDLER_TARGET_CLASS_ATTR);
                // String targetProperty = specificPropertyRefElement.getAttribute(SPECIFIC_PROPERTY_HANDLER_TARGET_PROPERTY_ATTR);
                String handlerRef = specificPropertyRefElement.getAttribute(SPECIFIC_PROPERTY_HANDLER_HANDLER_REF_ATTR);
                RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(handlerRef);
                specificPropertyHandlersRefSet.add(runtimeBeanReference);
            }
            bean.addPropertyValue(Conventions.attributeNameToPropertyName(SPECIFIC_PROPERTY_HANDLER_TAG) + "s", specificPropertyHandlersRefSet);
        }
    }
}