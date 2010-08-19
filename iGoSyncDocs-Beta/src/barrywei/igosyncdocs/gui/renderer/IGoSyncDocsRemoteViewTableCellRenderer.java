/**
 * @(#)IGoSyncDocsRemoteViewTableCellRenderer.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.renderer;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import barrywei.igosyncdocs.bean.IGoImageManager;
import barrywei.igosyncdocs.gui.model.IGoSyncDocsRemoteViewTableItem;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsRemoteViewTableCellRenderer extends JLabel implements
		TableCellRenderer {

	private static final long serialVersionUID = 3717742565339639284L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (value instanceof IGoSyncDocsRemoteViewTableItem) {
			IGoSyncDocsRemoteViewTableItem item = (IGoSyncDocsRemoteViewTableItem) value;

			if (column == 0) {
				Icon icon = item.isStared() ? IGoImageManager.getInstance()
						.getIcon("listicon/stared.png") : IGoImageManager
						.getInstance().getIcon("listicon/stared-not.png");
				setIcon(icon);
			} else if (column == 1) {
				setIcon(item.getIcon());
			} else if (column == 2) {
				setText(item.getName());
			} else if (column == 3) {
				setText(item.getLastViewdDate());
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
