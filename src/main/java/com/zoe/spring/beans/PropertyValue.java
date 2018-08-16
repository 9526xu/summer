package com.zoe.spring.beans;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/14 21:15
 * @since JDK 1.6
 */
public class PropertyValue {
	private String name;

	private Object value;

	private Object convertedValue;

	public Object getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(Object convertedValue) {
		this.convertedValue = convertedValue;
	}

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
