package com.zoe.spring.beans.factory.support;

import com.google.common.collect.Maps;
import com.zoe.spring.beans.PropertyValue;
import com.zoe.spring.beans.factory.BeanCreationException;
import com.zoe.spring.beans.factory.ConfigurableBeanFactory;
import com.zoe.spring.beans.factory.config.BeanDefinition;
import com.zoe.spring.beans.factory.config.support.DefaultSingletonBeanRegistry;
import com.zoe.spring.beans.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author andyxu
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
			populateProperty(bd, object);
			return object;
		} catch (Exception e) {
			throw new BeanCreationException(beanName, "creat bean is error", e);
		}
	}

	/**
	 * @param bd
	 * @param object
	 */
	private void populateProperty(BeanDefinition bd, Object object) {
		BeanDefinitionValueResolver beanDefinitionValueResolver = new BeanDefinitionValueResolver(this);
		List<PropertyValue> propertyValues = bd.getPropertyValues();
		for (PropertyValue propertyValue : propertyValues) {
			Object convertedValue = propertyValue.getConvertedValue();
			if (convertedValue == null) {
				convertedValue = beanDefinitionValueResolver.resolveValueIfNecessary(propertyValue.getName(), propertyValue.getValue());
				propertyValue.setConvertedValue(convertedValue);
			}
			// 转化
			try {
				PropertyDescriptor propDesc = new PropertyDescriptor(propertyValue.getName(), object.getClass());
				Method methodSet = propDesc.getWriteMethod();
				methodSet.invoke(object, convertedValue);
			} catch (Exception e) {
				throw new BeanCreationException("get bean property error", e);
			}

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
