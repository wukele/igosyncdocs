/**
 * @(#)Runtime.java Oct 18, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import barrywey.igosyncdocs2011.gui.dialog.UploadFileProcessDialog;

import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.docs.DocumentListEntry;
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
	public static List<AclFeed> CachedEntryAclFeed = new ArrayList<AclFeed>();
	public static List<DocumentListEntry> SelectedItem = new ArrayList<DocumentListEntry>();
	public static UploadFileProcessDialog UploadDialog = null;
	
}
