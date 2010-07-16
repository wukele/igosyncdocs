/**
 * @(#)IGoSyncDocsLocalViewComboboxItem.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import javax.swing.Icon;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsLocalViewComboboxItem {

	private Icon icon;
	private String path;
	private String text;
	private String parentPath;

	public IGoSyncDocsLocalViewComboboxItem() {
		super();
	}

	public IGoSyncDocsLocalViewComboboxItem(Icon icon, String path,
			String text, String parentPath) {
		super();
		this.icon = icon;
		this.path = path;
		this.text = text;
		this.parentPath = parentPath;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

}
