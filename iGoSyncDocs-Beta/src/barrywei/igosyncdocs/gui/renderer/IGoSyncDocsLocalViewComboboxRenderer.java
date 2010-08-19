/**
 * @(#)IGoSyncDocsLocalViewComboboxRenderer.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import barrywei.igosyncdocs.gui.model.IGoSyncDocsLocalViewComboboxItem;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsLocalViewComboboxRenderer extends JLabel implements ListCellRenderer{

	private static final long serialVersionUID = 3087038761392264042L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		if(value instanceof IGoSyncDocsLocalViewComboboxItem) {
			IGoSyncDocsLocalViewComboboxItem item = (IGoSyncDocsLocalViewComboboxItem)value;
			setIcon(item.getIcon());
			setText(item.getText());
		}
		
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		setIconTextGap(15);		
		
		return this;
	}
	
}
