package org.org.usurper.springframework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.org.usurper.UsurperGenerator;
import org.org.usurper.setup.UsurperGeneratorSetup;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

/**
 * The Class UsurperMapFactoryBean is a Spring compliant FactoryBean and InitializingBean.
 * As such, it can be used as any other Spring Factory Bean.
 * It generates Maps of Usurpers for given key class and value class.
 */
public class UsurperMapFactoryBean implements FactoryBean, InitializingBean {

    private UsurperGenerator<?> keyUsurperGenerator;
    private UsurperGenerator<?> valueUsurperGenerator;
    private String usurpedValueClassName;
    private String usurpedKeyClassName;
    private Integer count = UsurperSpringConstants.DEFAULT_ENTRIES_COUNT;
    @SuppressWarnings("unchecked")
    private Class usurpedKeyClass;
    @SuppressWarnings("unchecked")
    private Class usurpedValueClass;
    private UsurperGeneratorSetup usurperGeneratorSetup;

    public void setUsurperGeneratorSetup(UsurperGeneratorSetup usurperGeneratorSetup) {
        this.usurperGeneratorSetup = usurperGeneratorSetup;
    }
    
    @Required
    public void setUsurpedKeyClassName(String usurpedKeyClassName) {
        this.usurpedKeyClassName = usurpedKeyClassName;
    }

    @Required
    public void setUsurpedValueClassName(String usurpedValueClassName) {
        this.usurpedValueClassName = usurpedValueClassName;
    }

    @SuppressWarnings("unchecked")
    public Object getObject() throws Exception {
        List<?> keyList = keyUsurperGenerator.generateUsurperList(count);
        List<?> valueList = valueUsurperGenerator.generateUsurperList(count);
        Map result = new HashMap();
        for (int i = 0; i < keyList.size(); i++) {
            result.put(keyList.get(i), valueList.get(i));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public Class getObjectType() {
        return Map.class;
    }

    public boolean isSingleton() {
        return false;
    }

    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        usurpedKeyClass = Class.forName(usurpedKeyClassName);
        usurpedValueClass = Class.forName(usurpedValueClassName);
        if (usurperGeneratorSetup == null) {
            keyUsurperGenerator = new UsurperGenerator(usurpedKeyClass);
            valueUsurperGenerator = new UsurperGenerator(usurpedValueClass);
        } else {
            keyUsurperGenerator = new UsurperGenerator(usurpedKeyClass, usurperGeneratorSetup);
            valueUsurperGenerator = new UsurperGenerator(usurpedValueClass, usurperGeneratorSetup);
        }
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
