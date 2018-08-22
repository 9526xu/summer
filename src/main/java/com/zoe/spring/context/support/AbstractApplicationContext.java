package com.zoe.spring.context.support;

import com.zoe.spring.beans.factory.support.DefaultBeanFactory;
import com.zoe.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.zoe.spring.beans.util.ClassUtils;
import com.zoe.spring.context.ApplicationContext;
import com.zoe.spring.core.io.Resources;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/12 15:20
 * @since JDK 1.6
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

	protected DefaultBeanFactory beanFactory;

	protected XmlBeanDefinitionReader reader;

	protected Resources resources;

	protected String configLocation;

	protected ClassLoader beanClassLoader;


	public AbstractApplicationContext(String configLocation) {
		this.configLocation = configLocation;
		beanFactory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(beanFactory);
		resources = getResourcesByPath();
		reader.loadBeanDefinitions(resources);
	}


	public AbstractApplicationContext(String configLocation, ClassLoader beanClassLoader) {
		this(configLocation);
		this.beanClassLoader = beanClassLoader;
	}

	protected abstract Resources getResourcesByPath();

	@Override
	public Object getBean(String beanId) {
		return this.beanFactory.getBean(beanId);
	}

	@Override
	public ClassLoader getBeanClassLoader() {
		return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader;
	}

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}
}
