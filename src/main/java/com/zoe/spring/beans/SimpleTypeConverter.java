package com.zoe.spring.beans;

import com.google.common.collect.Maps;
import com.zoe.spring.beans.propertyeditors.CustomNumberEditor;
import com.zoe.spring.beans.util.ClassUtils;

import java.beans.PropertyEditor;
import java.util.Map;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/16 21:22
 * @since
 */
public class SimpleTypeConverter implements TypeConverter {

	private static Map<Class, PropertyEditor> editorMap;


	@Override
	public <T> T convertIfNecessary(Object value, Class<T> requiredType) {
		// 当前类跟 要转化的类相等 不需要转了

		if (ClassUtils.isAssignableValue(requiredType, value)) {
			return (T) value;
		} else {
			if (value instanceof String) {
				PropertyEditor propertyEditor = findDefaultEditor(requiredType);
			} else {
				throw new RuntimeException("Todo : can't convert value for " + value + " class:" + requiredType);
			}
		}
		return null;

	}

	private <T> PropertyEditor findDefaultEditor(Class<T> requiredType) {

		if (editorMap.get(requiredType) != null) {
			return editorMap.get(requiredType);
		} else {
			throw new RuntimeException("don't have PropertyEditor for this type" + requiredType);
		}
	}

	static {
		editorMap = Maps.newHashMap();
		editorMap.put(Integer.class, new CustomNumberEditor(Integer.class, false));
	}
}
