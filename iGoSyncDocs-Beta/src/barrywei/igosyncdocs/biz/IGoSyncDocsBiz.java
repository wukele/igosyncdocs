/**
 * @(#)IGoSyncDocsBiz.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.biz;

import java.io.File;
import java.util.List;

import barrywei.igosyncdocs.biz.impl.SyncDocsException;

import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.docs.DocumentListEntry;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public interface IGoSyncDocsBiz {

	public void login(String username, String password)throws SyncDocsException;

	public List<DocumentListEntry> getAllItems() throws SyncDocsException;

	public List<DocumentListEntry> getAllFolders() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllDocuments() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllSpreadsheets() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllPresentation() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllTrashedObject() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllStaredObject() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllHiddenObject() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllDraftedObject() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllOtherFiles() throws SyncDocsException;
	
	public List<DocumentListEntry> getAllDocumentsInFolder(String folderName) throws SyncDocsException;
	
	public void cacheAllItems() throws SyncDocsException;

	public void downloadAll() throws SyncDocsException;
	
	public boolean uploadFiles(File[] files) throws SyncDocsException;
	
	public boolean createNew(String title,int type) throws SyncDocsException;
	
	public boolean star(DocumentListEntry entry) throws SyncDocsException;
	
	public boolean rename(DocumentListEntry entry,String newName) throws SyncDocsException;
	
	public boolean hide(DocumentListEntry entry) throws SyncDocsException;
	
	public boolean trash(DocumentListEntry entry) throws SyncDocsException;
	
	public boolean delete(DocumentListEntry entry) throws SyncDocsException;
	
	public void downloadDocument(DocumentListEntry entry ,String filePath,String format) throws SyncDocsException;
	
	public void downloadPresentation(DocumentListEntry entry ,String filePath,String format) throws SyncDocsException;
	
	public void downloadSpreadsheet(DocumentListEntry entry ,String filePath,String format) throws SyncDocsException;
	
	public void downloadFile(DocumentListEntry entry,String filePath) throws SyncDocsException;
	
	public List<DocumentListEntry> getDocumentsInFolder(String folderId) throws SyncDocsException;
	
	public AclFeed getAclFeed(DocumentListEntry entry) throws SyncDocsException;
	
	public boolean addAclEntry(DocumentListEntry entry,AclEntry aclEntry) throws SyncDocsException;
	
	public boolean removeAclEntry(DocumentListEntry entry,AclEntry aclEntry) throws SyncDocsException;
	
	public List<DocumentListEntry> fullTextSearch(String text) throws SyncDocsException;

}
