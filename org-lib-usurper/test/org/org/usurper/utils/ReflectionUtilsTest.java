package org.org.usurper.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.org.usurper.utils.ReflectionUtils;

import junit.framework.TestCase;

public class ReflectionUtilsTest extends TestCase {
    private class FixtureVO {
        private final Map<String, Integer> attr;

        private Map<String, Integer> attr2;

        public Map<String, Integer> getAttr2() {
            return attr2;
        }

        public void setAttr2(Map<String, Integer> attr2) {
            this.attr2 = attr2;
        }

        public FixtureVO(final Map<String, Integer> attr) {
            this.attr = attr;
        }

        public Map<String, Integer> getAttr() {
            return attr;
        }
    }

    private class FixtureVO2 {
        private String attr;

        private Integer attr2;

        private String[] attr3;

        public FixtureVO2() {
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        public Integer getAttr2() {
            return attr2;
        }

        public void setAttr2(Integer attr2) {
            this.attr2 = attr2;
        }

        public String[] getAttr3() {
            return attr3;
        }

        public void setAttr3(String[] attr3) {
            this.attr3 = attr3;
        }

    }

    public void testGetGenericTypeOfConstructor() {
        Type[] types = ReflectionUtils.getGenericTypes(FixtureVO.class.getConstructors()[0], 1);
        assertEquals(types[0], String.class);
        assertEquals(types[1], Integer.class);
    }

    public void testGetGenericTypesField() {
        FixtureVO fixtureVO = new FixtureVO(new HashMap<String, Integer>());
        Field field = null;
        try {
            field = fixtureVO.getClass().getDeclaredField("attr2");
        } catch (SecurityException e) {
            fail("INCOMPLETE FIXTURE, Field needed.");
        } catch (NoSuchFieldException e) {
            fail("INCOMPLETE FIXTURE, Field needed.");
        }
        Type[] types = ReflectionUtils.getGenericTypes(field);
        assertEquals(types[0], String.class);
        assertEquals(types[1], Integer.class);
    }

    public void testGetField() {
        Field field = null;
        try {
            field = ReflectionUtils.getField(new FixtureVO2(), "attr");
        } catch (SecurityException e) {
            fail("SecurityException should not occur.");
        } catch (NoSuchFieldException e) {
            fail("NoSuchFieldException should not occur.");
        }
        assertEquals(field.getName(), "attr");
    }

    public void testSetPropertyByName() {
        FixtureVO2 fixtureVO2 = new FixtureVO2();
        try {
            ReflectionUtils.setProperty(fixtureVO2, "attr", "blabla");
        } catch (SecurityException e) {
            fail("SecurityException should not occur.");
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException should not occur.");
        } catch (NoSuchFieldException e) {
            fail("NoSuchFieldException should not occur.");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException should not occur.");
        }
        assertEquals(fixtureVO2.getAttr(), "blabla");
    }

    public void testSetArrayPropertyByName() {
        FixtureVO2 fixtureVO2 = new FixtureVO2();
        try {
            ReflectionUtils.setProperty(fixtureVO2, "attr3", new String[] { "blabla", "blabla" });
        } catch (SecurityException e) {
            fail("SecurityException should not occur.");
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException should not occur.");
        } catch (NoSuchFieldException e) {
            fail("NoSuchFieldException should not occur.");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException should not occur.");
        }
        assertTrue(Arrays.deepEquals(fixtureVO2.getAttr3(), new String[] { "blabla", "blabla" }));
    }

    public void testSetPropertyBySetter() {
        FixtureVO2 fixtureVO2 = new FixtureVO2();
        try {
            ReflectionUtils.setProperty(fixtureVO2, FixtureVO2.class.getMethod("setAttr", new Class[] { String.class }), "blabla");
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException should not occur.");
        } catch (SecurityException e) {
            fail("SecurityException should not occur.");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException should not occur.");
        } catch (InvocationTargetException e) {
            fail("InvocationTargetException should not occur.");
        } catch (NoSuchMethodException e) {
            fail("NoSuchMethodException should not occur.");
        }
        assertEquals(fixtureVO2.getAttr(), "blabla");
    }

    public void testSetArrayPropertyBySetter() {
        FixtureVO2 fixtureVO2 = new FixtureVO2();
        try {
            ReflectionUtils.setProperty(fixtureVO2, FixtureVO2.class.getMethod("setAttr3", new Class[] { String[].class }), new String[] { "blabla", "blabla" });
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException should not occur.");
        } catch (SecurityException e) {
            fail("SecurityException should not occur.");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException should not occur.");
        } catch (InvocationTargetException e) {
            fail("InvocationTargetException should not occur.");
        } catch (NoSuchMethodException e) {
            fail("NoSuchMethodException should not occur.");
        }
        assertTrue(Arrays.deepEquals(fixtureVO2.getAttr3(), new String[] { "blabla", "blabla" }));
    }
}
