package com.zoe.spring.beans.factory.config;

import lombok.Data;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/14 21:23
 * @since JDK 1.6
 */
@Data
public class TypedStringValue {
	public TypedStringValue(String value) {
		this.value = value;
	}

	private String value;

}
