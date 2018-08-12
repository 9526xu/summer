package com.zoe.spring.beans.factory;

import com.zoe.spring.beans.BeansException;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 12:27
 * @since JDK 1.6
 */
public class BeanCreationException extends BeansException {

	private String beanName;

	public BeanCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanCreationException(String message) {
		super(message);
	}

	public BeanCreationException(String beanName, String message, Throwable cause) {
		super(message, cause);
		this.beanName = beanName;
	}
}
