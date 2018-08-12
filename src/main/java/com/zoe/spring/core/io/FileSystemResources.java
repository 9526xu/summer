package com.zoe.spring.core.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author xurj@yintong.com.cn
 * @version V1.0
 * @Date 2018/8/12 15:09
 * @since JDK 1.6
 */
public class FileSystemResources implements Resources {


	private String configLocation;

	public FileSystemResources(String configLocation) {
		this.configLocation = configLocation;
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(this.configLocation);
	}

	@Override
	public String getDescription() {
		return this.configLocation;
	}
}
