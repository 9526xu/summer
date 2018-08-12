package com.zoe.spring.beans.factory.config.support;

import com.zoe.spring.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/11 17:06
 * @since JDK 1.6
 */
public class GenericBeanDefinition implements BeanDefinition {

	private String beanId;

	private String beanClassName;

	private String scope;

	public GenericBeanDefinition(String beanId, String beanClassName) {
		this.beanId = beanId;
		this.beanClassName = beanClassName;
		// 默认为
		this.scope = this.SCOPE_SINGLETON;
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
}
