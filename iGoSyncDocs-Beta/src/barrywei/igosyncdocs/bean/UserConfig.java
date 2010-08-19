/**
 * @(#)UserConfig.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.bean;

import java.io.Serializable;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class UserConfig implements Serializable {

	private static final long serialVersionUID = -4477318052519845947L;
	public static String Username = null;
	public static String[] DocumentFileFormat = {"doc","txt","odt","pdf","png","rtf","html","zip"};
	public static String[] PresentationtFileFormat = {"ppt","png","pdf","swf","txt"};
	public static String[] SpreadsheetFileFormat = {"xls","ods","pdf","csv","tsv","html"};

}
