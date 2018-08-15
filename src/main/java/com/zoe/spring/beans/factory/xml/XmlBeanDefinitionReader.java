package com.zoe.spring.beans.factory.xml;

import com.zoe.spring.beans.PropertyValue;
import com.zoe.spring.beans.factory.BeanDefinitionStoreException;
import com.zoe.spring.beans.factory.config.BeanDefinition;
import com.zoe.spring.beans.factory.config.RuntimeBeanReference;
import com.zoe.spring.beans.factory.config.TypedStringValue;
import com.zoe.spring.beans.factory.config.support.GenericBeanDefinition;
import com.zoe.spring.beans.factory.support.BeanDefinitionRegistry;
import com.zoe.spring.beans.util.Assert;
import com.zoe.spring.core.io.Resources;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

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

	private static final String PROPERTY_TAG = "property";


	private static final String PROPERTY_NAME_ATTRIBUTE = "name";

	private static final String PROPERTY_REF_ATTRIBUTE = "ref";

	private static final String PROPERTY_VALUE_ATTRIBUTE = "value";

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
				preparePropertyValue(ele, bd);
				this.registry.registerBeanDefinition(id, bd);
			}
		} catch (Exception e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document from ", e);
		}
	}

	/**
	 * 设置 proper 标签相关属性
	 *
	 * @param beanElement
	 * @param bd
	 */
	private void preparePropertyValue(Element beanElement, BeanDefinition bd) {
		List<Element> propertyElements = beanElement.elements(PROPERTY_TAG);
		for (Element propertyElement : propertyElements) {
			String name = propertyElement.attributeValue("name");
			Object value = null;

			if (StringUtils.isNotBlank(propertyElement.attributeValue(PROPERTY_REF_ATTRIBUTE))) {
				RuntimeBeanReference beanReference = new RuntimeBeanReference(propertyElement.attributeValue(PROPERTY_REF_ATTRIBUTE));
				value = beanReference;
			} else if (StringUtils.isNotBlank(propertyElement.attributeValue(PROPERTY_VALUE_ATTRIBUTE))) {
				value = new TypedStringValue(propertyElement.attributeValue(PROPERTY_VALUE_ATTRIBUTE));
			} else {
				throw new BeanDefinitionStoreException("property tag is error");
			}
			PropertyValue propertyValue = new PropertyValue(name, value);
			bd.addPropertyValue(propertyValue);
		}
	}
}
