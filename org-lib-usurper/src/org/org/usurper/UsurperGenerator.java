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

package org.org.usurper;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.org.usurper.handlers.IHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.handlers.exceptions.NoHandlerDefinedException;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.ITargetDefinition;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.model.SpecificPropertyDefinition;
import org.org.usurper.setup.IUsurperGeneratorSetup;
import org.org.usurper.setup.ImmutableUsurperGeneratorSetup;
import org.org.usurper.setup.UsurperGeneratorSetup;
import org.org.usurper.setup.constants.OnMissingHandlers;
import org.org.usurper.setup.constants.PropertyWritingMechanism;
import org.org.usurper.utils.ReflectionUtils;

/**
 * This class defines a UsurperGenerator generator
 * 
 * @author pagregoire
 */
public class UsurperGenerator<T> {

    private final Class<? extends T> usurpatedClass;
    private Class<?>[] constructorParameterTypes = new Class<?>[0];
    private ImmutableUsurperGeneratorSetup setup;

    /**
     * This constructor creates an UsurperGenerator for the given Bean class.<br>
     * It creates a default UsurperGeneratorSetup.
     * 
     * @param usurpatedClass
     *            the generated class
     * @see UsurperGeneratorSetup
     */
    public UsurperGenerator(Class<? extends T> usurpatedClass) {
        this(usurpatedClass, new UsurperGeneratorSetup());
    }

    /**
     * This constructor is the same as the one-argument one, except that you can specify whether you want to ignore undefined handlers.<br>
     * <code>
     *  new UsurperGenerator&lt;T&gt;(Class&lt;T&gt; valueObjectClass, UsurperGenerator.SKIP_MISSING_HANDLERS)
     * </code><br>
     * or<br>
     * <code>
     * new UsurperGenerator&lt;T&gt;(Class&lt;T&gt; valueObjectClass, UsurperGenerator.FAIL_ON_MISSING_HANDLERS)
     * </code>
     * 
     * @param valueObjectClass
     * @param onMissingHandlers
     * @deprecated prefer the use of the UsurperGeneratorSetup if you need to override the default behaviour
     */
    @SuppressWarnings("unchecked")
    public UsurperGenerator(Class<? extends T> usurpatedClass, OnMissingHandlers onMissingHandlers) {
        UsurperGeneratorSetup mutableSetup = new UsurperGeneratorSetup();
        mutableSetup.onMissingHandlers(onMissingHandlers);
        this.setup = mutableSetup.getImmutable();
        this.usurpatedClass = usurpatedClass;
    }

    /**
     * This constructor creates an UsurperGenerator for the given Bean class.<br>
     * It takes a specific UsurperGeneratorSetup as a parameter.
     * 
     * @param usurpatedClass
     *            the generated class
     * @see UsurperGeneratorSetup
     */
    public UsurperGenerator(Class<? extends T> usurpatedClass, IUsurperGeneratorSetup usurperGeneratorSetup) {
        this.replaceSetup(usurperGeneratorSetup);
        this.usurpatedClass = usurpatedClass;
    }

    /**
     * This method generates an usurper object of the type specified as Generic for the UsurperGenerator instance.
     * 
     * @return an usurper object
     */
    public T generateUsurper() {
        T valueObject = null;

        try {
            valueObject = createInstance();

            if (valueObject == null) {
                throw new UsurperException("Can not find a usable constructor for bean:" + this.usurpatedClass.getName());
            }

            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(this.usurpatedClass).getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (!propertyDescriptor.getName().equals("class")) {
                    String propertyName = propertyDescriptor.getName();
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    Method setter = propertyDescriptor.getWriteMethod();
                    // if no setter method is available, then don't set anything
                    // TODO provide a way to set privately accessible fields,
                    // but this is not top priority.
                    if (setter != null) {
                        IHandler handler = getHandler(propertyName, propertyType);
                        Object object = null;

                        if (handler == null) {
                            if (this.setup.getOnMissingHandlers().equals(OnMissingHandlers.FAIL)) {
                                throw new NoHandlerDefinedException("No handler defined for property: " + propertyName + "(" + propertyType.getName() + ") in object of type: " + this.usurpatedClass.getName() + ".");
                            }
                        } else {
                            HandledBeanProperty handledBeanProperty = new HandledBeanProperty(valueObject, propertyType, propertyName, this.setup);

                            try {
                                object = handler.handle(handledBeanProperty);
                            } catch (NoHandlerDefinedException nhde) {
                                // if a bean property handler has thrown a
                                // missing handler error
                                // we should rethrow or swallow depending on the
                                // defined policy
                                if (this.setup.getOnMissingHandlers().equals(OnMissingHandlers.FAIL)) {
                                    throw nhde;
                                }
                            }
                        }

                        if (object != null) {
                            try {
                                switch (this.setup.getPropertyWritingMechanism()) {
                                case USE_SETTERS:
                                    ReflectionUtils.setProperty(valueObject, setter, object);
                                    break;
                                case MODIFY_ATTRIBUTES_DIRECTLY:
                                    ReflectionUtils.setProperty(valueObject, propertyName, object);
                                    break;
                                default:
                                    break;
                                }

                            } catch (IllegalAccessException e) {
                                throw new UsurperException("Can not access bean property (illegal access):" + this.usurpatedClass.getName() + "." + propertyName, e);
                            } catch (IllegalArgumentException e) {
                                throw new UsurperException("Wrong parameter for the setter of bean property:" + this.usurpatedClass.getName() + "." + propertyName, e);
                            } catch (InvocationTargetException e) {
                                throw new UsurperException("Exception while setting value of bean property:" + this.usurpatedClass.getName() + "." + propertyName, e);
                            } catch (SecurityException e) {
                                throw new UsurperException("Impossible to set the value of bean property:" + this.usurpatedClass.getName() + "." + propertyName, e);
                            } catch (NoSuchFieldException e) {
                                throw new UsurperException("Specified bean property does not exist:" + this.usurpatedClass.getName() + "." + propertyName, e);
                            }
                        }
                    }
                }
            }

        } catch (IntrospectionException e) {
            throw new UsurperException("Can not get Bean Info from bean:" + this.usurpatedClass.getName(), e);
        }

        return valueObject;
    }

    /**
     * @param valueObject
     * @return
     * @throws SecurityException
     * @throws UsurperException
     */
    private T createInstance() throws SecurityException, UsurperException {
        T valueObject = null;
        if (this.constructorParameterTypes.length != 0) {
            try {
                Constructor<? extends T> constructor = this.usurpatedClass.getConstructor(this.constructorParameterTypes);
                valueObject = createInstance(valueObject, constructor);
            } catch (NoSuchMethodException e) {
                throw new UsurperException("Can not instantiate bean:" + this.usurpatedClass.getName(), e);
            }
        } else {
            Constructor<? extends T> constructor = choseConstructor(this.usurpatedClass);
            valueObject = createInstance(valueObject, constructor);
        }
        return valueObject;
    }

    @SuppressWarnings("unchecked")
    private Constructor<? extends T> choseConstructor(Class<? extends T> usurpatedClass) {
        Constructor<? extends T> result = null;
        Set<Constructor> constructors = new TreeSet<Constructor>(new Comparator<Constructor>() {
            public int compare(Constructor o1, Constructor o2) {
                int result = 0;
                result = (o1.getParameterTypes().length > o2.getParameterTypes().length) ? -1 : result;
                result = (o1.getParameterTypes().length < o2.getParameterTypes().length) ? 1 : result;
                return -result;
            }
        });
        for (Constructor constructor : Arrays.asList(usurpatedClass.getConstructors())) {
            constructors.add(constructor);
        }
        for (Constructor<? extends T> constructor : constructors) {
            boolean constructorOk = true;
            for (Class<?> parameterType : constructor.getParameterTypes()) {
                IHandler handler = getHandler(parameterType);
                if (handler == null) {
                    constructorOk = false;
                    break;
                }
            }
            if (constructorOk) {
                result = constructor;
                break;
            }
        }
        return result;
    }

    /**
     * @param valueObject
     * @param constructor
     * @return
     * @throws UsurperException
     */
    private T createInstance(T valueObject, Constructor<? extends T> constructor) throws UsurperException {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] constructorArguments = new Object[parameterTypes.length];
        int argCounter = 0;
        for (Class<?> parameterType : parameterTypes) {
            IHandler handler = getHandler(parameterType);
            if (handler == null) {
                break;
            } else {
                HandledConstructorArg handledConstructorArg = new HandledConstructorArg(constructor, parameterType, ++argCounter, this.setup);
                constructorArguments[argCounter - 1] = handler.handle(handledConstructorArg);
            }
        }
        if ((argCounter) == constructorArguments.length && constructorArguments.length == parameterTypes.length) {
            try {
                valueObject = constructor.newInstance(constructorArguments);
            } catch (Exception e) {
                throw new UsurperException("Can not instantiate bean:" + this.usurpatedClass.getName(), e);
            }
        }
        return valueObject;
    }

    private IHandler getHandler(Class<?> propertyType) {
        return getHandler(null, propertyType);
    }

    private IHandler getHandler(String propertyName, Class<?> propertyType) {
        IHandler handler;
        SpecificPropertyDefinition specificPropertyDefinition = new SpecificPropertyDefinition(this.usurpatedClass, propertyName);

        if (propertyType.isArray()) {
            handler = this.setup.getArrayHandler();
        } else if (propertyType.isEnum()) {
            handler = this.setup.getEnumHandler();
        } else if ((propertyName != null) && (this.setup.hasSpecificPropertyHandler(specificPropertyDefinition))) {
            handler = this.setup.getSpecificPropertyHandler(specificPropertyDefinition);
        } else {
            handler = this.setup.getPropertyTypeHandler(new PropertyTypeDefinition(propertyType));
        }

        return handler;
    }

    /**
     * Utility method that generates a list of usurpers of the type specified as Generic for the UsurperGenerator instance.
     * 
     * @param number
     *            the quantity of usurpers to be generated
     * @return a List of usurpers.
     */
    public List<T> generateUsurperList(int number) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < number; i++) {
            list.add(generateUsurper());
        }
        return list;
    }

    /**
     * Utility method that generates a list of usurpers of the type specified as Generic for the UsurperGenerator instance.
     * 
     * @param number
     *            the quantity of usurpers to be generated
     * @return a List of usurpers.
     */
    public Set<T> generateUsurperSet(int number) {
        Set<T> set = new HashSet<T>();
        for (int i = 0; i < number; i++) {
            set.add(generateUsurper());
        }
        return set;
    }

    /**
     * This methods allows the replacement of the setup object.
     * 
     * @param usurperGeneratorSetup
     */
    public void replaceSetup(IUsurperGeneratorSetup usurperGeneratorSetup) {
        ImmutableUsurperGeneratorSetup immutableUsurperGeneratorSetup = null;
        if (usurperGeneratorSetup instanceof UsurperGeneratorSetup) {
            immutableUsurperGeneratorSetup = ((UsurperGeneratorSetup) usurperGeneratorSetup).getImmutable();
        } else if (usurperGeneratorSetup instanceof ImmutableUsurperGeneratorSetup) {
            immutableUsurperGeneratorSetup = (ImmutableUsurperGeneratorSetup) usurperGeneratorSetup;
        } else {
            throw new IllegalArgumentException(usurperGeneratorSetup.getClass().getName() + " is not a valid setup instance. Valid implementations are:\n\t-" + UsurperGeneratorSetup.class.getName() + "\n\t-" + ImmutableUsurperGeneratorSetup.class.getName());
        }
        this.setup = immutableUsurperGeneratorSetup;
    }

    /**
     * This methods returns the current setup object
     * 
     * @return an immutable setup object
     */
    public ImmutableUsurperGeneratorSetup getSetup() {
        return this.setup;
    }

    /**
     * Setter for the flag specifying if the undefined handlers must be ignored or trigger a failure.
     * 
     * @param onMissingHandlers
     *            The value to set.
     * @see OnMissingHandlers
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public void onMissingHandlers(OnMissingHandlers onMissingHandlers) {
        UsurperGeneratorSetup mutableSetup = new UsurperGeneratorSetup(this.setup);
        mutableSetup.onMissingHandlers(onMissingHandlers);
        this.setup = mutableSetup.getImmutable();
    }

    /**
     * This method allows the user to register a Handler for Properties of determinated java types.<br>
     * Only one handler can be registered for a given java type at a time.<br>
     * Registering a handler when one is already registered for a given type overrides the preceding one.<br>
     * 
     * @param typeHandler
     *            the handler to register (AbstractPropertyTypeHandler)
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public void registerPropertyTypeHandler(AbstractPropertyTypeHandler typeHandler) {
        UsurperGeneratorSetup mutableSetup = new UsurperGeneratorSetup(this.setup);
        mutableSetup.registerPropertyTypeHandler(typeHandler);
        this.setup = mutableSetup.getImmutable();
    }

    /**
     * This method allows the user to register many Handlers for Properties of determinated java types.<br>
     * Only one handler can be registered for a given java type at a time.<br>
     * Registering a handler when one is already registered for a given type overrides the preceding one.<br>
     * 
     * @param typeHandlers
     *            the handler to register (Set&lt;AbstractPropertyTypeHandler&gt;)
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public void registerPropertyTypeHandlers(Set<AbstractPropertyTypeHandler> typeHandlers) {
        UsurperGeneratorSetup mutableSetup = new UsurperGeneratorSetup(this.setup);
        mutableSetup.registerPropertyTypeHandlers(typeHandlers);
        this.setup = mutableSetup.getImmutable();
    }

    /**
     * This method tests if a handler is registered for a given Java Type.
     * 
     * @param type
     *            the java class of the type to test
     * @return true if one is registered, false otherwise
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public boolean hasPropertyTypeHandler(Class<?> type) {
        return this.setup.hasPropertyTypeHandler(new PropertyTypeDefinition(type));
    }

    /**
     * This method retrieves the Handler for Properties of a given java type.
     * 
     * @param handledType
     *            the java type
     * @return the registered handler or null if none is defined.
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public AbstractPropertyTypeHandler getPropertyTypeHandler(Class<?> handledType) {
        return this.setup.getPropertyTypeHandler(new PropertyTypeDefinition(handledType));
    }

    /**
     * This method allows the user to register a Handler for a given property.<br>
     * Only one handler can be registered for a given property at a time.<br>
     * Registering a handler when one is already registered for a given property overrides the preceding one.<br>
     * 
     * @param propertyHandler
     *            the handler to register (AbstractSpecificPropertyHandler)
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public void registerSpecificPropertyHandler(AbstractSpecificPropertyHandler propertyHandler) {
        UsurperGeneratorSetup mutableSetup = new UsurperGeneratorSetup(this.setup);
        mutableSetup.getSpecificPropertyHandlersMap().put(propertyHandler.getTargetProperty(), propertyHandler);
        this.setup = mutableSetup.getImmutable();
    }

    /**
     * This method tests if a handler is registered for a given Property of a given Class.
     * 
     * @param usurpatedClass
     * @param handledProperty
     * @return true if one is registered, false otherwise
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public boolean hasSpecificPropertyHandler(Class<?> usurpatedClass, String handledProperty) {
        return this.setup.getSpecificPropertyHandlersMap().containsKey(usurpatedClass.getName() + "." + handledProperty);
    }

    /**
     * This method retrieves the Handler for a given Property of a given Class.
     * 
     * @param usurpatedClass
     * @param handledProperty
     * @return the registered property handler or null if none is defined.
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public AbstractSpecificPropertyHandler getSpecificPropertyHandler(Class<?> usurpatedClass, String handledProperty) {
        return this.setup.getSpecificPropertyHandlersMap().get(usurpatedClass.getName() + "." + handledProperty);
    }

    /**
     * This methods return all the handlers.
     * 
     * @return
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public Map<ITargetDefinition, IHandler> getAllHandlers() {
        return this.setup.getAllHandlers();
    }

    /**
     * This methods sets handlers. The Map must contains pairs of :
     * <ul>
     * <li>String,AbstractSpecificPropertyHandler
     * <li>Class,AbstractPropertyTypeHandler
     * </ul>
     * Otherwise, it will throw a ClassCastException.
     * 
     * @param handlers
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public void setAllHandlers(Map<ITargetDefinition, IHandler> handlers) {
        UsurperGeneratorSetup mutableSetup = new UsurperGeneratorSetup(this.setup);
        mutableSetup.setAllHandlers(handlers);
        this.setup = mutableSetup.getImmutable();
    }

    /**
     * @param propertyWritingMechanism
     * @deprecated prefer the use of the UsurperGeneratorSetup
     * @see UsurperGeneratorSetup
     */
    public void usePropertyWritingMechanism(PropertyWritingMechanism propertyWritingMechanism) {
        UsurperGeneratorSetup mutableSetup = new UsurperGeneratorSetup(this.setup);
        mutableSetup.usePropertyWritingMechanism(propertyWritingMechanism);
        this.setup = mutableSetup.getImmutable();
    }

    /**
     * @param constructorParameterTypes
     */
    public void useConstructor(Class<?>... constructorParameterTypes) {
        this.constructorParameterTypes = constructorParameterTypes;
    }

}