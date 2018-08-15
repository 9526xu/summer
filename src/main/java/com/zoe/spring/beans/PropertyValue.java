package com.zoe.spring.beans;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/14 21:15
 * @since JDK 1.6
 */
public class PropertyValue {
	private String name;

	private Object value;

	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Object getValue() {
		return this.value;
	}


}
