/**
 * @(#)IGoSyncDocsLocalViewTableCellRenderer.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import barrywei.igosyncdocs.gui.model.IGoSyncDocsLocalViewTableItem;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsLocalViewTableCellRenderer extends JLabel implements
		TableCellRenderer {

	private static final long serialVersionUID = 3202690049512030433L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (value instanceof IGoSyncDocsLocalViewTableItem) {
			IGoSyncDocsLocalViewTableItem item = (IGoSyncDocsLocalViewTableItem) value;

			if (column == 0) {
				setIcon(item.getIcon());
				setText(item.getName());
			} else if (column == 1) {
				setText(item.getLastModified());
				setHorizontalAlignment(SwingConstants.CENTER);
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
