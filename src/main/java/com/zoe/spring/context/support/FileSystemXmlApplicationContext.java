package com.zoe.spring.context.support;

import com.zoe.spring.core.io.FileSystemResources;
import com.zoe.spring.core.io.Resources;

/**
 * @author andyxu
 * @version V1.0
 * @Date 2018/8/12 15:01
 * @since JDK 1.6
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {


	public FileSystemXmlApplicationContext(String configLocation) {
		super(configLocation);
	}

	@Override
	protected Resources getResourcesByPath() {
		return new FileSystemResources(this.configLocation);
	}


}
