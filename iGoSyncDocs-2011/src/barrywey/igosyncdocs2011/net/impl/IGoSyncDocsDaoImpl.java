/**
 * @(#)IGoSyncDocsDaoImpl.java Oct 14, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.net.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import barrywey.igosyncdocs2011.net.IGoSyncDocsDao;


import com.google.gdata.client.DocumentQuery;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.GoogleService.AccountDeletedException;
import com.google.gdata.client.GoogleService.AccountDisabledException;
import com.google.gdata.client.GoogleService.CaptchaRequiredException;
import com.google.gdata.client.GoogleService.InvalidCredentialsException;
import com.google.gdata.client.GoogleService.NotVerifiedException;
import com.google.gdata.client.GoogleService.ServiceUnavailableException;
import com.google.gdata.client.GoogleService.SessionExpiredException;
import com.google.gdata.client.GoogleService.TermsNotAgreedException;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentEntry;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.FolderEntry;
import com.google.gdata.data.docs.PresentationEntry;
import com.google.gdata.data.docs.SpreadsheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 14, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsDaoImpl implements IGoSyncDocsDao{

	private DocsService service;
	private GoogleService spreadsheetsService;
	private static final String appName = "Barry Wey-iGoSyncDocs-2011";
	private UserToken token;

	public IGoSyncDocsDaoImpl() {
		service = new DocsService(appName);
		spreadsheetsService = new GoogleService("wise", appName);
	}

	public void login(String email, String password)
			throws CaptchaRequiredException, InvalidCredentialsException,
			AccountDisabledException, AccountDeletedException,
			NotVerifiedException, ServiceUnavailableException,
			SessionExpiredException, TermsNotAgreedException,
			AuthenticationException {
		service.setUserCredentials(email, password);
		spreadsheetsService.setUserCredentials(email, password);
		token = (UserToken) service.getAuthTokenFactory().getAuthToken();
	}
	
	public DocumentListFeed getAllFeeds() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Feeds), DocumentListFeed.class);
	}
	
	public DocumentListFeed getAllDocuments() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Documents), DocumentListFeed.class);
	}
	
	public DocumentListFeed getAllSpreadsheets () throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Spreadsheets), DocumentListFeed.class);
	}
	
	public DocumentListFeed getAllPresentations() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Presentations), DocumentListFeed.class);
	}
	
	public DocumentListFeed getAllTrashedObjects() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_TrashedObjects), DocumentListFeed.class);
	}
	
	public DocumentListFeed getAllFolders() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Folders), DocumentListFeed.class);		
	}
	
	public DocumentListFeed exactTitleQuery(String title) throws MalformedURLException, IOException, ServiceException {
		DocumentQuery query = new DocumentQuery(new URL(URLManager.Exact_Title_Search));
		query.setTitleQuery(title);
		query.setMaxResults(20);
		service.setUserToken(token.getValue());
		return service.getFeed(query, DocumentListFeed.class);		
	}
	
	public DocumentListFeed fullTextQuery(String text) throws MalformedURLException, IOException, ServiceException {
		DocumentQuery query = new DocumentQuery(new URL(URLManager.Exact_Title_Search));
		query.setFullTextQuery(text);
		service.setUserToken(token.getValue());
		return service.getFeed(query, DocumentListFeed.class);			
	}
	
	public DocumentListEntry createNew(String title, String type) throws MalformedURLException, IOException, ServiceException {
		DocumentListEntry entry = null;
		if(type.trim().equalsIgnoreCase("document")) {
			entry = new DocumentEntry();
		}else if(type.trim().equalsIgnoreCase("spreadsheet")) {
			entry = new SpreadsheetEntry();
		}else if(type.trim().equalsIgnoreCase("presentation")) {
			entry = new PresentationEntry();
		}
		entry.setTitle(new PlainTextConstruct(title));
		service.setUserToken(token.getValue());
		return service.insert(new URL(URLManager.Exact_Title_Search),entry);
	}
	
	public DocumentListEntry upload(String title, String filePath)
			throws MalformedURLException, IOException, ServiceException {
		File file = new File(filePath);
		String mimeType = DocumentListEntry.MediaType.fromFileName(file.getName()).getMimeType();
		DocumentListEntry entry = new DocumentListEntry();
		entry.setFile(file,mimeType);
		entry.setTitle(new PlainTextConstruct(title));
		service.setUserToken(token.getValue());
		return service.insert(new URL(URLManager.Exact_Title_Search),entry);
	}
	
	public DocumentListEntry createFolder(String folderName)
			throws MalformedURLException, IOException, ServiceException {
		DocumentListEntry folder = new FolderEntry();
		folder.setTitle(new PlainTextConstruct(folderName));
		service.setUserToken(token.getValue());
		return service.insert(new URL(URLManager.Exact_Title_Search),folder);		
	}
	
	public AclFeed getAclFeed(DocumentListEntry entry)
			throws MalformedURLException, IOException, ServiceException {
		return service.getFeed(new URL(entry.getAclFeedLink().getHref()),
				AclFeed.class);
	}

	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#trash(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void trash(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		entry.delete();
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#hide(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void hide(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		entry.setHidden(true);
		service.update(new URL(entry.getEditLink().getHref()), entry);
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#star(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void star(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		entry.setStarred(true);
		service.update(new URL(entry.getEditLink().getHref()), entry);
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#addAclEntry(com.google.gdata.data.acl.AclRole, com.google.gdata.data.acl.AclScope, com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void addAclEntry(AclRole role, AclScope scope,
			DocumentListEntry entry) throws MalformedURLException, IOException,
			ServiceException {
		AclEntry aclEntry = new AclEntry();
		aclEntry.setRole(role);
		aclEntry.setScope(scope);
		service.insert(new URL(entry.getAclFeedLink().getHref()),aclEntry);
	}

	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#delAclEntry(com.google.gdata.data.acl.AclEntry, com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void delAclEntry(AclEntry entry)
			throws MalformedURLException, IOException, ServiceException {
		entry.delete();
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#delEntry(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void delEntry(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		service.delete(new URL(entry.getEditLink().getHref()+"?delete=true"),entry.getEtag());
	}
}
