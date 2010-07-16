/**
 * @(#)IGoSyncDocsDao.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public interface IGoSyncDocsDao {

	/**
	 * Set user credentials based on a username and password.
	 * 
	 * @param username
	 * @param password
	 * @throws AuthenticationException
	 */
	public void login(String username, String password)
			throws AuthenticationException;

	// /**
	// * Allow a user to login using an AuthSub token.
	// *
	// * @param tokenValue
	// * @throws AuthenticationException
	// */
	// public void loginWithAuthSubToken(String tokenValue)
	// throws AuthenticationException;

	/**
	 * Create a new item in the DocList
	 * 
	 * @param title
	 *            the title of document
	 * @param type
	 *            the type of document. One of
	 *            "spreadsheet","presentation","folder" or "document"
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListEntry createNew(String title, int type)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * Get a feed that contain documents
	 * 
	 * @param category
	 *            what types of documents to list: "all": lists all the doc
	 *            objects (documents, spreadsheets, presentations) "folders":
	 *            lists all doc objects including folders. "documents": lists
	 *            only documents. "spreadsheets": lists only spreadsheets.
	 *            "pdfs": lists only pdfs. "presentations": lists only
	 *            presentations. "starred": lists only starred objects.
	 *            "trashed": lists trashed objects.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getDocsListFeed(int category)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * get all
	 * @param category
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getAllDocsListFeed(int category)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListEntry getDocsListEntry(String resourceId)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * 
	 * @param folderResourceId
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getFolderDocsListFeed(String folderResourceId)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * 
	 * @param searchParamters
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed search(Map<String, String> searchParamters)
			throws IOException, MalformedURLException, ServiceException;

	/**
	 * 
	 * @param searchParamters
	 *            parameters to be used in searching criteria. accepted
	 *            parameters are: "q": Typical search query "alt": "author":
	 *            "updated-min": Lower bound on the last time a document'
	 *            content was changed. "updated-max": Upper bound on the last
	 *            time a document' content was changed. "edited-min": Lower
	 *            bound on the last time a document was edited by the current
	 *            user. This value corresponds to the app:edited value in the
	 *            Atom entry, which represents changes to the document's content
	 *            or metadata. "edited-max": Upper bound on the last time a
	 *            document was edited by the current user. This value
	 *            corresponds to the app:edited value in the Atom entry, which
	 *            represents changes to the document's content or metadata.
	 *            "title": Specifies the search terms for the title of a
	 *            document. This parameter used without title-exact will only
	 *            submit partial queries, not exact queries. "title-exact":
	 *            Specifies whether the title query should be taken as an exact
	 *            string. Meaningless without title. Possible values are true
	 *            and false. "opened-min": Bounds on the last time a document
	 *            was opened by the current user. Use the RFC 3339 timestamp
	 *            format. For example: 2005-08-09T10:57:00-08:00 "opened-max":
	 *            Bounds on the last time a document was opened by the current
	 *            user. Use the RFC 3339 timestamp format. For example:
	 *            2005-08-09T10:57:00-08:00 "owner": Searches for documents with
	 *            a specific owner. Use the email address of the owner.
	 *            "writer": Searches for documents which can be written to by
	 *            specific users. Use a single email address or a comma
	 *            separated list of email addresses. "reader": Searches for
	 *            documents which can be read by specific users. Use a single
	 *            email address or a comma separated list of email addresses.
	 *            "showfolders": Specifies whether the query should return
	 *            folders as well as documents. Possible values are true and
	 *            false.
	 * @param category
	 * @return
	 */
	public DocumentListFeed search(Map<String, String> searchParamters,
			int category) throws MalformedURLException, IOException,
			ServiceException;

	/**
	 * 
	 * @param filePaht
	 * @param title
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListEntry upload(String filePath, String title)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @param delete
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void trashObject(String resourceId, boolean delete)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @param folderResourceId
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void removeFromFolder(String resourceId, String folderResourceId)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * 
	 * @param url
	 * @param filePath
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void download(URL url, String filePath)
			throws MalformedURLException, IOException, ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @param filePath
	 * @param format
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void downloadSpreadsheet(String resourceId, String filePath,
			String format) throws MalformedURLException, IOException,
			ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @param filePath
	 * @param format
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void downloadDocument(String resourceId, String filePath,
			String format) throws MalformedURLException, IOException,
			ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @param filepath
	 * @param format
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	public void downloadPresentation(String resourceId, String filepath,
			String format) throws IOException, MalformedURLException,
			ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @param folderId
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	public DocumentListEntry moveObjectToFolder(String resourceId,
			String folderId) throws IOException, MalformedURLException,
			ServiceException;

	/**
	 * 
	 * @param resourceId
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	public AclFeed getAclFeed(String resourceId) throws IOException,
			MalformedURLException, ServiceException;

	/**
	 * 
	 * @param role
	 * @param scope
	 * @param resourceId
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	public AclEntry addAclRole(AclRole role, AclScope scope, String resourceId)
			throws IOException, MalformedURLException, ServiceException;

	/**
	 * 
	 * @param role
	 * @param scope
	 * @param resourceId
	 * @return
	 * @throws IOException
	 * @throws ServiceException
	 */
	public AclEntry changeAclRole(AclRole role, AclScope scope,
			String resourceId) throws IOException, ServiceException;

	/**
	 * 
	 * @param scope
	 * @param email
	 * @param resourceId
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	public void removeAclRole(String scope, String email, String resourceId)
			throws IOException, MalformedURLException, ServiceException;
	
	/**
	 * Update en entry
	 * @param entry
	 * @return
	 * @throws ServiceException
	 */
	public DocumentListEntry update(DocumentListEntry entry) throws IOException,ServiceException;
	

}
