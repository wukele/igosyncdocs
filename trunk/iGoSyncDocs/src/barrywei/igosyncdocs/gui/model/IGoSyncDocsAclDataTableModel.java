/**
 * @(#)IGoSyncDocsAclDataTableModel.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsAclDataTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -2468116724661911232L;
	private Vector<AclEntry> data = null;
	private Vector<String> columnNames = null;
	private AclFeed feeds;

	public IGoSyncDocsAclDataTableModel(AclFeed feeds) {
		this.feeds = feeds;
		initData();
	}
	
	public Vector<AclEntry> getData() {
		return data;
	}
	
	private void initData() {
		if(feeds!=null) {
			data = new Vector<AclEntry>();
			for(AclEntry entry : feeds.getEntries()) {
				data.add(entry);
			}
			columnNames = new Vector<String>();
			columnNames.add("Name");
			columnNames.add("Type");
			columnNames.add("Role");
		}
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		if (columnNames == null)
			return null;
		else
			return columnNames.get(column);
	}
	
	@Override
	public int getColumnCount() {
		if (columnNames == null)
			return 0;
		else
			return columnNames.size();
	}

	@Override
	public int getRowCount() {
		if (data == null)
			return 0;
		else
			return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (data == null)
			return null;
		else {
			return data.get(rowIndex);
		}// end of else
	}

	
}
