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
package org.org.usurper.springframework;

import org.org.usurper.UsurperGenerator;
import org.org.usurper.setup.UsurperGeneratorSetup;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

/**
 * The Class UsurperFactoryBean is a Spring compliant FactoryBean and InitializingBean.
 * As such, it can be used as any other Spring Factory Bean.
 * It generates an Usurped object.
 */
public class UsurperFactoryBean implements FactoryBean, InitializingBean {

    private UsurperGenerator<?> usurperGenerator;
    private String usurpedClassName;
    @SuppressWarnings("unchecked")
    private Class usurpedClass;
    private UsurperGeneratorSetup usurperGeneratorSetup;
    
    public void setUsurperGeneratorSetup(UsurperGeneratorSetup usurperGeneratorSetup) {
        this.usurperGeneratorSetup = usurperGeneratorSetup;
    }
    
    @Required
    public void setUsurpedClassName(String usurpedClassName) {
        this.usurpedClassName = usurpedClassName;
    }

    public Object getObject() throws Exception {
        return usurperGenerator.generateUsurper();
    }

    @SuppressWarnings("unchecked")
    public Class getObjectType() {
        return usurpedClass;
    }

    public boolean isSingleton() {
        return false;
    }

    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        usurpedClass = Class.forName(usurpedClassName);
        if (usurperGeneratorSetup == null) {
            usurperGenerator = new UsurperGenerator(usurpedClass);
        } else {
            usurperGenerator = new UsurperGenerator(usurpedClass, usurperGeneratorSetup);
        }
    }

}
