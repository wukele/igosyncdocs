/**
 * @(#)IGoSyncDocsLocalViewTableItem.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import java.io.Serializable;

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
public class IGoSyncDocsLocalViewTableItem implements Serializable {

	private static final long serialVersionUID = -5637604882581403267L;
	private String name;
	private Icon icon;
	private String actionCommand;
	private boolean file;
	private boolean directory;
	private String lastModified;
	private String size;

	public IGoSyncDocsLocalViewTableItem() {
		super();
	}

	public IGoSyncDocsLocalViewTableItem(String name, Icon icon,
			String actionCommand, boolean file, boolean directory,
			String lastModified, String size) {
		super();
		this.name = name;
		this.icon = icon;
		this.actionCommand = actionCommand;
		this.file = file;
		this.directory = directory;
		this.lastModified = lastModified;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getActionCommand() {
		return actionCommand;
	}

	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}

	public boolean isFile() {
		return file;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
