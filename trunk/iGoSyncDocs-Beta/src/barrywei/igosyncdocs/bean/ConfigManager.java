/**
 * @(#)ObjectSerlization.java Jul 21, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 21, 2010
 * @since JDK1.6
 */
public class ConfigManager extends Properties{
	
	private static final long serialVersionUID = -8389499813576806249L;
	private static ConfigManager manager = null;
	private static String userDir = System.getProperty("user.home");
	private static File file = new File(userDir+File.separator+IConstant.App_Name+"-"+IConstant.App_Version+File.separator+"Config.conf");
	
	private ConfigManager() throws FileNotFoundException, IOException {
		load(new FileInputStream(file));
	}
	
	//never_confirm_for_exit 
	public static boolean isNeverConfirmForExit() throws IOException {
		if(manager == null)
			manager = new ConfigManager();
		String str = manager.getProperty("never_confirm_for_exit");
		if(str==null)
			return false;
		else
			return str.equals("yes");
	}
	
	//defualt_exit_action 
	public static boolean isDefaultExitActionTray() throws IOException {
		if(manager == null)
			manager = new ConfigManager();
		String str = manager.getProperty("defualt_exit_action");
		if(str==null)
			return false;
		else
			return str.equals("tray");
	}
	
	public static void setNeverConfirmForExit(String value) throws IOException {
		if(manager == null)
			manager = new ConfigManager();
		manager.setProperty("never_confirm_for_exit", value);
		if(file.canWrite()) {
			FileOutputStream fos = new FileOutputStream(file);
			manager.store(fos,"use setting for never_confirm_for_exit");
			fos.flush();
			fos.close();			
		}
	}
	
	public static void setDefaultExitAction(String value) throws IOException {	
		if(manager == null)
			manager = new ConfigManager();
		manager.setProperty("defualt_exit_action", value);
		if(file.canWrite()) {
			FileOutputStream fos = new FileOutputStream(file);
			manager.store(fos,"use setting for defualt_exit_action");
			fos.flush();
			fos.close();			
		}
	}
}
