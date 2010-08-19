/**
 * @(#)IGoSyncDocsAclDataTableCellRenderer.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.google.gdata.data.acl.AclEntry;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsAclDataTableCellRenderer extends JLabel implements
		TableCellRenderer {

	private static final long serialVersionUID = 5311712787031784039L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		if (value instanceof AclEntry) {
			AclEntry entry = (AclEntry) value;
			if (column == 0) {
				setText(entry.getScope().getValue());
			}
			if (column == 1) {
				setText(entry.getScope().getType().toString());
			}
			if (column == 2) {
				setText(entry.getRole().getValue());
			}
		}

		if (isSelected) {
			setBackground(table.getSelectionBackground());
			setForeground(table.getSelectionForeground());
		} else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
		}

		setEnabled(table.isEnabled());
		setFont(table.getFont());
		setOpaque(true);
		setIconTextGap(10);
		return this;
	}

}
