package com.zoe.spring.beans.factory.support;

import com.google.common.collect.Maps;
import com.zoe.spring.beans.factory.BeanCreationException;
import com.zoe.spring.beans.factory.ConfigurableBeanFactory;
import com.zoe.spring.beans.factory.config.BeanDefinition;
import com.zoe.spring.beans.factory.config.support.DefaultSingletonBeanRegistry;
import com.zoe.spring.beans.util.ClassUtils;

import java.util.Map;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/11 16:33
 * @since JDK 1.6
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {

	private ClassLoader beanClassLoader;

	private Map<String, BeanDefinition> beanDefinitionMap;

	private Map<String, Object> beanMap;

	public DefaultBeanFactory() {
		beanDefinitionMap = Maps.newConcurrentMap();
		beanMap = Maps.newConcurrentMap();
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanName, bd);
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		return beanDefinitionMap.get(beanName);
	}

	@Override
	public Object getBean(String beanId) {
		// 先取出 beanDefinition 查看 Scope
		BeanDefinition bd = getBeanDefinition(beanId);
		if (bd == null) {
			return null;
		}
		Object bean = null;
		if (bd.isSingleton()) {
			bean = getSingleton(beanId);
			if (bean == null) {
				bean = createBean(beanId);
				registerSingleton(beanId, bean);
				return bean;
			}
		} else {
			bean = createBean(beanId);
		}
		return bean;
	}


	private Object createBean(String beanName) {
		BeanDefinition bd = beanDefinitionMap.get(beanName);
		try {
			Class resultClass = this.getBeanClassLoader().loadClass(bd.getBeanClassName());
			Object object = resultClass.newInstance();

			return object;
		} catch (Exception e) {
			throw new BeanCreationException(beanName, "creat bean is error", e);
		}
	}


	@Override
	public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}
}
