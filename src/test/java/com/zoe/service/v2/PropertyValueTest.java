package com.zoe.service.v2;

import com.zoe.spring.beans.PropertyValue;
import com.zoe.spring.beans.factory.config.BeanDefinition;
import com.zoe.spring.beans.factory.config.RuntimeBeanReference;
import com.zoe.spring.beans.factory.config.TypedStringValue;
import com.zoe.spring.beans.factory.support.DefaultBeanFactory;
import com.zoe.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.zoe.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/14 21:10
 * @since JDK 1.6
 */
public class PropertyValueTest {

	DefaultBeanFactory beanFactory = null;
	XmlBeanDefinitionReader xmlBeanDefinitionReader = null;

	@Before
	public void setUp() {
		beanFactory = new DefaultBeanFactory();
		xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

	}

	@Test
	public void testProperty() {
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

		BeanDefinition bd = beanFactory.getBeanDefinition("petStore");

		// 获取 PropertyValues
		List<PropertyValue> PropertyValues = bd.getPropertyValues();

		Assert.assertNotNull(PropertyValues);
		Assert.assertEquals(PropertyValues.size(), 3);

		for (PropertyValue propertyValue : PropertyValues) {
			if (propertyValue.getName().equals("accountDao")) {
				Assert.assertEquals(propertyValue.getValue().getClass().getName(), RuntimeBeanReference.class.getName());
			} else if (propertyValue.getName().equals("owner")) {
				Assert.assertEquals(propertyValue.getValue().getClass().getName(), TypedStringValue.class.getName());
			}

		}


	}
}
