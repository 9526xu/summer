package com.zoe.service.v2;

import com.zoe.dao.v2.AccountDao;
import com.zoe.dao.v2.ItemDao;
import com.zoe.spring.beans.factory.support.DefaultBeanFactory;
import com.zoe.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.zoe.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author
 * @version V1.0
 * @Date 2018/8/16 19:57
 * @since JDK 1.6
 */
public class BeanDefinitionValueResolverTest {

	DefaultBeanFactory beanFactory = null;
	XmlBeanDefinitionReader xmlBeanDefinitionReader = null;

	@Before
	public void setUp() {
		beanFactory = new DefaultBeanFactory();
		xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

	}


	/**
	 * 测试类型转换
	 */
	@Test
	public void valueResolverTest() {
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));


		PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

		Assert.assertNotNull(petStoreService);

		Assert.assertNotNull(petStoreService.getAccountDao());
		AccountDao accountDao = (AccountDao) beanFactory.getBean("accountDao");

		Assert.assertEquals(accountDao, petStoreService.getAccountDao());

		ItemDao itemDao = (ItemDao) beanFactory.getBean("itemDao");

		Assert.assertEquals(itemDao, petStoreService.getItemDao());

		Assert.assertEquals("liuxin", petStoreService.getOwner());

		Assert.assertEquals(2, petStoreService.getVersion());
	}
}
