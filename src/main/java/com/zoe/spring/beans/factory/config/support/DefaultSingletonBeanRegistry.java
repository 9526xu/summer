package com.zoe.spring.beans.factory.config.support;

import com.google.common.collect.Maps;
import com.zoe.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 15:53
 * @since JDK 1.6
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private Map<String, Object> singletonMap;

	public DefaultSingletonBeanRegistry() {
		this.singletonMap = Maps.newConcurrentMap();
	}

	@Override
	public void registerSingleton(String beanName, Object singleton) {
		singletonMap.put(beanName, singleton);
	}

	@Override
	public Object getSingleton(String beanName) {
		return singletonMap.get(beanName);
	}
}
