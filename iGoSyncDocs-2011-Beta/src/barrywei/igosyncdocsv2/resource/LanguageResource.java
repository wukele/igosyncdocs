/**
 * @(#)Language.java Oct 12, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.resource;

import java.util.Locale;
import java.util.ResourceBundle;

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
		return ResourceBundle.getBundle("barrywei.igosyncdocsv2.resource.lang.LanguageResource",Locale.getDefault()).getString(key);
	}
}
