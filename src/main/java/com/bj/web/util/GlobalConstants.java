package com.bj.web.util;

import java.util.Properties;

import com.bj.web.start.InterfaceUrlInti;

public class GlobalConstants {
	public static Properties interfaceUrlProperties;

	/**
	 * 
	 * @Description: TODO
	 * @param @param key
	 * @param @return   
	 * @author dapengniao
	 * @date 2015年10月13日 下午4:59:14
	 */
		public static String getInterfaceUrl(String key) {
			return (String) interfaceUrlProperties.get(key);
		}
		
	 
}
