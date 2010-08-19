/**
 * @(#)IGoSyncDocsRemoteViewTableItem.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import javax.swing.Icon;

import com.google.gdata.data.docs.DocumentListEntry;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsRemoteViewTableItem {

	private boolean stared;
	private DocumentListEntry entry;
	private String name;
	private String lastViewdDate;
	private String lastViewdByWhom;
	private Icon icon;
	private String type;
	private boolean hidden;

	public IGoSyncDocsRemoteViewTableItem() {
		super();
	}

	public IGoSyncDocsRemoteViewTableItem(boolean stared,
			DocumentListEntry entry, String name, String lastViewdDate,
			String lastViewdByWhom, Icon icon, String type) {
		super();
		this.stared = stared;
		this.entry = entry;
		this.name = name;
		this.lastViewdDate = lastViewdDate;
		this.lastViewdByWhom = lastViewdByWhom;
		this.icon = icon;
		this.type = type;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isStared() {
		return stared;
	}

	public void setStared(boolean stared) {
		this.stared = stared;
	}

	public DocumentListEntry getEntry() {
		return entry;
	}

	public void setEntry(DocumentListEntry entry) {
		this.entry = entry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastViewdDate() {
		return lastViewdDate;
	}

	public void setLastViewdDate(String lastViewdDate) {
		this.lastViewdDate = lastViewdDate;
	}

	public String getLastViewdByWhom() {
		return lastViewdByWhom;
	}

	public void setLastViewdByWhom(String lastViewdByWhom) {
		this.lastViewdByWhom = lastViewdByWhom;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
