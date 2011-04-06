/**
 * @(#)Language.java Oct 12, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import barrywey.igosyncdocs2011.bean.SystemRuntime;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 12, 2010
 * @since JDK1.6
 */
public class LanguageResource {

	public static String getStringValue(String key) {
//		File configFile = new File(SystemRuntime.Settings.Config_File_Path);
//		Properties pro = new Properties();
//		try {
//			pro.load(new FileInputStream(configFile));
//		} catch (FileNotFoundException e) {
//		} catch (IOException e) {
//		}
//		String defaultLanguage = pro.getProperty("default.language");
		return ResourceBundle.getBundle(			
				"barrywey.igosyncdocs2011.resource.lang.LanguageResource",
				Locale.getDefault(), LanguageResource.class.getClassLoader())
				.getString(key);
	}
}
