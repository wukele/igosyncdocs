/**
 * @(#)IGoSyncDocsRemoteViewTableModel.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;

import com.google.gdata.data.docs.DocumentListEntry;

import barrywei.igosyncdocs.bean.ICategory;
import barrywei.igosyncdocs.bean.IConstant;
import barrywei.igosyncdocs.bean.IGoImageManager;
import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.biz.impl.SyncDocsException;
import barrywei.igosyncdocs.factory.AbstractFactory;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsRemoteViewTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6536065223274848612L;
	private Vector<IGoSyncDocsRemoteViewTableItem> data;
	private Vector<String> columnNames;
	private IGoSyncDocsBiz biz;

	public Vector<IGoSyncDocsRemoteViewTableItem> getData() {
		return data;
	}

	public void setData(Vector<IGoSyncDocsRemoteViewTableItem> data) {
		this.data = data;
	}
	
	public IGoSyncDocsRemoteViewTableModel(String folderName)  throws SyncDocsException{
		try {
			biz = AbstractFactory.createSyncDocsBizObject();
		} catch (InstantiationException e) {throw new SyncDocsException(e.getMessage());
		} catch (IllegalAccessException e) {throw new SyncDocsException(e.getMessage());
		} catch (ClassNotFoundException e) {throw new SyncDocsException(e.getMessage());
		} catch (IOException e) {throw new SyncDocsException(e.getMessage());
		}
		initDocumentsWithFolder(folderName);
	}
	
	public IGoSyncDocsRemoteViewTableModel(List<DocumentListEntry> list) {
		initTableWithList(list);
	}

	public IGoSyncDocsRemoteViewTableModel(int type) throws SyncDocsException {
		try {
			biz = AbstractFactory.createSyncDocsBizObject();
			switch (type) {
			case ICategory.All:
				initAllItems();
				break;
			case ICategory.Documents:
				initDocuments();
				break;
			case ICategory.Folders:
				break;
			case ICategory.Hidden:
				initAllHiddenObject();
				break;
			case ICategory.Presentations:
				initAllPresentations();
				break;
			case ICategory.SpreadSheets:
				initAllSpreadsheets();
				break;
			case ICategory.Stared:
				initAllStared();
				break;
			case ICategory.Trashed:
				initAllTrashed();
				break;
			case ICategory.Drafted:
				initAllDrafted();
				break;
			case ICategory.OtherFiles:
				initAllOtherFiles();
				break;
			}
		} catch (SyncDocsException e) {throw new SyncDocsException(e.getMessage());
		} catch (InstantiationException e) {throw new SyncDocsException(e.getMessage());
		} catch (IllegalAccessException e) {throw new SyncDocsException(e.getMessage());
		} catch (ClassNotFoundException e) {throw new SyncDocsException(e.getMessage());
		} catch (IOException e) {throw new SyncDocsException(e.getMessage());
		}
	}
	
	private void initDocumentsWithFolder(String folder) throws SyncDocsException{
		if(biz!=null) {
			try {
				List<DocumentListEntry> list = biz.getDocumentsInFolder(folder);
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}
		}
	}

	private void initAllOtherFiles() throws SyncDocsException {
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllOtherFiles();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private void initAllDrafted()  throws SyncDocsException{
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllDraftedObject();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private void initAllTrashed() throws SyncDocsException {
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllTrashedObject();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private void initAllStared() throws SyncDocsException {
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllStaredObject();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private void initAllSpreadsheets()  throws SyncDocsException{
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllSpreadsheets();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private void initAllPresentations()  throws SyncDocsException{
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllPresentation();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private synchronized void initTableWithList(List<DocumentListEntry> list) {
		data = new Vector<IGoSyncDocsRemoteViewTableItem>();
		columnNames = new Vector<String>();
		
		for (DocumentListEntry entry : list) {
			IGoSyncDocsRemoteViewTableItem item = new IGoSyncDocsRemoteViewTableItem();
			item.setEntry(entry);
			item.setStared(entry.isStarred());
			item.setHidden(entry.isHidden());
			item.setName(entry.getTitle().getPlainText());
			item.setType(entry.getType());
			item.setIcon(getValiedIcon(entry));
			item.setLastViewdByWhom(entry.getLastModifiedBy().getEmail());
			String time = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss").format(new Date(entry.getUpdated().getValue()));
			item.setLastViewdDate(time);
			data.add(item);
		}

		columnNames.add("S");
		columnNames.add("T");
		columnNames.add("Name");
		columnNames.add("Last Updated");
		//fireTableChanged(null);
		fireTableDataChanged();
	}

	private void initAllHiddenObject()  throws SyncDocsException{
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllHiddenObject();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private void initAllItems()  throws SyncDocsException{
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllItems();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private void initDocuments() throws SyncDocsException {
		if (biz != null) {
			try {
				List<DocumentListEntry> list = biz.getAllDocuments();
				initTableWithList(list);
			} catch (SyncDocsException e) {
				throw e;
			}// end of try-catch
		}// end of if
	}

	private Icon getValiedIcon(DocumentListEntry entry) {
		if (entry.getType().equals("document"))
			return IGoImageManager.getInstance().getIcon("listicon/doc.png");
		else if (entry.getType().equals("spreadsheet"))
			return IGoImageManager.getInstance().getIcon(
					"listicon/spreadsheet.png");
		else if (entry.getType().equals("presentation"))
			return IGoImageManager.getInstance().getIcon(
					"listicon/presentation.png");
		else {
			String fileExtensiton = entry.getType();
			String userDir = System.getProperty("user.home");
			File file = new File(userDir+"/"+IConstant.App_Name+"-"+IConstant.App_Version+"/iGoSyncDocs-Temp." + fileExtensiton);
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
			if (icon instanceof ImageIcon) {
				ImageIcon image = (ImageIcon) icon;
				Image temp = image.getImage().getScaledInstance(18, 18,
						Image.SCALE_SMOOTH);
				icon = new ImageIcon(temp);
			}
			return icon;
		}
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
		else
			return data.get(rowIndex);
	}

}
