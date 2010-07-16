/**
 * @(#)IGoSyncDocsCategoryComboboxItem.java Jul 16, 2010
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
public class IGoSyncDocsCategoryComboboxItem {

	private Icon icon;
	private String text;
	private int type;

	public IGoSyncDocsCategoryComboboxItem() {
		super();
	}

	public IGoSyncDocsCategoryComboboxItem(Icon icon, String text, int type) {
		super();
		this.icon = icon;
		this.text = text;
		this.type = type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
