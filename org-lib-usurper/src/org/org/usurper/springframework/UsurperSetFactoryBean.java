package org.org.usurper.springframework;

import java.util.Set;

import org.org.usurper.UsurperGenerator;
import org.org.usurper.setup.UsurperGeneratorSetup;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

/**
 * The Class UsurperSetFactoryBean is a Spring compliant FactoryBean and InitializingBean.
 * As such, it can be used as any other Spring Factory Bean.
 * It generates Sets of Usurpers for a given class.
 */
public class UsurperSetFactoryBean implements FactoryBean, InitializingBean {

    private UsurperGenerator<?> usurperGenerator;
    private String usurpedClassName;
    private Integer count = UsurperSpringConstants.DEFAULT_ENTRIES_COUNT;
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
        return usurperGenerator.generateUsurperSet(count);
    }

    @SuppressWarnings("unchecked")
    public Class getObjectType() {
        return Set.class;
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

    public void setCount(Integer count) {
        this.count = count;
    }

}
