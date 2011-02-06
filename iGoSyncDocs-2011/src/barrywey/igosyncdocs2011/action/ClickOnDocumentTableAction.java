/**
 * package:barrywei.igosyncdocsv2.action
 * @(#)ClickOnDocumentTableAction.java Dec 29, 2010
 * Copyright 2010 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import com.google.gdata.data.docs.DocumentListEntry;

import barrywey.igosyncdocs2011.gui.model.EntryTableModel;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Dec 29, 2010
 * @since JDK1.6
 */
public class ClickOnDocumentTableAction extends MouseAdapter {

	private JTable table;

	public ClickOnDocumentTableAction(JTable table) {
		this.table = table;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			int selRow = table.getSelectedRow();
			if (selRow != -1) {
				DocumentListEntry entry = ((EntryTableModel)table.getModel()).getEntries().get(selRow);
				if(entry != null) {
					System.out.println(entry.getTitle().getPlainText());
				}//end of if
			}
		}
	}// end of method
}
