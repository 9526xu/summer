package com.zoe.spring.beans.factory.support;

import com.zoe.spring.beans.factory.BeanDefinitionStoreException;
import com.zoe.spring.beans.factory.BeanFactory;
import com.zoe.spring.beans.factory.config.RuntimeBeanReference;
import com.zoe.spring.beans.factory.config.TypedStringValue;
import com.zoe.spring.beans.util.Assert;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/16 20:02
 * @since 转化 propertyValue 所需要值
 */
public class BeanDefinitionValueResolver {

	private BeanFactory beanFactory;

	public BeanDefinitionValueResolver(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	/**
	 * @param value propertyValue中的对象
	 * @return
	 */
	public Object resolveValueIfNecessary(Object value) {
		Assert.notNull(value);
		if (value instanceof RuntimeBeanReference) {
			RuntimeBeanReference ref = (RuntimeBeanReference) value;
			return resolveReference(ref);
		} else if (value instanceof TypedStringValue) {
			TypedStringValue typedString = (TypedStringValue) value;
			return resolveTypedString(typedString);
		} else {
			throw new BeanDefinitionStoreException("property is error");
		}

	}

	private Object resolveTypedString(TypedStringValue typedString) {
		return typedString.getValue();
	}

	private Object resolveReference(RuntimeBeanReference ref) {
		Assert.notNull(ref.getBeanName());
		return beanFactory.getBean(ref.getBeanName());
	}
}
