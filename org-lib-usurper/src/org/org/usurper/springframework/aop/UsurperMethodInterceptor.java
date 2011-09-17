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
package org.org.usurper.springframework.aop;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.org.usurper.UsurperGenerator;
import org.org.usurper.setup.UsurperGeneratorSetup;
import org.org.usurper.springframework.UsurperMapFactoryBean;
import org.org.usurper.springframework.UsurperSpringConstants;

/**
 * The Class UsurperMethodInterceptor is an AOPAlliance MethodInterceptor that provides a virtual implementation for a given interface.<br>
 * These virtual methods return Usurped objects which are instances of the defined returned types.
 */
public class UsurperMethodInterceptor implements MethodInterceptor {

    private UsurperGeneratorSetup usurperGeneratorSetup;

    public void setUsurperGeneratorSetup(UsurperGeneratorSetup usurperGeneratorSetup) {
        this.usurperGeneratorSetup = usurperGeneratorSetup;
    }

    @SuppressWarnings("unchecked")
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object usurpedResult = null;
        Class<?> returnType = methodInvocation.getMethod().getReturnType();
        if (returnType.isAssignableFrom(Map.class)) {
            ParameterizedType genericType = (ParameterizedType) methodInvocation.getMethod().getGenericReturnType();
            UsurperMapFactoryBean mapFactoryBean = new UsurperMapFactoryBean();
            mapFactoryBean.setUsurpedKeyClassName(((Class) genericType.getActualTypeArguments()[0]).getName());
            mapFactoryBean.setUsurpedValueClassName(((Class) genericType.getActualTypeArguments()[1]).getName());
            mapFactoryBean.setUsurperGeneratorSetup(this.usurperGeneratorSetup);
            mapFactoryBean.afterPropertiesSet();
            usurpedResult = mapFactoryBean.getObject();
        } else if (returnType.isAssignableFrom(List.class)) {
            ParameterizedType genericType = (ParameterizedType) methodInvocation.getMethod().getGenericReturnType();
            UsurperGenerator usurperGenerator = createUsurperGenerator((Class) genericType.getActualTypeArguments()[0]);
            usurpedResult = usurperGenerator.generateUsurperList(UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        } else if (returnType.isAssignableFrom(Set.class)) {
            ParameterizedType genericType = (ParameterizedType) methodInvocation.getMethod().getGenericReturnType();
            UsurperGenerator usurperGenerator = createUsurperGenerator((Class) genericType.getActualTypeArguments()[0]);
            usurpedResult = usurperGenerator.generateUsurperSet(UsurperSpringConstants.DEFAULT_ENTRIES_COUNT);
        } else {
            UsurperGenerator usurperGenerator = createUsurperGenerator(returnType);
            usurpedResult = usurperGenerator.generateUsurper();
        }
        return usurpedResult;
    }

    @SuppressWarnings("unchecked")
    private UsurperGenerator createUsurperGenerator(Class<?> usurpedClass) {
        UsurperGenerator usurperGenerator = null;
        if (usurperGeneratorSetup == null) {
            usurperGenerator = new UsurperGenerator(usurpedClass);
        } else {
            usurperGenerator = new UsurperGenerator(usurpedClass, usurperGeneratorSetup);
        }
        return usurperGenerator;
    }

}
