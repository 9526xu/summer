package com.zoe.service.v2;

import com.zoe.spring.beans.propertyeditors.CustomBooleanEditor;
import com.zoe.spring.beans.propertyeditors.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/16 22:32
 * @since
 */
public class PropertyEditorTest {

	@Test
	public void customNumberTest() {
		CustomNumberEditor customNumberEditor = new CustomNumberEditor(Integer.class, false);

		customNumberEditor.setAsText("11");

		Object value = customNumberEditor.getValue();

		Assert.assertEquals(((Integer) value).intValue(), 11);

		Assert.assertTrue(value instanceof Integer);


		try {
			customNumberEditor.setAsText("11.7");
		} catch (IllegalArgumentException e) {
			return;
		}

		Assert.fail();

	}

	@Test
	public void customBooleanTest() {
		CustomBooleanEditor customBooleanEditor = new CustomBooleanEditor(false);
		customBooleanEditor.setAsText("false");

		Object value = customBooleanEditor.getValue();

		Assert.assertEquals(((Boolean) value).booleanValue(), false);

		Assert.assertTrue(value instanceof Boolean);

		try {
			customBooleanEditor.setAsText("onn");
		} catch (IllegalArgumentException e) {
			return;
		}

		Assert.fail();
	}
}
