package com.zoe.service;

import com.zoe.service.v1.PetStoreService;
import com.zoe.spring.context.ApplicationContext;
import com.zoe.spring.context.support.ClassPathXmlApplicationContext;
import com.zoe.spring.context.support.FileSystemXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 13:06
 * @since JDK 1.6
 */
public class ApplicationContextTest {

	@Test
	public void testapplication() {
		ApplicationContext context = new ClassPathXmlApplicationContext("test.xml",ApplicationContextTest.class.getClassLoader());

		PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");

		Assert.assertNotNull(petStoreService);

		PetStoreService petStoreService1 = (PetStoreService) context.getBean("petStore");

		Assert.assertEquals(petStoreService, petStoreService1);
	}

	@Test
	public void testFileSystemApplicationContext() {
		ApplicationContext context = new FileSystemXmlApplicationContext("E:\\github\\spring\\src\\test\\resources\\test.xml");

		PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");

		Assert.assertNotNull(petStoreService);

		PetStoreService petStoreService1 = (PetStoreService) context.getBean("petStore");

		Assert.assertEquals(petStoreService, petStoreService1);
	}
}
