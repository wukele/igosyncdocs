/**
 * @(#)EntityTableHeaderRenderer.java Oct 21, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui.renderer;

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
 * @author BarryWei
 * @version 1.0, Oct 21, 2010
 * @since JDK1.6
 */
public class EntityTableHeaderRenderer extends JLabel implements
		TableCellRenderer {

	private static final long serialVersionUID = 6449791158608857374L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (column == 3 || column == 0 || column == 1) {
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		setText((String) value);

		setBackground(table.getTableHeader().getBackground());
		setForeground(table.getTableHeader().getForeground());

		setEnabled(table.isEnabled());
		setFont(table.getFont());
		setOpaque(true);
		//setBorder(UIManager.getBorder("TableHeader.cellBorder"));

		return this;
	}

}
