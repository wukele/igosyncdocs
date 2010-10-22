/**
 * @(#)DocumentPanel.java Oct 22, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import barrywei.igosyncdocsv2.gui.model.EntityTableModel;
import barrywei.igosyncdocsv2.gui.renderer.EntityTableCellRenderer;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 22, 2010
 * @since JDK1.6
 */
public class OtherFilesPanel extends JPanel{
	
	public OtherFilesPanel() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout(5, 0));
		
		pnlCenter = new JScrollPane();
		pnlCenter.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCenter.setName("pnlCenter");
		add(pnlCenter, BorderLayout.CENTER);
		
		tblAllItems = new JTable();
		tblAllItems.setModel(new EntityTableModel("other"));
		tblAllItems.setName("tblAllItems");
		initTableSettings(tblAllItems);
		pnlCenter.setViewportView(tblAllItems);
		
		pnlRight = new JScrollPane();
		pnlRight.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlRight.setPreferredSize(new Dimension(200, 20));
		pnlRight.setName("pnlRight");
		add(pnlRight, BorderLayout.EAST);
		
		pnlAcl = new JPanel();
		pnlAcl.setName("pnlAcl");
		pnlRight.setViewportView(pnlAcl);
	}

	private void initTableSettings(JTable tbl) {
		tbl.getTableHeader().setReorderingAllowed(false);
		tbl.setRowHeight(20);
		tbl.setAutoCreateRowSorter(true);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbl.getColumnModel().getColumn(0).setCellRenderer(new EntityTableCellRenderer());
		tbl.getColumnModel().getColumn(1).setPreferredWidth(30);
		tbl.getColumnModel().getColumn(1).setCellRenderer(new EntityTableCellRenderer());
		tbl.getColumnModel().getColumn(2).setPreferredWidth(500);
		tbl.getColumnModel().getColumn(2).setCellRenderer(new EntityTableCellRenderer());
		tbl.getColumnModel().getColumn(3).setPreferredWidth(130);
		tbl.getColumnModel().getColumn(3).setCellRenderer(new EntityTableCellRenderer());
	}
	
	private JScrollPane pnlCenter;
	private JTable tblAllItems;
	private JScrollPane pnlRight;
	private JPanel pnlAcl;			
	private static final long serialVersionUID = -4246191262559940915L;

}
