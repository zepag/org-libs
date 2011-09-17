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

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.org.usurper.TestCommons.NullPropertyException;
import org.org.usurper.dummydomain.DummyVOWithVOArrays;
import org.org.usurper.dummydomain.DummyVOWithVOCollections;
import org.org.usurper.dummydomain.DummyWithChildVO;
import org.org.usurper.dummydomain.FcknVO;
import org.org.usurper.dummydomain.IImmutableDummyVO;
import org.org.usurper.dummydomain.ImmutableDummyVO;
import org.org.usurper.dummydomain.ImmutableDummyWithChildVO;
import org.org.usurper.dummydomain.ImmutableDummyWithChildVOArray;
import org.org.usurper.dummydomain.ImmutableDummyWithChildVOCollections;
import org.org.usurper.dummydomain.SonOfDummyWithChildVO;
import org.org.usurper.dummydomain.SonOfSonOfDummyWithChildVO;
import org.org.usurper.handlers.additional.ListOfValuesSpecificPropertyHandler;
import org.org.usurper.handlers.additional.ValueObjectPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.SpecificPropertyDefinition;

/**
 * Tests former behaviour (with deprecated method calls).
 * 
 * @author pagregoire
 * @since 1.1.0
 */
@SuppressWarnings("deprecation")
public class UsurperGeneratorDeprecatedTest extends TestCase {

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGenerateImmutableObject() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyVO> immutableDummyVOUsurper = new UsurperGenerator<ImmutableDummyVO>(ImmutableDummyVO.class);
        immutableDummyVOUsurper.registerPropertyTypeHandler(new AbstractPropertyTypeHandler(String.class) {

            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.HANDLED_STRING;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.HANDLED_STRING;
            }

        });

        // GENERATE USURPER
        IImmutableDummyVO object = immutableDummyVOUsurper.generateUsurper();
        assertEquals("The shortest constructor should be used.", ImmutableDummyVO.SHORT_CONTRUCTOR, object.getStringField());

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateImmutableWithChildObject() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyWithChildVO> immutableDummyWithChildVOUsurper = new UsurperGenerator<ImmutableDummyWithChildVO>(ImmutableDummyWithChildVO.class);

        // REGISTER AN HANDLER FOR SPECIFIC VALUE OBJECTS
        immutableDummyWithChildVOUsurper.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(DummyWithChildVO.class, SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // GENERATE USURPER
        ImmutableDummyWithChildVO object = immutableDummyWithChildVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateImmutableWithChildObjectArray() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyWithChildVOArray> immutableDummyWithChildVOArrayUsurper = new UsurperGenerator<ImmutableDummyWithChildVOArray>(ImmutableDummyWithChildVOArray.class);

        // REGISTER AN HANDLER FOR SPECIFIC VALUE OBJECTS
        immutableDummyWithChildVOArrayUsurper.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(DummyWithChildVO.class, SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // GENERATE USURPER
        ImmutableDummyWithChildVOArray object = immutableDummyWithChildVOArrayUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateImmutableWithChildObjectCollections() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyWithChildVOCollections> immutableDummyWithChildVOCollectionsUsurper = new UsurperGenerator<ImmutableDummyWithChildVOCollections>(ImmutableDummyWithChildVOCollections.class);

        // REGISTER AN HANDLER FOR SPECIFIC VALUE OBJECTS
        immutableDummyWithChildVOCollectionsUsurper.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(DummyWithChildVO.class, SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // GENERATE USURPER
        ImmutableDummyWithChildVOCollections object = immutableDummyWithChildVOCollectionsUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateImmutableObjectWithChosenConstructor() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyVO> immutableDummyVOUsurper = new UsurperGenerator<ImmutableDummyVO>(ImmutableDummyVO.class);
        immutableDummyVOUsurper.registerPropertyTypeHandler(new AbstractPropertyTypeHandler(String.class) {

            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.HANDLED_STRING;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.HANDLED_STRING;
            }

        });
        immutableDummyVOUsurper.useConstructor(Integer.class, Float.class, Double.class, String.class, Date.class, Long.class, Short.class, Boolean.class, int.class, short.class, long.class, boolean.class, byte.class, char.class, float.class, double.class, Timestamp.class, java.sql.Date.class);
        // GENERATE USURPER
        IImmutableDummyVO object = immutableDummyVOUsurper.generateUsurper();
        assertNotSame("The shortest constructor should not be used.", ImmutableDummyVO.SHORT_CONTRUCTOR, object.getStringField());
        assertEquals("The longest constructor should be used.", TestCommons.HANDLED_STRING, object.getStringField());

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithChilds() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyWithChildVO> dummyVOUsurper = new UsurperGenerator<DummyWithChildVO>(DummyWithChildVO.class);

        // REGISTER AN HANDLER FOR SPECIFIC VALUE OBJECTS
        ValueObjectPropertyTypeHandler valueObjectFieldTypeHandler = new ValueObjectPropertyTypeHandler(SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class);
        dummyVOUsurper.registerPropertyTypeHandler(valueObjectFieldTypeHandler);

        // GENERATE USURPER
        DummyWithChildVO object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithVOCollectionChilds() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVOWithVOCollections> dummyVOUsurper = new UsurperGenerator<DummyVOWithVOCollections>(DummyVOWithVOCollections.class);

        // REGISTER AN HANDLER FOR SPECIFIC VALUE OBJECTS
        dummyVOUsurper.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // GENERATE USURPER
        DummyVOWithVOCollections object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithVOArrayChilds() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVOWithVOArrays> dummyVOUsurper = new UsurperGenerator<DummyVOWithVOArrays>(DummyVOWithVOArrays.class);

        // REGISTER AN HANDLER FOR SPECIFIC VALUE OBJECTS
        dummyVOUsurper.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // GENERATE USURPER
        DummyVOWithVOArrays object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithListOfValuesSpecificPropertyHandler() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<FcknVO> fcknVOUsurper = new UsurperGenerator<FcknVO>(FcknVO.class);

        // REGISTER CUSTOM SPECIFIC FIELD HANDLERS
        fcknVOUsurper.registerSpecificPropertyHandler(new ListOfValuesSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField"), TestCommons.BIG_INTEGER_VALUES_LIST));
        fcknVOUsurper.registerSpecificPropertyHandler(new ListOfValuesSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField2"), TestCommons.BIG_INTEGER_VALUES_LIST));

        // GENERATE USURPER
        List<FcknVO> collection = fcknVOUsurper.generateUsurperList(6);

        Iterator<BigInteger> it = TestCommons.BIG_INTEGER_VALUES_LIST.iterator();
        // VALIDATE RESULTING OBJECT
        for (FcknVO fcknVO : collection) {
            try {
                TestCommons.auditVO(fcknVO);
            } catch (NullPropertyException e) {
                fail(e.getMessage());
            }
            if (!it.hasNext()) {
                it = TestCommons.BIG_INTEGER_VALUES_LIST.iterator();
            }
            BigInteger nextValue = it.next();
            assertEquals("the specificFieldHandler should put the <" + nextValue + "> value for the <bigIntField> field", nextValue, fcknVO.getBigIntField());
            assertEquals("the specificFieldHandler should put the <" + nextValue + "> value for the <bigIntField2> field", nextValue, fcknVO.getBigIntField2());
        }
    }

    public void testGenerateObjectWithSpecificPropertyHandler() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<FcknVO> fcknVOUsurper = new UsurperGenerator<FcknVO>(FcknVO.class);

        // REGISTER CUSTOM SPECIFIC FIELD HANDLERS
        fcknVOUsurper.registerSpecificPropertyHandler(new AbstractSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField")) {
            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.BIG_INTEGER_VALUE;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.BIG_INTEGER_VALUE;
            }
        });
        fcknVOUsurper.registerSpecificPropertyHandler(new AbstractSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField2")) {
            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.BIG_INTEGER_VALUE;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.BIG_INTEGER_VALUE;
            }
        });

        // GENERATE USURPER
        FcknVO object = fcknVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        assertEquals("the specificFieldHandler should put a default value for the field", TestCommons.BIG_INTEGER_VALUE, ((FcknVO) object).getBigIntField());
        assertEquals("the specificFieldHandler should put a default value for the field", TestCommons.BIG_INTEGER_VALUE, ((FcknVO) object).getBigIntField2());
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithPropertyTypeHandler() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<FcknVO> fcknVOUsurper = new UsurperGenerator<FcknVO>(FcknVO.class);

        // REGISTER CUSTOM TYPE HANDLER
        fcknVOUsurper.registerPropertyTypeHandler(new AbstractPropertyTypeHandler(BigInteger.class) {
            public Object handle(HandledBeanProperty handledBeanProperty) {
                return new BigInteger(24, new Random());
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return new BigInteger(24, new Random());
            }
        });

        // GENERATE USURPER
        FcknVO object3 = fcknVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object3);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

}