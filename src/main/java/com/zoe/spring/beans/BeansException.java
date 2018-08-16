package com.zoe.spring.beans;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/11 17:10
 * @since JDK 1.6
 */
public class BeansException extends RuntimeException {
	public BeansException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeansException(String message) {
		super(message);
	}
}
