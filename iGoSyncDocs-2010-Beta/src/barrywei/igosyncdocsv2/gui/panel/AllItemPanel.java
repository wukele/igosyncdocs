/**
 * @(#)AllItemPanel.java Oct 12, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import barrywei.igosyncdocsv2.gui.model.AllItemTableModel;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 12, 2010
 * @since JDK1.6
 */
public class AllItemPanel extends JPanel {
	
	private static final long serialVersionUID = -858390102352879991L;
	private JScrollPane scrollPane;
	private JTable table;

	public AllItemPanel() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setName("scrollPane");
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new AllItemTableModel());
		table.setName("table");
		scrollPane.setViewportView(table);
	}

}
