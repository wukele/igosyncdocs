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
public class AllItemTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 3857728294426996858L;
	private List<DocumentListEntry> entries;
	private Vector<String> columnName;

	public AllItemTableModel() {
		entries = IGoSyncDocsBiz.getAllDocuments();
		columnName = new Vector<String>();
		columnName.add("type");
		columnName.add("title");
		columnName.add("resource id");
		columnName.add("etag");
		columnName.add("document link");
		columnName.add("kind");
		columnName.add("update time");
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
		else {
			DocumentListEntry entry = entries.get(rowIndex);
			String message = "";
			switch (columnIndex) {
			case 0:
				message = entry.getType();
				break;
			case 1:
				message = entry.getTitle().getPlainText();
				break;
			case 2:
				message = entry.getResourceId();
				break;
			case 3:
				message = entry.getEtag();
				break;
			case 4:
				message = entry.getDocumentLink().getHref();
				break;
			case 5:
				message = entry.getKind();
				break;
			case 6:
				message = entry.getUpdated().toStringRfc822();
				break;				
			}
			return message;
		}//end of if
	}
	
	public String getColumnName(int column) {
		if(columnName == null)
			return null;
		else
			return columnName.get(column);
	}

}
 