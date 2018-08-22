package com.zoe.spring.beans.factory.config;

import com.zoe.spring.beans.PropertyValue;

import java.util.List;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/11 16:39
 * @since JDK 1.6
 */
public interface BeanDefinition {
	String SCOPE_SINGLETON = "singleton";


	String SCOPE_PROTOTYPE = "prototype";

	boolean isSingleton();

	boolean isPrototype();

	String getBeanClassName();

	String getScope();

	void setScope(String scope);

	List<PropertyValue> getPropertyValues();

	void addPropertyValue(PropertyValue propertyValue);
}
