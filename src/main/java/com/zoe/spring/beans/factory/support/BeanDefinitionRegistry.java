package com.zoe.spring.beans.factory.support;

import com.zoe.spring.beans.factory.config.BeanDefinition;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/12 12:45
 * @since JDK 1.6
 */
public interface BeanDefinitionRegistry {
	void registerBeanDefinition(String beanName, BeanDefinition bd);

	BeanDefinition getBeanDefinition(String beanName);
}
