/**
 * @(#)AllItemTableModel.java Oct 19, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import barrywei.igosyncdocsv2.biz.IGoSyncDocsBiz;

import com.google.gdata.data.docs.DocumentListEntry;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 19, 2010
 * @since JDK1.6
 */
public class EntityTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 3857728294426996858L;
	private List<DocumentListEntry> entries;
	private Vector<String> columnName;

	public EntityTableModel(String type) {
		if(type.trim().equalsIgnoreCase("all"))
			entries = IGoSyncDocsBiz.getAllItems();
		else if(type.trim().equalsIgnoreCase("document"))
			entries = IGoSyncDocsBiz.getAllDocuments();
		else if(type.trim().equalsIgnoreCase("spreadsheet"))
			entries = IGoSyncDocsBiz.getAllSpreadsheets();
		else if(type.trim().equalsIgnoreCase("presentation"))
			entries = IGoSyncDocsBiz.getAllPresentations();
		else if(type.trim().equalsIgnoreCase("other"))
			entries = IGoSyncDocsBiz.getAllOthers();
		else if(type.trim().equalsIgnoreCase("hidden")) 
			entries = IGoSyncDocsBiz.getHiddenObjects();
		else if(type.trim().equalsIgnoreCase("stared"))
			entries = IGoSyncDocsBiz.getStaredObjects();
		else if(type.trim().equalsIgnoreCase("trashed"))
			entries = IGoSyncDocsBiz.getTrashedObjects();
		else if(type.trim().equalsIgnoreCase("shared"))
			entries = IGoSyncDocsBiz.getSharedWithMeObjects();
		columnName = new Vector<String>();
		columnName.add("Star");
		columnName.add("Type");
		columnName.add("Name");
		columnName.add("Last Updated");
	}

	public int getColumnCount() {
		if(columnName == null)
			return 0;
		else
			return columnName.size();
	}

	public int getRowCount() {
		if(entries == null)
			return 0;
		else
			return entries.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(entries == null)
			return null;
		else
			return entries.get(rowIndex);
	}
	
	public String getColumnName(int column) {
		if(columnName == null)
			return null;
		else
			return columnName.get(column);
	}

}
 