package com.zoe.spring.context.support;

import com.zoe.spring.core.io.ClassPathResource;
import com.zoe.spring.core.io.Resources;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 13:12
 * @since JDK 1.6
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


	public ClassPathXmlApplicationContext(String configLocation) {
		super(configLocation);
	}

	public ClassPathXmlApplicationContext(String configLocation, ClassLoader beanClassLoader) {
		super(configLocation, beanClassLoader);
	}

	@Override
	protected Resources getResourcesByPath() {
		return new ClassPathResource(this.configLocation, this.beanClassLoader);
	}


}
