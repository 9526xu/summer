package com.zoe.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 13:18
 * @since JDK 1.6
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		BeanFactoryTest.class,
		ApplicationContextTest.class
})
public class AllTest {
}
