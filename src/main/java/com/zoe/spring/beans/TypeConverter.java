package com.zoe.spring.beans;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/16 21:19
 * @since 实现类型转化
 */
public interface TypeConverter {

	<T> T convertIfNecessary(Object value,Class<T> requireType);
}
