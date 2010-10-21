/**
 * @(#)UserSettings.java Oct 18, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Locale;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 18, 2010
 * @since JDK1.6
 */
public class UserSettings implements Serializable{

	private static final long serialVersionUID = -4633382409728491029L;
	public String App_Data_Home = System.getProperty("user.home")+File.separator+"iGoSyncDocs-2011";
	public String Config_File_Path = App_Data_Home+File.separator+"iGoSyncDocs.Config.conf";
	public Locale UserLanguage = Locale.US;
	
	public String UserName = "";
	public boolean UseProxy = false;
	public String Proxy_Server = "";
	public String Proxy_Port = "";
	public String Proxy_Username = "";
	public String Proxy_Password = "";

}
