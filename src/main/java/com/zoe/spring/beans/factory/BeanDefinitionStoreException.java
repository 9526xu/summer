package com.zoe.spring.beans.factory;

import com.zoe.spring.beans.BeansException;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/11 17:08
 * @since JDK 1.6
 */
public class BeanDefinitionStoreException extends BeansException {

	public BeanDefinitionStoreException(String message) {
		super(message);
	}

	public BeanDefinitionStoreException(String message, Throwable cause) {
		super(message, cause);
	}
}
