package com.zoe.spring.beans.factory;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/11 16:33
 * @since JDK 1.6
 */
public interface BeanFactory {
	Object getBean(String beanId);
}
