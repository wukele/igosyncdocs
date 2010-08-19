/**
 * @(#)IGoSyncDocsBizImpl.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.biz.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.gdata.data.MediaContent;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import barrywei.igosyncdocs.bean.ICategory;
import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.dao.IGoSyncDocsDao;
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
public class IGoSyncDocsBizImpl implements IGoSyncDocsBiz {

	private static IGoSyncDocsDao dao;
	private static List<DocumentListEntry> allItems;
	private static List<DocumentListEntry> allFolders;

	public IGoSyncDocsBizImpl() throws SyncDocsException {
		try {
			dao = AbstractFactory.createSyncDocsDaoObject();
		} catch (InstantiationException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			throw new SyncDocsException(e.getMessage());
		}
	}

	public synchronized void cacheAllItems() throws SyncDocsException {
		if (dao != null) {
			try {
				DocumentListFeed feeds = dao.getDocsListFeed(ICategory.All);
				allItems = feeds.getEntries();
				DocumentListFeed folderFeeds = dao.getDocsListFeed(ICategory.Folders);
				allFolders = folderFeeds.getEntries();
			} catch (MalformedURLException e) {
				throw new SyncDocsException(e.getMessage());
			} catch (IOException e) {
				throw new SyncDocsException(e.getMessage());
			} catch (ServiceException e) {
				throw new SyncDocsException(e.getMessage());
			}
		}// end of if
	}

	public void login(String username, String password) throws SyncDocsException {
		try {
			dao.login(username, password);
		} catch (AuthenticationException e) {
			throw new SyncDocsException("Login failed.\n" + e.getMessage());
		}
	}

	@Override
	public void downloadAll() throws SyncDocsException {

	}

	@Override
	public List<DocumentListEntry> getAllDocuments() throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (entry.getType().equals("document") && !entry.isHidden() && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllFolders() throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allFolders != null && allFolders.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allFolders) {
				list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllHiddenObject()
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (entry.isHidden()  && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllItems() throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (!entry.isHidden()  && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllPresentation()
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (entry.getType().equals("presentation") && !entry.isHidden()  && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllSpreadsheets()
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (entry.getType().equals("spreadsheet") && !entry.isHidden()  && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllStaredObject()
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (entry.isStarred() && !entry.isHidden()  && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllTrashedObject()
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (entry.isTrashed() && !entry.isHidden())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllDraftedObject()
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (entry.isDraft() && !entry.isHidden()  && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}
	
	@Override
	public List<DocumentListEntry> getAllDocumentsInFolder(String folderName)
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		return list;
	}

	@Override
	public List<DocumentListEntry> getAllOtherFiles() throws SyncDocsException {
		List<DocumentListEntry> list = null;
		if (allItems != null && allItems.size() > 0) {
			list = new Vector<DocumentListEntry>();
			for (DocumentListEntry entry : allItems) {
				if (!isGoolgeFormat(entry) && !entry.isHidden() && !entry.isTrashed())
					list.add(entry);
			}// end of for
		}// end of if
		return list;
	}

	private boolean isGoolgeFormat(DocumentListEntry entry) {
		boolean result = false;
		if (entry.getType().equals("document")
				|| entry.getType().equals("presentation")
				|| entry.getType().equals("spreadsheet")) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean createNew(String title, int type) throws SyncDocsException {
		boolean result = false;
		try {
			dao.createNew(title, type);
			result = true;
		} catch (MalformedURLException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			throw new SyncDocsException(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean uploadFiles(File[] files) throws SyncDocsException {
		boolean result = false;
		try {
			for(File file : files) {
				dao.upload(file.getPath(), file.getName().substring(0,file.getName().lastIndexOf('.')));	
			}
			result = true;
		} catch (MalformedURLException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			throw new SyncDocsException(e.getMessage());
		} catch(Exception e) {
			throw new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean rename(DocumentListEntry entry, String newName)
			throws SyncDocsException {
		boolean result = false;
		entry.setTitle(new PlainTextConstruct(newName));
		try {
			DocumentListEntry  newEntry = entry.update();
			if(newEntry!=null)
				result = true;			
		} catch (IOException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			throw new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean star(DocumentListEntry entry) throws SyncDocsException {
		boolean result = false;
		if(entry.isStarred())
			entry.setStarred(false);
		else
			entry.setStarred(true);
		try {
			DocumentListEntry newEntry = dao.update(entry);
			if(newEntry!=null)
				result = true;
		} catch (IOException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			throw new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean hide(DocumentListEntry entry) throws SyncDocsException {
		boolean result = false;
		if(entry.isHidden())
			entry.setHidden(false);
		else
			entry.setHidden(true);
		try {
			DocumentListEntry newEntry = dao.update(entry);
			if(newEntry!=null)
				result = true;
		} catch (IOException e) {
			throw new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			throw new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean trash(DocumentListEntry entry) throws SyncDocsException  {
		boolean result = false;
		try {
			dao.trashObject(entry.getResourceId(), false);
			result = true;
		} catch (MalformedURLException e) {
			new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean delete(DocumentListEntry entry) throws SyncDocsException {
		boolean result = false;
		try {
			dao.trashObject(entry.getResourceId(), true);
			result = true;
		} catch (MalformedURLException e) {
			new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public void downloadDocument(DocumentListEntry entry,String filePath,String format)
			throws SyncDocsException {
		try {
			dao.downloadDocument(entry.getResourceId(), filePath, (format==null || format.equals(""))?"doc":format);
		} catch (MalformedURLException e) {
			new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			new SyncDocsException(e.getMessage());
		}
	}
	
	@Override
	public void downloadPresentation(DocumentListEntry entry, String filePath,String format)
			throws SyncDocsException {
		try {
			dao.downloadPresentation(entry.getResourceId(), filePath,(format==null || format.equals(""))?"ppt":format);
		} catch (MalformedURLException e) {
			new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			new SyncDocsException(e.getMessage());
		}
	}
	
	@Override
	public void downloadSpreadsheet(DocumentListEntry entry, String filePath,String format)
			throws SyncDocsException {
		try {
			dao.downloadSpreadsheet(entry.getResourceId(), filePath, (format==null || format.equals(""))?"xls":format);
		} catch (MalformedURLException e) {
			new SyncDocsException(e.getMessage());
		} catch (IOException e) {
			new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {
			new SyncDocsException(e.getMessage());
		}
	}
	
	@Override
	public void downloadFile(DocumentListEntry entry, String filePath)
			throws SyncDocsException {
		MediaContent mc = (MediaContent)entry.getContent();
		URL url;
		try {
			url = new URL(mc.getUri());
			dao.download(url, filePath);
		} catch (MalformedURLException e) {new SyncDocsException(e.getMessage());
		} catch (IOException e) {new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {new SyncDocsException(e.getMessage());
		}
	}
	
	@Override
	public List<DocumentListEntry> getDocumentsInFolder(String folderId)
			throws SyncDocsException {
		List<DocumentListEntry> list = null;
		try {
			DocumentListFeed feeds = dao.getFolderDocsListFeed(folderId);
			if(feeds!=null) {
				list = new ArrayList<DocumentListEntry>();
				for (DocumentListEntry entry : feeds.getEntries()) {
					list.add(entry);
				}
			}//end of if
		} catch (MalformedURLException e) {new SyncDocsException(e.getMessage());
		} catch (IOException e) {new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {new SyncDocsException(e.getMessage());
		}
		return list;
	}
	
	@Override
	public AclFeed getAclFeed(DocumentListEntry entry) throws SyncDocsException {
		AclFeed feed = null;
		try {
			feed = dao.getAclFeed(entry.getResourceId());
		} catch (MalformedURLException e) {new SyncDocsException(e.getMessage());
		} catch (IOException e) {new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {new SyncDocsException(e.getMessage());
		}
		return feed;
	}
	
	@Override
	public boolean addAclEntry(DocumentListEntry entry, AclEntry aclEntry)
			throws SyncDocsException {
		boolean result = false;
		try {
			AclEntry updatedEntry = dao.addAclRole(aclEntry.getRole(),aclEntry.getScope(),entry.getResourceId());
			if(updatedEntry!=null)
				result = true;
		} catch (MalformedURLException e) {new SyncDocsException(e.getMessage());
		} catch (IOException e) {new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean removeAclEntry(DocumentListEntry entry, AclEntry aclEntry)
			throws SyncDocsException {
		boolean result = false;
		try {
			dao.removeAclRole(aclEntry.getScope().getValue(),aclEntry.getScope().getValue(), entry.getResourceId());
			result = true;
		} catch (IOException e) {new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {new SyncDocsException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<DocumentListEntry> fullTextSearch(String text)
			throws SyncDocsException {
		List<DocumentListEntry> list = null;;
		Map<String,String> map = new HashMap<String, String>();
		map.put("q", text);
		try {
			DocumentListFeed feeds = dao.search(map);
			if(feeds!=null) {
				list = feeds.getEntries();
			}
		} catch (MalformedURLException e) {new SyncDocsException(e.getMessage());
		} catch (IOException e) {new SyncDocsException(e.getMessage());
		} catch (ServiceException e) {new SyncDocsException(e.getMessage());
		}
		return list;
	}
}
