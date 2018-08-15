package com.zoe.spring.beans.factory.config;

import lombok.Data;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/14 21:20
 * @since JDK 1.6
 */
@Data
public class RuntimeBeanReference {

	private String beanName;

	public RuntimeBeanReference(String beanName) {
		this.beanName = beanName;
	}
}
