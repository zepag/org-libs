/**
 * 
 */
package org.org.springframework.security.addons;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.SecurityConfig;
import org.springframework.metadata.Attributes;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * This class allows to create SecurityConfig Attributes from a mapping between custom annotations and the String representation of these attributes.<br>
 * <p>
 * For example:
 * 
 * <pre>
 * &lt;bean id=&quot;attributes&quot; class=&quot;org.org.springframework.security.addons.CustomSecurityAnnotationAttributes&quot;&gt;
 *       &lt;constructor-arg&gt;
 *           &lt;map&gt;
 *               &lt;entry key=&quot;org.org.springframework.security.addons.AdministrationOperation&quot; value=&quot;ROLE_SUPERVISOR,ROLE_USER&quot;/&gt;
 *           &lt;/map&gt;
 *      &lt;/constructor-arg&gt;
 *   &lt;/bean&gt;
 * </pre>
 * 
 * </p>
 * <p>
 * Therefore applying the :
 * 
 * <pre>
 *   &#64;AdministrationOperation
 * </pre>
 * 
 * annotation to a Method/Class will be fully equivalent to applying the
 * 
 * <pre>
 *   &#64;Secured ({&quot;ROLE_SUPERVISOR&quot;,&quot;ROLE_USER&quot;})
 * </pre>
 * 
 * annotation used by Acegi's SecurityAnnotationAttributes, with the added value of not polluting your domain model with a technical annotation, and allowing the domain model to self-document its Security constraints.
 * 
 * Please note that in the current implementation of Acegi, annotations are only dealt with properly if you put them on the Interface.
 * This is due to be modified, for a greater consistency with the whole Spring framework approach (see JIRA)
 * </p><br>
 * @author Pierre-Antoine Grégoire
 * @see SecurityConfig
 */
@SuppressWarnings("unchecked")
public class CustomSecurityAnnotationAttributes implements Attributes {

    /** The annotations and attributes mapping. */
    private final Map<Class, String[]> annotationsAndAttributesMapping;

    /**
     * Instantiates a new custom security annotation attributes.<br>
     * This constructor needs a mandatory annotation to Attributes mapping.
     * 
     * @param mapping
     *            a Map allowing the association of an annotation with a series of Attribute definitions (such as ROLE_*, RUNAS_*...etc).
     */
    public CustomSecurityAnnotationAttributes(Map<Class, String[]> mapping) {
        super();
        for (Class clazz : mapping.keySet()) {
            Assert.isAssignable(Annotation.class, clazz, "Mapped types should all be sub types of the java.lang.Annotation type.");
        }
        this.annotationsAndAttributesMapping = Collections.unmodifiableMap(mapping);
    }

    public Collection getAttributes(Method method) {
        Set<SecurityConfig> attributes = new HashSet<SecurityConfig>();

        Annotation[] annotations = null;

        // Use AnnotationUtils if in classpath (ie Spring 1.2.9+ deployment)
        try {
            Class clazz = ClassUtils.forName("org.springframework.core.annotation.AnnotationUtils");
            Method m = clazz.getMethod("getAnnotations", new Class[] { Method.class });
            annotations = (Annotation[]) m.invoke(null, new Object[] { method });
        } catch (Exception ex) {
            // Fallback to manual retrieval if no AnnotationUtils available
            annotations = method.getAnnotations();
        }

        for (Annotation annotation : annotations) {
            String[] roles = annotationsAndAttributesMapping.get(annotation.annotationType());
            if (roles != null) {
                for (String role : roles) {
                    attributes.add(new SecurityConfig(role));
                }
            }
        }
        return attributes;
    }

    public Collection getAttributes(Class target) {
        Set<SecurityConfig> attributes = new HashSet<SecurityConfig>();

        for (Annotation annotation : target.getAnnotations()) {
            String[] roles = annotationsAndAttributesMapping.get(annotation.annotationType());
            if (roles != null) {
                for (String role : roles) {
                    attributes.add(new SecurityConfig(role));
                }
            }
        }
        return attributes;
    }

    public Collection getAttributes(Method targetMethod, Class filter) {
        throw new UnsupportedOperationException("Unsupported operation");

    }

    public Collection getAttributes(Field targetField) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    public Collection getAttributes(Class targetClass, Class filter) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    public Collection getAttributes(Field targetField, Class filter) {
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
