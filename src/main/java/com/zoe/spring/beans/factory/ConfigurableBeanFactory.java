package com.zoe.spring.beans.factory;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/12 15:35
 * @since JDK 1.6
 */
public interface ConfigurableBeanFactory extends BeanFactory {

	ClassLoader getBeanClassLoader();

	void setBeanClassLoader(ClassLoader beanClassLoader);
}
