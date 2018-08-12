package com.zoe.spring.beans.factory.xml;

import com.zoe.spring.beans.factory.BeanDefinitionStoreException;
import com.zoe.spring.beans.factory.config.BeanDefinition;
import com.zoe.spring.beans.factory.config.support.GenericBeanDefinition;
import com.zoe.spring.beans.factory.support.BeanDefinitionRegistry;
import com.zoe.spring.beans.util.Assert;
import com.zoe.spring.core.io.Resources;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 12:46
 * @since JDK 1.6
 */
public class XmlBeanDefinitionReader {

	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	private static final String SCOPE_ATTRIBUTE = "scope";

	private BeanDefinitionRegistry registry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}


	public void loadBeanDefinitions(Resources resources) {
		// 加载 beanDefinition 定义xml
		Assert.notNull(resources);

		try (InputStream is = resources.getInputStream()) {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);

			Element root = doc.getRootElement(); //<beans>
			Iterator<Element> iter = root.elementIterator();
			while (iter.hasNext()) {
				Element ele = (Element) iter.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
				if (ele.attribute(SCOPE_ATTRIBUTE) != null) {
					bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
				}
				this.registry.registerBeanDefinition(id, bd);
			}
		} catch (Exception e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document from ", e);
		}
	}
}
