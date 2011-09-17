package org.org.springframework.security;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

import org.org.springframework.security.addons.CustomSecurityAnnotationAttributes;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.SecurityConfig;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.intercept.method.MethodDefinitionAttributes;
import org.springframework.security.intercept.method.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.ProviderManager;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.userdetails.memory.InMemoryDaoImpl;
import org.springframework.security.vote.AccessDecisionVoter;
import org.springframework.security.vote.RoleVoter;
import org.springframework.security.vote.UnanimousBased;

public class CustomSecurityAnnotationAttributesTest extends TestCase {

    @SuppressWarnings("unchecked")
    public void testAttributesRetrievalOnClass() throws Exception {
        Map<Class, String[]> mapping = new HashMap<Class, String[]>();
        String[] adminRoleNeededCorrespondingRoles = new String[] { "ROLE_SUPERVISOR", "ROLE_USER" };
        mapping.put(AdminRoleNeeded.class, adminRoleNeededCorrespondingRoles);
        // litterally this means that when the domain model specified through the AdminRoleNeeded annotation (meta data)
        // that an admin role is needed, "ROLE_SUPERVISOR" AND "ROLE_USER" roles are needed on the deployment system.
        // Of Course , the Set of Strings can contain any Security attribute relevant to Spring security's work, like RUNAS for example.

        // TEST WITH ANNOTATIONS ON THE INTERFACE
        MyNotAnnotatedServiceImpl myService = new MyNotAnnotatedServiceImpl();
        CustomSecurityAnnotationAttributes annotationAttributes = new CustomSecurityAnnotationAttributes(mapping);
        try {
            Collection attributes = annotationAttributes.getAttributes(myService.getClass());
            for (Class anInterface : myService.getClass().getInterfaces()) {
                attributes.addAll(annotationAttributes.getAttributes(anInterface));
            }
            assertNotNull("result should not be null", attributes);
            for (Object attribute : attributes) {
                assertTrue("attribute should be a " + SecurityConfig.class.getName(), attribute instanceof SecurityConfig);
            }
            assertEquals("All the mapped roles should be returned, no more, no less.", adminRoleNeededCorrespondingRoles.length, attributes.size());
        } catch (Exception e) {
            fail("An unexpected exception occured:" + e.getMessage());
        }
        // TEST WITH ANNOTATIONS ON THE CLASS
        MyAnnotatedServiceImpl myService2 = new MyAnnotatedServiceImpl();
        CustomSecurityAnnotationAttributes annotationAttributes2 = new CustomSecurityAnnotationAttributes(mapping);
        try {
            Collection attributes = annotationAttributes2.getAttributes(myService2.getClass());
            for (Class anInterface : myService.getClass().getInterfaces()) {
                attributes.addAll(annotationAttributes.getAttributes(anInterface));
            }
            assertNotNull("result should not be null", attributes);
            for (Object attribute : attributes) {
                assertTrue("attribute should be a " + SecurityConfig.class.getName(), attribute instanceof SecurityConfig);
            }
            assertEquals("All the mapped roles should be returned, no more, no less.", adminRoleNeededCorrespondingRoles.length, attributes.size());
        } catch (Exception e) {
            fail("An unexpected exception occured:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void testAttributesRetrievalOnMethodFromClass() throws Exception {
        Map<Class, String[]> mapping = new HashMap<Class, String[]>();
        String[] adminRoleNeededCorrespondingRoles = new String[] { "ROLE_SUPERVISOR", "ROLE_USER" };
        mapping.put(AdminRoleNeeded.class, adminRoleNeededCorrespondingRoles);
        // litterally this means that when the domain model specified through the AdminRoleNeeded annotation (meta data)
        // that an admin role is needed, "ROLE_SUPERVISOR" AND "ROLE_USER" roles are needed on the deployment system.
        // Of Course , the Set of Strings can contain any Security attribute relevant to Spring security's work, like RUNAS for example.
        // TEST WITH ANNOTATIONS ON THE INTERFACE
        MyNotAnnotatedServiceImpl myService = new MyNotAnnotatedServiceImpl();
        CustomSecurityAnnotationAttributes annotationAttributes = new CustomSecurityAnnotationAttributes(mapping);
        try {
            Method method = myService.getClass().getMethod("getResult", new Class[0]);
            Collection attributes = annotationAttributes.getAttributes(method);
            for (Method interfaceMethod : getInterfaceMethods(method)) {
                attributes.addAll(annotationAttributes.getAttributes(interfaceMethod));
            }
            assertNotNull("result should not be null", attributes);
            for (Object attribute : attributes) {
                assertTrue("attribute should be a " + SecurityConfig.class.getName(), attribute instanceof SecurityConfig);
            }
            assertEquals("All the mapped roles should be returned, no more, no less.", adminRoleNeededCorrespondingRoles.length, attributes.size());
        } catch (Exception e) {
            fail("An unexpected exception occured:" + e.getMessage());
        }
        // TEST WITH ANNOTATIONS ON THE CLASS
        MyAnnotatedServiceImpl myService2 = new MyAnnotatedServiceImpl();
        CustomSecurityAnnotationAttributes annotationAttributes2 = new CustomSecurityAnnotationAttributes(mapping);
        try {
            Method method = myService2.getClass().getMethod("getResult", new Class[0]);
            Collection attributes = annotationAttributes2.getAttributes(method);
            for (Method interfaceMethod : getInterfaceMethods(method)) {
                attributes.addAll(annotationAttributes.getAttributes(interfaceMethod));
            }
            assertNotNull("result should not be null", attributes);
            for (Object attribute : attributes) {
                assertTrue("attribute should be a " + SecurityConfig.class.getName(), attribute instanceof SecurityConfig);
            }
            assertEquals("All the mapped roles should be returned, no more, no less.", adminRoleNeededCorrespondingRoles.length, attributes.size());
        } catch (Exception e) {
            fail("An unexpected exception occured:" + e.getMessage());
        }
    }

    private List<Method> getInterfaceMethods(Method method) {
        List<Method> methods = new LinkedList<Method>();
        Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            Class<?> clazz = interfaces[i];

            try {
                methods.add(clazz.getDeclaredMethod(method.getName(), (Class[]) method.getParameterTypes()));
            } catch (Exception e) {
                // this won't happen since we are getting a method from an interface that
                // the declaring class implements
            }
        }
        return methods;
    }

    @SuppressWarnings("unchecked")
	public void testUnsupportedOperations() throws Exception {
        Map<Class, String[]> mapping = new HashMap<Class, String[]>();
        String[] adminRoleNeededCorrespondingRoles = new String[] { "ROLE_SUPERVISOR", "ROLE_USER" };
        mapping.put(AdminRoleNeeded.class, adminRoleNeededCorrespondingRoles);
        // litterally this means that when the domain model specified through the AdminRoleNeeded annotation (meta data)
        // that an admin role is needed, "ROLE_SUPERVISOR" AND "ROLE_USER" roles are needed on the deployment system.
        // Of Course , the Set of Strings can contain any Security attribute relevant to Spring security's work, like RUNAS for example.

        IMyAnnotatedServiceInterface myService = new MyNotAnnotatedServiceImpl();
        CustomSecurityAnnotationAttributes annotationAttributes = new CustomSecurityAnnotationAttributes(mapping);

        try {
            annotationAttributes.getAttributes(myService.getClass().getDeclaredFields()[0]);
            fail("This attribute does not support this method");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        try {
            annotationAttributes.getAttributes(myService.getClass(), AdminRoleNeeded.class);
            fail("This attribute does not support this method");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        try {
            annotationAttributes.getAttributes(myService.getClass().getDeclaredFields()[0], AdminRoleNeeded.class);
            fail("This attribute does not support this method");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        try {
            annotationAttributes.getAttributes(myService.getClass().getMethods()[0], AdminRoleNeeded.class);
            fail("This attribute does not support this method");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    public void testComplianceWithSpringWithXMLConfiguration() throws Exception {
        SecurityContextHolder.setContext(new SecurityContext() {

            private static final long serialVersionUID = 9182609898080024870L;

            public void setAuthentication(Authentication arg0) {
            }

            public Authentication getAuthentication() {
                return new UsernamePasswordAuthenticationToken("marissa", "koala");
            }

        });
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test-config1.xml");
        IMyAnnotatedServiceInterface myService = (IMyAnnotatedServiceInterface) applicationContext.getBean("myServiceProxy");
        IMyNotAnnotatedServiceInterface myService2 = (IMyNotAnnotatedServiceInterface) applicationContext.getBean("myServiceProxy2");
        doTestServices(myService, myService2);
    }

    @SuppressWarnings("unchecked")
	public void testComplianceWithSpring() throws Exception {
        // This test is exactly the same test
        PropertiesFactoryBean userProperties = new PropertiesFactoryBean();
        userProperties.setLocation(new ClassPathResource("users.properties"));
        userProperties.afterPropertiesSet();

        InMemoryDaoImpl inMemoryDaoImpl = new InMemoryDaoImpl();
        inMemoryDaoImpl.setUserProperties((Properties) userProperties.getObject());
        inMemoryDaoImpl.afterPropertiesSet();

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(inMemoryDaoImpl);
        daoAuthenticationProvider.afterPropertiesSet();

        List<AuthenticationProvider> providers = new LinkedList<AuthenticationProvider>();
        providers.add(daoAuthenticationProvider);

        ProviderManager authenticationManager = new ProviderManager();
        authenticationManager.setProviders(providers);
        authenticationManager.afterPropertiesSet();
        Map<Class, String[]> mapping = new HashMap<Class, String[]>();
        String[] adminRoleNeededCorrespondingRoles = new String[] { "ROLE_SUPERVISOR", "ROLE_USER" };
        mapping.put(AdminRoleNeeded.class, adminRoleNeededCorrespondingRoles);
        CustomSecurityAnnotationAttributes annotationAttributes = new CustomSecurityAnnotationAttributes(mapping);

        MethodDefinitionAttributes methodDefinitionAttributes = new MethodDefinitionAttributes();
        methodDefinitionAttributes.setAttributes(annotationAttributes);

        RoleVoter roleVoter = new RoleVoter();
        List<AccessDecisionVoter> decisionVoters = new LinkedList<AccessDecisionVoter>();
        decisionVoters.add(roleVoter);

        UnanimousBased accessDecisionManager = new UnanimousBased();
        accessDecisionManager.setAllowIfAllAbstainDecisions(false);
        accessDecisionManager.setDecisionVoters(decisionVoters);

        MethodSecurityInterceptor methodSecurityInterceptor = new MethodSecurityInterceptor();
        methodSecurityInterceptor.setValidateConfigAttributes(false);
        methodSecurityInterceptor.setAuthenticationManager(authenticationManager);
        methodSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        methodSecurityInterceptor.setObjectDefinitionSource(methodDefinitionAttributes);

        MyNotAnnotatedServiceImpl myService = new MyNotAnnotatedServiceImpl();
        MyAnnotatedServiceImpl myService2 = new MyAnnotatedServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[] { IMyAnnotatedServiceInterface.class });
        proxyFactory.setTarget(myService);
        proxyFactory.addAdvice(methodSecurityInterceptor);

        ProxyFactory proxyFactory2 = new ProxyFactory();
        proxyFactory2.setInterfaces(new Class[] { IMyNotAnnotatedServiceInterface.class });
        proxyFactory2.setTarget(myService2);
        proxyFactory2.addAdvice(methodSecurityInterceptor);

        IMyAnnotatedServiceInterface myServiceProxy = (IMyAnnotatedServiceInterface) proxyFactory.getProxy();
        IMyNotAnnotatedServiceInterface myService2Proxy = (IMyNotAnnotatedServiceInterface) proxyFactory2.getProxy();

        doTestServices(myServiceProxy, myService2Proxy);
    }

    /**
     * @param myServiceWithAnnotatedInterface
     * @param myServiceWithAnnotatedImpl
     */
    private void doTestServices(IMyAnnotatedServiceInterface myServiceWithAnnotatedInterface, IMyNotAnnotatedServiceInterface myServiceWithAnnotatedImpl) {
        // Setting a security context with an authorized user
        // (i.e. its granted authorities are matching the ones mapped to the custom annotation)
        SecurityContextHolder.setContext(new SecurityContext() {

            private static final long serialVersionUID = 9182609898080024870L;

            public void setAuthentication(Authentication arg0) {
            }

            public Authentication getAuthentication() {
                return new UsernamePasswordAuthenticationToken("marissa", "koala");
            }

        });
        try {
            myServiceWithAnnotatedInterface.getResult();
            myServiceWithAnnotatedInterface.getResult2();
//            myServiceWithAnnotatedImpl.getResult();
//            myServiceWithAnnotatedImpl.getResult2();
        } catch (Exception e) {
            fail("An unexpected error occured");
        }
        SecurityContextHolder.clearContext();

        // Setting a security context with an unauthorized user
        // (i.e. its granted authorities are NOT matching the ones mapped to the custom annotation)
        SecurityContextHolder.setContext(new SecurityContext() {

            private static final long serialVersionUID = 9182609898080024870L;

            public void setAuthentication(Authentication arg0) {
            }

            public Authentication getAuthentication() {
                return new UsernamePasswordAuthenticationToken("dianne", "emu");
            }

        });
        try {
            myServiceWithAnnotatedInterface.getResult();
            fail("should fail with access denied as method is annotated on the Interface");
        } catch (Exception e) {
            assertTrue(e instanceof AccessDeniedException);
        }
        try {
            myServiceWithAnnotatedInterface.getResult2();
            fail("should fail with access denied as interface is annotated");
        } catch (Exception e) {
            assertTrue(e instanceof AccessDeniedException);
        }
//        try {
//            myServiceWithAnnotatedImpl.getResult();
//            fail("should fail with access denied  as method is annotated on the Implementation");
//        } catch (Exception e) {
//            assertTrue(e instanceof AccessDeniedException);
//        }
//        try {
//            myServiceWithAnnotatedImpl.getResult2();
//            fail("should fail with access denied  as implementation class is annotated");
//        } catch (Exception e) {
//            assertTrue(e instanceof AccessDeniedException);
//        }
        SecurityContextHolder.clearContext();

    }
}