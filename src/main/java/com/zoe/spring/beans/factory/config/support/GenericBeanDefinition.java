package com.zoe.spring.beans.factory.config.support;

import com.google.common.collect.Lists;
import com.zoe.spring.beans.PropertyValue;
import com.zoe.spring.beans.factory.config.BeanDefinition;

import java.util.List;
import java.util.Objects;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/11 17:06
 * @since JDK 1.6
 */
public class GenericBeanDefinition implements BeanDefinition {

	private String beanId;

	private String beanClassName;

	private String scope;

	private List<PropertyValue> propertyValues;

	public GenericBeanDefinition(String beanId, String beanClassName) {
		this.beanId = beanId;
		this.beanClassName = beanClassName;
		// 默认为
		this.scope = this.SCOPE_SINGLETON;
		propertyValues = Lists.newArrayList();
	}

	@Override
	public boolean isSingleton() {
		return Objects.equals(this.scope, this.SCOPE_SINGLETON);
	}

	@Override
	public boolean isPrototype() {
		return Objects.equals(this.scope, this.SCOPE_PROTOTYPE);
	}

	@Override
	public String getBeanClassName() {
		return this.beanClassName;
	}

	@Override
	public String getScope() {
		return this.scope;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public List<PropertyValue> getPropertyValues() {
		return propertyValues;
	}

	@Override
	public void addPropertyValue(PropertyValue propertyValue) {
		this.propertyValues.add(propertyValue);
	}
}
