/**
 * @(#)IGoPropertiesManager.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.bean;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoPropertiesManager {

	private static IGoPropertiesManager instance;
	private Properties pro;

	private IGoPropertiesManager() throws IOException {
		pro = new Properties();
		pro.load(getClass().getResourceAsStream("/barrywei/igosyncdocs/resource/config/config.pro"));
	}

	public static IGoPropertiesManager getInstance() throws IOException {
		if (instance == null)
			instance = new IGoPropertiesManager();
		return instance;
	}

	public String get(String key) {
		return pro.getProperty(key);
	}
}
