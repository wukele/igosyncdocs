/**
 * @(#)Runtime.java Oct 18, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.docs.DocumentListFeed;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 18, 2010
 * @since JDK1.6
 */
public class SystemRuntime implements Serializable {

	private static final long serialVersionUID = 3636286982031235354L;
	public static UserSettings Settings = new UserSettings();
	public static DocumentListFeed CachedDocumentFeed = null;
	public static List<AclFeed> ChachedEntryAclFeed = new ArrayList<AclFeed>();
	
	public static void loadUserSettings() throws FileNotFoundException, IOException, ClassNotFoundException {		
		File file = new File(Settings.Config_File_Path);
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			Object obj = ois.readObject();
			if(obj != null && obj instanceof UserSettings)
				Settings = (UserSettings)obj;
			ois.close();
		}//end of if
	}//end of method
	
	public static void saveUserSettings(UserSettings settings) throws FileNotFoundException, IOException {
		File file = new File(Settings.Config_File_Path);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(settings);
		oos.flush();
		oos.close();		
	}//end of method
	
	public static boolean testProxyConnection() {
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("https.proxyHost", SystemRuntime.Settings.Proxy_Server);
		System.getProperties().put("https.proxyPort", SystemRuntime.Settings.Proxy_Port);
		System.getProperties().put("http.proxyHost", SystemRuntime.Settings.Proxy_Server);
		System.getProperties().put("http.proxyPort", SystemRuntime.Settings.Proxy_Port);
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(SystemRuntime.Settings.Proxy_Server, Integer.parseInt(SystemRuntime.Settings.Proxy_Port)));
			HttpURLConnection conn = (HttpURLConnection) new URL("https://www.google.com").openConnection(proxy);
			conn.getContent();
			conn.disconnect();
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		} catch(Exception e) {
			return false;
		}
		System.getProperties().put("proxySet", "false");
		return true;
	}
}
