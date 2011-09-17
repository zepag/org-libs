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
import java.util.Set;

import junit.framework.TestCase;

import org.org.usurper.TestCommons.NullPropertyException;
import org.org.usurper.dummydomain.DummyVO;
import org.org.usurper.dummydomain.DummyVOWithArrays;
import org.org.usurper.dummydomain.DummyVOWithCollections;
import org.org.usurper.dummydomain.DummyVOWithEnum;
import org.org.usurper.dummydomain.DummyVOWithVOArrays;
import org.org.usurper.dummydomain.DummyVOWithVOCollections;
import org.org.usurper.dummydomain.DummyWithChildVO;
import org.org.usurper.dummydomain.FcknVO;
import org.org.usurper.dummydomain.IDummyVO;
import org.org.usurper.dummydomain.IImmutableDummyVO;
import org.org.usurper.dummydomain.ImmutableDummyVO;
import org.org.usurper.dummydomain.ImmutableDummyVOWithEnum;
import org.org.usurper.dummydomain.ImmutableDummyWithChildVO;
import org.org.usurper.dummydomain.ImmutableDummyWithChildVOArray;
import org.org.usurper.dummydomain.ImmutableDummyWithChildVOCollections;
import org.org.usurper.dummydomain.PartiallyImmutableDummyVO;
import org.org.usurper.dummydomain.SonOfDummyWithChildVO;
import org.org.usurper.dummydomain.SonOfSonOfDummyWithChildVO;
import org.org.usurper.dummydomain.TelescopicConstructorDummyVO;
import org.org.usurper.handlers.additional.ListOfValuesSpecificPropertyHandler;
import org.org.usurper.handlers.additional.ValueObjectPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractSpecificPropertyHandler;
import org.org.usurper.handlers.exceptions.NoHandlerDefinedException;
import org.org.usurper.model.HandledBeanProperty;
import org.org.usurper.model.HandledConstructorArg;
import org.org.usurper.model.PropertyTypeDefinition;
import org.org.usurper.model.SpecificPropertyDefinition;
import org.org.usurper.setup.UsurperGeneratorSetup;
import org.org.usurper.setup.constants.OnMissingHandlers;

/**
 * @author pagregoire
 */
public class UsurperGeneratorTest extends TestCase {

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testGenerateObject() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVO> dummyVOUsurper = new UsurperGenerator<DummyVO>(DummyVO.class);

        // GENERATE USURPER
        DummyVO object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateImmutableObject() {
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new AbstractPropertyTypeHandler(new PropertyTypeDefinition(String.class)) {

            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.HANDLED_STRING;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.HANDLED_STRING;
            }

        });

        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyVO> immutableDummyVOUsurper = new UsurperGenerator<ImmutableDummyVO>(ImmutableDummyVO.class, usurperGeneratorSetup);

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
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(DummyWithChildVO.class, SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyWithChildVO> immutableDummyWithChildVOUsurper = new UsurperGenerator<ImmutableDummyWithChildVO>(ImmutableDummyWithChildVO.class, usurperGeneratorSetup);

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
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(DummyWithChildVO.class, SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyWithChildVOArray> immutableDummyWithChildVOArrayUsurper = new UsurperGenerator<ImmutableDummyWithChildVOArray>(ImmutableDummyWithChildVOArray.class, usurperGeneratorSetup);

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
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(DummyWithChildVO.class, SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyWithChildVOCollections> immutableDummyWithChildVOCollectionsUsurper = new UsurperGenerator<ImmutableDummyWithChildVOCollections>(ImmutableDummyWithChildVOCollections.class, usurperGeneratorSetup);

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
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new AbstractPropertyTypeHandler(String.class) {

            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.HANDLED_STRING;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.HANDLED_STRING;
            }

        });

        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyVO> immutableDummyVOUsurper = new UsurperGenerator<ImmutableDummyVO>(ImmutableDummyVO.class, usurperGeneratorSetup);
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

    public void testGeneratePartiallyImmutableObject() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<PartiallyImmutableDummyVO> partiallyImmutableDummyVOUsurper = new UsurperGenerator<PartiallyImmutableDummyVO>(PartiallyImmutableDummyVO.class);

        // GENERATE USURPER
        PartiallyImmutableDummyVO object = partiallyImmutableDummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testBestConstructorSelection() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<TelescopicConstructorDummyVO> usurperGenerator = new UsurperGenerator<TelescopicConstructorDummyVO>(TelescopicConstructorDummyVO.class);

        // GENERATE USURPER
        TelescopicConstructorDummyVO object = usurperGenerator.generateUsurper();

        // VALIDATE RESULTING OBJECT
        // THE SHORTEST CONSTRUCTOR WITH HANDLERS AVAILABLE FOR ALL ARGUMENTS SHOULD BE CHOSEN.
        assertNull(object.getInputStream());
        assertNotNull(object.getInteger());
        assertNotNull(object.getString());
    }

    public void testGenerateObjectWithInterface() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<IDummyVO> dummyVOUsurper = new UsurperGenerator<IDummyVO>(DummyVO.class);

        // GENERATE USURPER
        IDummyVO object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateImmutableObjectWithInterface() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<IImmutableDummyVO> immutableDummyVOUsurper = new UsurperGenerator<IImmutableDummyVO>(ImmutableDummyVO.class);

        // GENERATE USURPER
        IImmutableDummyVO object = immutableDummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithEnum() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVOWithEnum> dummyVOUsurper = new UsurperGenerator<DummyVOWithEnum>(DummyVOWithEnum.class);

        // GENERATE USURPER
        DummyVOWithEnum object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateImmutableObjectWithEnum() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<ImmutableDummyVOWithEnum> immutableDummyVOUsurper = new UsurperGenerator<ImmutableDummyVOWithEnum>(ImmutableDummyVOWithEnum.class);

        // GENERATE USURPER
        ImmutableDummyVOWithEnum object = immutableDummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithChilds() {
        // PREPARE USURPER GENERATOR SETUP
        ValueObjectPropertyTypeHandler valueObjectFieldTypeHandler = new ValueObjectPropertyTypeHandler(SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class);
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(valueObjectFieldTypeHandler);

        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyWithChildVO> dummyVOUsurper = new UsurperGenerator<DummyWithChildVO>(DummyWithChildVO.class, usurperGeneratorSetup);

        // GENERATE USURPER
        DummyWithChildVO object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithCollectionChilds() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVOWithCollections> dummyVOUsurper = new UsurperGenerator<DummyVOWithCollections>(DummyVOWithCollections.class);

        // GENERATE USURPER
        DummyVOWithCollections object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithVOCollectionChilds() {
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVOWithVOCollections> dummyVOUsurper = new UsurperGenerator<DummyVOWithVOCollections>(DummyVOWithVOCollections.class, usurperGeneratorSetup);

        // GENERATE USURPER
        DummyVOWithVOCollections object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithArrayChilds() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVOWithArrays> dummyVOUsurper = new UsurperGenerator<DummyVOWithArrays>(DummyVOWithArrays.class);

        // GENERATE USURPER
        DummyVOWithArrays object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithVOArrayChilds() {
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new ValueObjectPropertyTypeHandler(SonOfDummyWithChildVO.class, SonOfSonOfDummyWithChildVO.class));

        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVOWithVOArrays> dummyVOUsurper = new UsurperGenerator<DummyVOWithVOArrays>(DummyVOWithVOArrays.class, usurperGeneratorSetup);

        // GENERATE USURPER
        DummyVOWithVOArrays object = dummyVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithNoHandlerForProperty() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<FcknVO> fcknVOUsurper = new UsurperGenerator<FcknVO>(FcknVO.class);

        // TRY TO GENERATE USURPER WITH AN UNHANDLED TYPE : BigInteger
        try {
            @SuppressWarnings("unused")
            FcknVO object = fcknVOUsurper.generateUsurper();
            fail("a NoHandlerDefinedException should be thrown.");
        } catch (Exception e) {
            assertTrue("the thrown exception should be a " + NoHandlerDefinedException.class.getName(), e instanceof NoHandlerDefinedException);
        }

        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.onMissingHandlers(OnMissingHandlers.SKIP);

        // TRY TO GENERATE USURPER WITH AN UNHANDLED TYPE : BigInteger
        // BUT WITH UNDEFINEDHANDLERS IGNORED
        fcknVOUsurper.replaceSetup(usurperGeneratorSetup);
        try {
            @SuppressWarnings("unused")
            FcknVO object = fcknVOUsurper.generateUsurper();

            // VALIDATE RESULTING OBJECT (should fail as the unhandled property
            // is null)
            try {
                TestCommons.auditVO(object);
                fail("unhandled property should be null");
            } catch (Exception e) {
                assertTrue("the thrown exception should be a " + NullPropertyException.class.getName(), e instanceof NullPropertyException);
            }
        } catch (NoHandlerDefinedException e) {
            fail("NoHandlerDefinedException should not be thrown.");
        }
    }

    public void testGenerateObjectWithListOfValuesSpecificPropertyHandler() {
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerSpecificPropertyHandler(new ListOfValuesSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField"), TestCommons.BIG_INTEGER_VALUES_LIST));
        usurperGeneratorSetup.registerSpecificPropertyHandler(new ListOfValuesSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField2"), TestCommons.BIG_INTEGER_VALUES_LIST));

        // CREATE USURPER GENERATOR
        UsurperGenerator<FcknVO> fcknVOUsurper = new UsurperGenerator<FcknVO>(FcknVO.class, usurperGeneratorSetup);

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
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerSpecificPropertyHandler(new AbstractSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField")) {
            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.BIG_INTEGER_VALUE;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.BIG_INTEGER_VALUE;
            }
        });
        usurperGeneratorSetup.registerSpecificPropertyHandler(new AbstractSpecificPropertyHandler(new SpecificPropertyDefinition(FcknVO.class, "bigIntField2")) {
            public Object handle(HandledBeanProperty handledBeanProperty) {
                return TestCommons.BIG_INTEGER_VALUE;
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return TestCommons.BIG_INTEGER_VALUE;
            }
        });

        // CREATE USURPER GENERATOR
        UsurperGenerator<FcknVO> fcknVOUsurper = new UsurperGenerator<FcknVO>(FcknVO.class, usurperGeneratorSetup);

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
        // PREPARE USURPER GENERATOR SETUP
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandler(new AbstractPropertyTypeHandler(BigInteger.class) {
            public Object handle(HandledBeanProperty handledBeanProperty) {
                return new BigInteger(24, new Random());
            }

            public Object handle(HandledConstructorArg handledConstructorArg) {
                return new BigInteger(24, new Random());
            }
        });

        // CREATE USURPER GENERATOR
        UsurperGenerator<FcknVO> fcknVOUsurper = new UsurperGenerator<FcknVO>(FcknVO.class, usurperGeneratorSetup);

        // GENERATE USURPER
        FcknVO object3 = fcknVOUsurper.generateUsurper();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object3);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateCollection() {
        // CREATE USURPER GENERATOR
        UsurperGenerator<DummyVO> dummyVOUsurper = new UsurperGenerator<DummyVO>(DummyVO.class);

        int collectionSize = 2;

        // GENERATE USURPER LIST

        List<DummyVO> list = dummyVOUsurper.generateUsurperList(collectionSize);

        // VALIDATE RESULTING OBJECT
        assertEquals("the generated list's size should be " + collectionSize, list.size(), collectionSize);
        for (DummyVO dummyVO : list) {
            try {
                TestCommons.auditVO(dummyVO);
            } catch (NullPropertyException e) {
                fail(e.getMessage());
            }
        }

        // GENERATE USURPER SET
        Set<DummyVO> set = dummyVOUsurper.generateUsurperSet(collectionSize);

        // VALIDATE RESULTING OBJECT
        assertEquals("the generated list's size should be " + collectionSize, set.size(), collectionSize);
        for (DummyVO dummyVO : set) {
            try {
                TestCommons.auditVO(dummyVO);
            } catch (NullPropertyException e) {
                fail(e.getMessage());
            }
        }
    }
}