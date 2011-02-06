/**
 * @(#)MyFolderPanel.java Oct 12, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.panel;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;

import barrywey.igosyncdocs2011.gui.util.FaceUtils;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 12, 2010
 * @since JDK1.6
 */
public class MyFolderPanel extends JSplitPane {

	private static final long serialVersionUID = -7081628537163336918L;
	private JScrollPane pnlLeft;
	private JScrollPane pnlRight;
	private JTree tree;
	private JTable table;

	public MyFolderPanel() {
		initComponents();
	}
	private void initComponents() {
		setResizeWeight(0.2);
		
		pnlLeft = new JScrollPane();
		pnlLeft.setName("pnlLeft");
		setLeftComponent(pnlLeft);
		
		tree = new JTree();
		tree.setRootVisible(false);
		tree.setName("tree");
		pnlLeft.setViewportView(tree);
		FaceUtils.expandTree(tree);
		
		pnlRight = new JScrollPane();
		pnlRight.setName("pnlRight");
		setRightComponent(pnlRight);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.setName("table");
		pnlRight.setViewportView(table);
	}

}
