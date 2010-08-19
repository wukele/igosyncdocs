/**
 * @(#)IGoSyncDocsLocalViewTableHaderRenderer.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsLocalViewTableHaderRenderer extends JLabel implements
		TableCellRenderer {

	private static final long serialVersionUID = 7858530854636062025L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (column == 0) {
			setText((String) value);
			setHorizontalAlignment(SwingConstants.LEFT);

		} else if (column == 1) {
			setText((String) value);
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		setBackground(table.getTableHeader().getBackground());
		setForeground(table.getTableHeader().getForeground());

		setEnabled(table.isEnabled());
		setFont(table.getFont());
		setOpaque(true);
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));

		return this;
	}
}
