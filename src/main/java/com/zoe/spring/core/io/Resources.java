package com.zoe.spring.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 15:07
 * @since JDK 1.6
 */
public interface Resources {

	InputStream getInputStream() throws FileNotFoundException;

	/**
	 * 返回描述 如路径等
	 *
	 * @return
	 */
	String getDescription();
}
