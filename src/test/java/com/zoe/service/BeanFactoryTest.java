package com.zoe.service;

import com.zoe.service.v1.PetStoreService;
import com.zoe.spring.beans.factory.BeanCreationException;
import com.zoe.spring.beans.factory.BeanDefinitionStoreException;
import com.zoe.spring.beans.factory.config.BeanDefinition;
import com.zoe.spring.beans.factory.support.DefaultBeanFactory;
import com.zoe.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.zoe.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/11 16:27
 * @since JDK 1.8
 * beanFactory 测试
 */
public class BeanFactoryTest {
	DefaultBeanFactory beanFactory = null;
	XmlBeanDefinitionReader xmlBeanDefinitionReader = null;

	@Before
	public void setUp() {
		beanFactory = new DefaultBeanFactory();
		xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

	}


	@Test
	public void testGetBean() {

		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("test.xml"));

		BeanDefinition bd = beanFactory.getBeanDefinition("petStore");

		Assert.assertTrue(bd.isSingleton());

		Assert.assertFalse(bd.isPrototype());

		Assert.assertEquals("com.zoe.service.v1.PetStoreService", bd.getBeanClassName());

		Assert.assertEquals(bd.SCOPE_SINGLETON, bd.getScope());

		PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

		Assert.assertNotNull(petStoreService);

		PetStoreService petStoreService1 = (PetStoreService) beanFactory.getBean("petStore");

		Assert.assertEquals(petStoreService, petStoreService1);
	}

	@Test(expected = BeanCreationException.class)
	public void testInvalidBean() {
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("test.xml"));
		beanFactory.getBean("invalidBean");

		Assert.fail("testInvalidBean failed");
	}

	@Test(expected = BeanDefinitionStoreException.class)
	public void testInvalidBeanDefinition() {
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("test.xml"));
		beanFactory.getBeanDefinition("invalidBean");
		Assert.fail("testInvalidBeanDefinition failed");
	}
}
