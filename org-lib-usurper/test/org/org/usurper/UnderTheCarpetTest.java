package org.org.usurper;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;

import org.org.usurper.handlers.exceptions.NoHandlerDefinedException;
import org.org.usurper.handlers.exceptions.PropertyTypeHandlingException;
import org.org.usurper.handlers.exceptions.SpecificPropertyHandlingException;

/**
 * Test used to improve coverage... shameful... :P
 * 
 * @author pagregoire
 */
public class UnderTheCarpetTest extends TestCase {
    public void testBlah() throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        testException(UsurperException.class);
        testException(NoHandlerDefinedException.class);
        testException(SpecificPropertyHandlingException.class);
        testException(PropertyTypeHandlingException.class);
    }

    private void testException(Class<?> name) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        name.getConstructor(new Class[] {}).newInstance(new Object[] {});
        name.getConstructor(new Class[] { String.class }).newInstance(new Object[] { "" });
        name.getConstructor(new Class[] { String.class, Throwable.class }).newInstance(new Object[] { "", new Exception() });
        name.getConstructor(new Class[] { Throwable.class }).newInstance(new Object[] { new Exception() });
    }
}