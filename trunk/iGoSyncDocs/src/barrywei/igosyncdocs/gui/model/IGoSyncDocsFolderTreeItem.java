/**
 * @(#)IGoSyncDocsFolderTreeItem.java Jul 16, 2010
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
public class IGoSyncDocsFolderTreeItem {

	private Icon icon;
	private String text;
	private DocumentListEntry entry;

	public IGoSyncDocsFolderTreeItem() {
		super();
	}

	public IGoSyncDocsFolderTreeItem(Icon icon, String text) {
		super();
		this.icon = icon;
		this.text = text;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return this.getText();
	}

	public DocumentListEntry getEntry() {
		return entry;
	}

	public void setEntry(DocumentListEntry entry) {
		this.entry = entry;
	}
	
	
}
