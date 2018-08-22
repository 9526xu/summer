package com.zoe.spring.beans.factory.config;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/12 15:51
 * @since JDK 1.6
 */
public interface SingletonBeanRegistry {

	void registerSingleton(String beanName, Object singleton);

	Object getSingleton(String beanName);
}
