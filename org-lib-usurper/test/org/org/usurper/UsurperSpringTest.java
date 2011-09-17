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

import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.org.usurper.TestCommons.BadSetupException;
import org.org.usurper.TestCommons.NullPropertyException;
import org.org.usurper.dummydomain.DummyService;
import org.org.usurper.dummydomain.DummyVO;
import org.org.usurper.dummydomain.IDummyDAO;
import org.org.usurper.dummydomain.IOtherDummyDAO;
import org.org.usurper.dummydomain.ImmutableDummyVO;
import org.org.usurper.dummydomain.OtherDummyVO;
import org.org.usurper.setup.IUsurperGeneratorSetup;
import org.org.usurper.setup.constants.OnMissingHandlers;
import org.org.usurper.setup.constants.PropertyWritingMechanism;
import org.org.usurper.springframework.UsurperFactoryBean;
import org.org.usurper.springframework.UsurperGeneratorSetupFactoryBean;
import org.org.usurper.springframework.UsurperListFactoryBean;
import org.org.usurper.springframework.UsurperMapFactoryBean;
import org.org.usurper.springframework.UsurperSetFactoryBean;
import org.org.usurper.springframework.UsurperSpringConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author pagregoire
 */
public class UsurperSpringTest extends TestCase {

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGenerateObject() throws Exception {
        // CREATE USURPER GENERATOR
        UsurperFactoryBean usurperFactoryBean = new UsurperFactoryBean();
        usurperFactoryBean.setUsurpedClassName(DummyVO.class.getName());
        usurperFactoryBean.afterPropertiesSet();
        // GENERATE USURPER
        DummyVO object = (DummyVO) usurperFactoryBean.getObject();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateList() throws Exception {
        final int listSize = 15;
        // CREATE USURPER GENERATOR
        UsurperListFactoryBean usurperListFactoryBean = new UsurperListFactoryBean();
        usurperListFactoryBean.setUsurpedClassName(DummyVO.class.getName());
        usurperListFactoryBean.setCount(listSize);
        usurperListFactoryBean.afterPropertiesSet();
        // GENERATE USURPER
        List list = (List) usurperListFactoryBean.getObject();
        assertTrue("Generated List's size should be " + listSize, list.size() == listSize);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : list) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateSet() throws Exception {
        final int setSize = 15;
        // CREATE USURPER GENERATOR
        UsurperSetFactoryBean usurperSetFactoryBean = new UsurperSetFactoryBean();
        usurperSetFactoryBean.setUsurpedClassName(DummyVO.class.getName());
        usurperSetFactoryBean.setCount(setSize);
        usurperSetFactoryBean.afterPropertiesSet();
        // GENERATE USURPER
        Set set = (Set) usurperSetFactoryBean.getObject();
        assertTrue("Generated Set's size should be " + setSize, set.size() == setSize);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : set) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateMap() throws Exception {
        final int mapSize = 15;
        // CREATE USURPER GENERATOR
        UsurperMapFactoryBean usurperMapFactoryBean = new UsurperMapFactoryBean();
        usurperMapFactoryBean.setUsurpedKeyClassName(DummyVO.class.getName());
        usurperMapFactoryBean.setUsurpedValueClassName(OtherDummyVO.class.getName());
        usurperMapFactoryBean.setCount(mapSize);
        usurperMapFactoryBean.afterPropertiesSet();
        // GENERATE USURPER
        Map map = (Map) usurperMapFactoryBean.getObject();
        assertTrue("Generated Map's size should be " + mapSize, map.size() == mapSize);
        // VALIDATE RESULTING OBJECT
        try {
            Set keys = map.keySet();
            for (Object key : keys) {
                TestCommons.auditVO(key);
                TestCommons.auditVO(map.get(key));
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateObjectWithNamespaceHandler() throws Exception {
        // CREATE USURPER GENERATOR through Spring namespace
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace-onebean.xml");
        // RETRIEVE USURPER
        DummyVO object = (DummyVO) applicationContext.getBean("DummyVO");

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        object = ((DummyService) applicationContext.getBean("DummyService")).getDummyVO();

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(object);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateListWithNamespaceHandler() throws Exception {
        final int listSize = 15;
        // CREATE USURPER GENERATOR through Spring namespace
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace-listofbeans.xml");
        // RETRIEVE USURPER
        List list = (List) applicationContext.getBean("DummyVOs");

        assertTrue("Generated List's size should be " + listSize, list.size() == listSize);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : list) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateSetWithNamespaceHandler() throws Exception {
        final int setSize = 15;
        // CREATE USURPER GENERATOR through Spring namespace
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace-setofbeans.xml");
        // RETRIEVE USURPER
        Set set = (Set) applicationContext.getBean("DummyVOs");
        assertTrue("Generated Set's size should be " + setSize, set.size() == setSize);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : set) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateMapWithNamespaceHandler() throws Exception {
        final int mapSize = 15;
        // CREATE USURPER GENERATOR through Spring namespace
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace-mapofbeans.xml");
        // RETRIEVE USURPER
        Map map = (Map) applicationContext.getBean("DummyVOs");
        assertTrue("Generated Map's size should be " + mapSize, map.size() == mapSize);
        // VALIDATE RESULTING OBJECT
        try {
            Set keys = map.keySet();
            for (Object key : keys) {
                TestCommons.auditVO(key);
                TestCommons.auditVO(map.get(key));
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testMethodInterceptor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        IDummyDAO dummyDAO = (IDummyDAO) applicationContext.getBean("DummyDAO");

        // RETRIEVE USURPER
        DummyVO dummyVO = dummyDAO.getVO("whatever, this is not used");

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(dummyVO);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        List list = dummyDAO.getVOList("whatever, this is not used");

        assertTrue("Generated List's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, list.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : list) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        Set set = dummyDAO.getVOSet("whatever, this is not used");
        assertTrue("Generated Set's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, set.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : set) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        Map map = dummyDAO.getVOMap("whatever, this is not used");
        assertTrue("Generated Map's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, map.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            Set keys = map.keySet();
            for (Object key : keys) {
                TestCommons.auditVO(key);
                TestCommons.auditVO(map.get(key));
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testMethodInterceptorWithNamespaceHandler() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace-aop.xml");
        IDummyDAO dummyDAO = (IDummyDAO) applicationContext.getBean("DummyDAO");

        // RETRIEVE USURPER
        DummyVO dummyVO = dummyDAO.getVO("whatever, this is not used");

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(dummyVO);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        List list = dummyDAO.getVOList("whatever, this is not used");

        assertTrue("Generated List's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, list.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : list) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        Set set = dummyDAO.getVOSet("whatever, this is not used");
        assertTrue("Generated Set's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, set.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : set) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        Map map = dummyDAO.getVOMap("whatever, this is not used");
        assertTrue("Generated Map's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, map.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            Set keys = map.keySet();
            for (Object key : keys) {
                TestCommons.auditVO(key);
                TestCommons.auditVO(map.get(key));
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testMethodInterceptorWithNamespaceHandlerAndImmutables() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace-aop-immutable.xml");
        IOtherDummyDAO dummyDAO = (IOtherDummyDAO) applicationContext.getBean("OtherDummyDAO");

        // RETRIEVE USURPER
        ImmutableDummyVO dummyVO = dummyDAO.getVO("whatever, this is not used");

        // VALIDATE RESULTING OBJECT
        try {
            TestCommons.auditVO(dummyVO);
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        List list = dummyDAO.getVOList("whatever, this is not used");

        assertTrue("Generated List's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, list.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : list) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        Set set = dummyDAO.getVOSet("whatever, this is not used");
        assertTrue("Generated Set's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, set.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            for (Object object : set) {
                TestCommons.auditVO(object);
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }

        // RETRIEVE USURPER
        Map map = dummyDAO.getVOMap("whatever, this is not used");
        assertTrue("Generated Map's size should be " + UsurperSpringConstants.DEFAULT_ENTRIES_COUNT, map.size() == UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        // VALIDATE RESULTING OBJECT
        try {
            Set keys = map.keySet();
            for (Object key : keys) {
                TestCommons.auditVO(key);
                TestCommons.auditVO(map.get(key));
            }
        } catch (NullPropertyException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateSetup() throws Exception {
        UsurperGeneratorSetupFactoryBean usurperGeneratorSetupFactoryBean = new UsurperGeneratorSetupFactoryBean();
        usurperGeneratorSetupFactoryBean.setArrayHandler(TestCommons.ARRAY_HANDLER);
        usurperGeneratorSetupFactoryBean.setEnumHandler(TestCommons.ENUM_HANDLER);
        usurperGeneratorSetupFactoryBean.setCountCallback(TestCommons.DUMMY_COLLECTION_COUNT_CALLBACK);
        usurperGeneratorSetupFactoryBean.onMissingHandlers(OnMissingHandlers.SKIP);
        usurperGeneratorSetupFactoryBean.usePropertyWritingMechanism(PropertyWritingMechanism.USE_SETTERS);
        usurperGeneratorSetupFactoryBean.setPropertyTypeHandlers(TestCommons.PROPERTY_TYPE_HANDLERS_SET);
        usurperGeneratorSetupFactoryBean.setSpecificPropertyHandlers(TestCommons.SPECIFIC_PROPERTY_HANDLERS_SET);
        usurperGeneratorSetupFactoryBean.afterPropertiesSet();

        IUsurperGeneratorSetup usurperGeneratorSetup = (IUsurperGeneratorSetup) usurperGeneratorSetupFactoryBean.getObject();
        try {
            TestCommons.auditSetup(usurperGeneratorSetup);
        } catch (BadSetupException e) {
            fail(e.getMessage());
        }

        UsurperGeneratorSetupFactoryBean usurperGeneratorSetupFactoryBean2 = new UsurperGeneratorSetupFactoryBean();
        usurperGeneratorSetupFactoryBean2.setParentSetup(usurperGeneratorSetup);
        usurperGeneratorSetupFactoryBean2.afterPropertiesSet();

        IUsurperGeneratorSetup usurperGeneratorSetup2 = (IUsurperGeneratorSetup) usurperGeneratorSetupFactoryBean2.getObject();
        try {
            TestCommons.auditSetup(usurperGeneratorSetup2);
        } catch (BadSetupException e) {
            fail(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testGenerateSetupWithXMLConfig() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-setup.xml");
        IUsurperGeneratorSetup usurperGeneratorSetup = (IUsurperGeneratorSetup) applicationContext.getBean("usurperGeneratorSetupClassic");
        try {
            TestCommons.auditSetup(usurperGeneratorSetup);
        } catch (BadSetupException e) {
            fail(e.getMessage());
        }

        IUsurperGeneratorSetup usurperGeneratorSetup2 = (IUsurperGeneratorSetup) applicationContext.getBean("usurperGeneratorSetupClassic2");
        try {
            TestCommons.auditSetup(usurperGeneratorSetup2);
        } catch (BadSetupException e) {
            fail(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public void testGenerateSetupWithNamespaceHandler() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-namespace-setup.xml");
        IUsurperGeneratorSetup usurperGeneratorSetup = (IUsurperGeneratorSetup) applicationContext.getBean("usurperGeneratorSetupNamespace");
        try {
            TestCommons.auditSetup(usurperGeneratorSetup);
        } catch (BadSetupException e) {
            fail(e.getMessage());
        }
        IUsurperGeneratorSetup usurperGeneratorSetup2 = (IUsurperGeneratorSetup) applicationContext.getBean("usurperGeneratorSetupNamespace2");
        try {
            TestCommons.auditSetup(usurperGeneratorSetup2);
        } catch (BadSetupException e) {
            fail(e.getMessage());
        }

    }
}