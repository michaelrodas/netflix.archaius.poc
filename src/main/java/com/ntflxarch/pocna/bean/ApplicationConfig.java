package com.ntflxarch.pocna.bean;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.jmx.BaseConfigMBean;
import com.netflix.config.jmx.ConfigJMXManager;
import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationConfig{

    static {
        System.setProperty("archaius.configurationSource.defaultFileName", "application.properties");
        System.setProperty("archaius.fixedDelayPollingScheduler.delayMills", "30000");
        System.setProperty("archaius.dynamicPropertyFactory.registerConfigWithJMX", "true");
        System.setProperty("archaius.fixedDelayPollingScheduler.initialDelayMills", "5000");
    }

    public ApplicationConfig() {
    }

    public String getStringProperty(String key, String defaultValue) {

        Optional<String> defval = Optional.ofNullable(defaultValue);
        //DynamicStringProperty property = new DynamicStringProperty(key, defval.orElse("Property not found."));
        //DynamicProperty property = DynamicProperty.getInstance(key);

        DynamicStringProperty property = DynamicPropertyFactory.getInstance().getStringProperty(key,
                defval.orElse("Property not found."));
        return property.get();
    }

    public void setStringProperty(String key, String value){
        BaseConfigMBean baseConfigMBean = new BaseConfigMBean(ConfigurationManager.getConfigInstance());
        baseConfigMBean.updateProperty(key, value);
    }
}

