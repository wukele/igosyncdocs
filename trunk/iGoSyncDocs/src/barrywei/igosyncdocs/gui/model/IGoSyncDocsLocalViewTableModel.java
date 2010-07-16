/**
 * @(#)IGoSyncDocsLocalViewTableModel.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;

import barrywei.igosyncdocs.bean.IGoImageManager;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsLocalViewTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1271570670270316638L;
	private Vector<IGoSyncDocsLocalViewTableItem> data = null;
	private Vector<String> columnNames = null;

	public IGoSyncDocsLocalViewTableModel(String path) {
		if (path == null)
			path = "d:\\";
		initData(path);
	}

	private void initData(String path) {
		data = new Vector<IGoSyncDocsLocalViewTableItem>();
		File directory = new File(path);

		// up item
		IGoSyncDocsLocalViewTableItem up = new IGoSyncDocsLocalViewTableItem();
		up.setName("....");
		up.setActionCommand(directory.getParent());
		up.setIcon(IGoImageManager.getInstance().getIcon("up.png"));
		up.setDirectory(true);
		up.setFile(false);
		data.add(up);

		if(directory.listFiles()!=null && directory.listFiles().length>0) {
			
			for (File file : directory.listFiles()) {
				if (!file.isHidden() && file.isDirectory()) {
					IGoSyncDocsLocalViewTableItem item = new IGoSyncDocsLocalViewTableItem();
					item.setName(file.getName());
					item.setActionCommand(file.getAbsolutePath());
					item.setIcon(getIconOfFile(file));
					item.setDirectory(file.isDirectory());
					item.setFile(file.isFile());
					item.setSize(String.valueOf(file.getFreeSpace()));
					String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(file.lastModified()));
					item.setLastModified(time);
					data.add(item);
				}
			}
			
			for (File file : directory.listFiles()) {
				if (!file.isHidden() && file.isFile()) {
					IGoSyncDocsLocalViewTableItem item = new IGoSyncDocsLocalViewTableItem();
					item.setName(file.getName());
					item.setActionCommand(file.getAbsolutePath());
					item.setIcon(getIconOfFile(file));
					item.setDirectory(file.isDirectory());
					item.setFile(file.isFile());
					item.setSize(String.valueOf(file.getFreeSpace()));
					String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(file.lastModified()));
					item.setLastModified(time);
					data.add(item);
				}
			}		
			fireTableChanged(null);	
		}
		columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("Last Modified");		
	}

	private Icon getIconOfFile(File file) {
		Icon icon = null;
		icon = FileSystemView.getFileSystemView().getSystemIcon(file);
		if (icon instanceof ImageIcon) {
			ImageIcon image = (ImageIcon) icon;
			Image temp = image.getImage().getScaledInstance(18, 18,Image.SCALE_SMOOTH);
			icon = new ImageIcon(temp);
		}
		return icon;
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

	public Vector<IGoSyncDocsLocalViewTableItem> getData() {
		return data;
	}

	public void setData(Vector<IGoSyncDocsLocalViewTableItem> data) {
		this.data = data;
	}

}
