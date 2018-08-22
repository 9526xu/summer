package com.zoe.spring.core.io;

import com.zoe.spring.beans.util.ClassUtils;

import java.io.InputStream;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/12 15:08
 * @since JDK 1.6
 */
public class ClassPathResource implements Resources {

	private String configLocation;

	private ClassLoader beanClassLoader;

	public ClassPathResource(String configLocation) {
		this(configLocation, (ClassLoader) null);

	}

	public ClassPathResource(String configLocation, ClassLoader beanClassLoader) {
		this.configLocation = configLocation;
		this.beanClassLoader = (beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : beanClassLoader);

	}

	@Override
	public InputStream getInputStream() {
		return beanClassLoader.getResourceAsStream(configLocation);

	}

	@Override
	public String getDescription() {
		return this.configLocation;
	}
}
