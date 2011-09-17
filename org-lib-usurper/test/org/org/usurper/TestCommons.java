package org.org.usurper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.org.usurper.dummydomain.BigDecimalHandler;
import org.org.usurper.dummydomain.BigIntegerHandler;
import org.org.usurper.dummydomain.DummyCountCallback;
import org.org.usurper.dummydomain.DummyVO;
import org.org.usurper.dummydomain.SpecificIntegerFieldHandler;
import org.org.usurper.dummydomain.SpecificStringFieldHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.handlers.basic.ArrayHandler;
import org.org.usurper.handlers.basic.EnumHandler;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.model.SpecificPropertyDefinition;
import org.org.usurper.setup.IUsurperGeneratorSetup;
import org.org.usurper.setup.constants.OnMissingHandlers;
import org.org.usurper.setup.constants.PropertyWritingMechanism;

public final class TestCommons {

    private TestCommons() {
    }

    public final static class NullPropertyException extends Exception {

        /**
         * 
         */
        private static final long serialVersionUID = 2281733649024485007L;

        /**
         * 
         */
        public NullPropertyException() {
            super();
        }

        /**
         * @param message
         * @param cause
         */
        public NullPropertyException(String message, Throwable cause) {
            super(message, cause);
        }

        /**
         * @param message
         */
        public NullPropertyException(String message) {
            super(message);
        }

        /**
         * @param causetest
         */
        public NullPropertyException(Throwable cause) {
            super(cause);
        }

    }

    public final static class BadSetupException extends Exception {

        /**
         * 
         */
        private static final long serialVersionUID = -5417693717950210139L;

        /**
         * 
         */
        public BadSetupException() {
            super();
        }

        /**
         * @param message
         * @param cause
         */
        public BadSetupException(String message, Throwable cause) {
            super(message, cause);
        }

        /**
         * @param message
         */
        public BadSetupException(String message) {
            super(message);
        }

        /**
         * @param causetest
         */
        public BadSetupException(Throwable cause) {
            super(cause);
        }

    }

    public static final String HANDLED_STRING = "HANDLED_STRING";
    public static final BigInteger BIG_INTEGER_VALUE = new BigInteger("2");
    public static List<BigInteger> BIG_INTEGER_VALUES_LIST;
    public static Set<AbstractPropertyTypeHandler> PROPERTY_TYPE_HANDLERS_SET;
    public static Set<AbstractSpecificPropertyHandler> SPECIFIC_PROPERTY_HANDLERS_SET;
    static {
        List<BigInteger> tmpList = BIG_INTEGER_VALUES_LIST = new ArrayList<BigInteger>();
        tmpList.add(new BigInteger("1"));
        tmpList.add(new BigInteger("2"));
        tmpList.add(new BigInteger("3"));
        BIG_INTEGER_VALUES_LIST = Collections.unmodifiableList(tmpList);
        Set<AbstractPropertyTypeHandler> tmpList2 = new HashSet<AbstractPropertyTypeHandler>();
        tmpList2.add(new BigDecimalHandler(new PropertyTypeDefinition(BigDecimal.class)));
        tmpList2.add(new BigIntegerHandler(new PropertyTypeDefinition(BigInteger.class)));
        PROPERTY_TYPE_HANDLERS_SET = tmpList2;
        Set<AbstractSpecificPropertyHandler> tmpList3 = new HashSet<AbstractSpecificPropertyHandler>();
        tmpList3.add(new SpecificIntegerFieldHandler(new SpecificPropertyDefinition(DummyVO.class, "integerField")));
        tmpList3.add(new SpecificStringFieldHandler(new SpecificPropertyDefinition(DummyVO.class, "stringField")));
        SPECIFIC_PROPERTY_HANDLERS_SET = tmpList3;
    }

    public static final DummyCountCallback DUMMY_COLLECTION_COUNT_CALLBACK = new DummyCountCallback();
    public static final EnumHandler ENUM_HANDLER = new EnumHandler();
    public static final ArrayHandler ARRAY_HANDLER = new ArrayHandler();

    public static void auditVO(Object valueObject) throws NullPropertyException {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(valueObject.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (!propertyDescriptor.getName().equals("class")) {
                    String propertyName = propertyDescriptor.getName();
                    try {
                        Object result = getProperty(valueObject, propertyName);
                        if (result == null) {
                            throw new NullPropertyException("Property <" + propertyName + "> should not be null");
                        }
                        if (result.getClass().isArray()) {
                            if (Array.getLength(result) == 0) {
                                throw new NullPropertyException("Array Property <" + propertyName + "> should not be empty");
                            }
                        }
                        if (result instanceof Collection) {
                            if (Collections.frequency((Collection<?>) result, null) != 0) {
                                throw new NullPropertyException("Collection Property <" + propertyName + "> should not contain any null item");
                            }
                        }
                        if (result instanceof Map) {
                            if (Collections.frequency(((Map<?, ?>) result).keySet(), null) != 0) {
                                throw new NullPropertyException("Map Property <" + propertyName + "> should not contain any null key");
                            }
                            if (Collections.frequency(((Map<?, ?>) result).values(), null) != 0) {
                                throw new NullPropertyException("Map Property <" + propertyName + "> should not contain any null value");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        System.err.println("cannot access bean property (illegal access) :" + valueObject.getClass().getName() + "." + propertyName);
                    } catch (NoSuchFieldException e) {
                        System.err.println("no such field :" + valueObject.getClass().getName() + "." + propertyName);
                    }
                }
            }
        } catch (IntrospectionException e) {
            throw new UsurperException("cannot get Bean Info from bean :" + valueObject.getClass().getName(), e);
        }
    }

    public static Object getProperty(Object object, String propertyName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);
        Object value = field.get(object);
        return value;
    }

    public static void auditSetup(IUsurperGeneratorSetup usurperGeneratorSetup) throws BadSetupException {
        auditParameter("Array handler should be an " + ArrayHandler.class.getName(), usurperGeneratorSetup.getArrayHandler().getClass().getName().equals(ArrayHandler.class.getName()));
        auditParameter("Enum handler should be an " + EnumHandler.class.getName(), usurperGeneratorSetup.getEnumHandler().getClass().getName().equals(EnumHandler.class.getName()));
        auditParameter("Collection callback should be a " + DummyCountCallback.class.getName(), usurperGeneratorSetup.getCountCallback().getClass().getName().equals(DummyCountCallback.class.getName()));
        auditParameter("On missing handlers behaviour should be " + OnMissingHandlers.SKIP, OnMissingHandlers.SKIP.equals(usurperGeneratorSetup.getOnMissingHandlers()));
        auditParameter("Property writing mechanism should be " + PropertyWritingMechanism.USE_SETTERS, PropertyWritingMechanism.USE_SETTERS.equals(usurperGeneratorSetup.getPropertyWritingMechanism()));

        assertNotNull("Property Type Handlers should not be null", usurperGeneratorSetup.getPropertyTypeHandlersMap());
        assertNotNull("Specific Property Handlers should not be null", usurperGeneratorSetup.getSpecificPropertyHandlersMap());
        // Test the first set of handlers
        final String expectedPropertyTypeHandlerClassName = BigDecimalHandler.class.getName();
        auditParameter("Property Type handler for BigDecimal should be: " + expectedPropertyTypeHandlerClassName, usurperGeneratorSetup.getPropertyTypeHandlersMap().get(new PropertyTypeDefinition(BigDecimal.class)).getClass().getName().equals(expectedPropertyTypeHandlerClassName));
        auditParameter("Specific Property handler for DummyVO.integerField should be: " + SpecificIntegerFieldHandler.class.getName(), usurperGeneratorSetup.getSpecificPropertyHandlersMap().get(new SpecificPropertyDefinition(DummyVO.class, "integerField")).getClass().getName().equals(SpecificIntegerFieldHandler.class.getName()));
        // Test the second set of handlers
        final String expectedPropertyTypeHandlerClassName2 = BigIntegerHandler.class.getName();
        auditParameter("Property Type handler for BigInteger should be: " + expectedPropertyTypeHandlerClassName2, usurperGeneratorSetup.getPropertyTypeHandlersMap().get(new PropertyTypeDefinition(BigInteger.class)).getClass().getName().equals(expectedPropertyTypeHandlerClassName2));
        auditParameter("Specific Property handler for DummyVO.integerField should be: " + SpecificStringFieldHandler.class.getName(), usurperGeneratorSetup.getSpecificPropertyHandlersMap().get(new SpecificPropertyDefinition(DummyVO.class, "stringField")).getClass().getName().equals(SpecificStringFieldHandler.class.getName()));
    }

    private static void assertNotNull(String message, Object toBeTested) throws BadSetupException {
        if (toBeTested == null) {
            throw new BadSetupException(message);
        }
    }

    private static void auditParameter(String message, Boolean clauseOk) throws BadSetupException {
        if (!clauseOk) {
            throw new BadSetupException(message);
        }
    }
}
