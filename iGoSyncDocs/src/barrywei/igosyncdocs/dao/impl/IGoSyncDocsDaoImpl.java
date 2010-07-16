/**
 * @(#)IGoSyncDocsDaoImpl.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gdata.client.GoogleService;
import com.google.gdata.client.Query;
import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.MediaContent;
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
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import barrywei.igosyncdocs.bean.ICategory;
import barrywei.igosyncdocs.dao.IGoSyncDocsDao;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsDaoImpl implements IGoSyncDocsDao {

	private DocsService service;
	private GoogleService spreadsheetsService;
	public UserToken token;

	public static IGoSyncDocsDaoImpl instance;

	public IGoSyncDocsDaoImpl() {
		service = new DocsService(ServiceUrlBuilder.AppName);
		spreadsheetsService = new GoogleService("wise",ServiceUrlBuilder.AppName);// for download
	}

	@Override
	public AclEntry addAclRole(AclRole role, AclScope scope, String resourceId)
			throws IOException, MalformedURLException, ServiceException {
		AclEntry entry = new AclEntry();
		entry.setRole(role);
		entry.setScope(scope);
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + resourceId
				+ ServiceUrlBuilder.URL_ACL);

		return service.insert(url, entry);
	}

	@Override
	public AclEntry changeAclRole(AclRole role, AclScope scope,
			String resourceId) throws IOException, ServiceException {
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + resourceId
				+ ServiceUrlBuilder.URL_ACL);
		return service.update(url, scope, role);
	}
	

	@Override
	public DocumentListEntry createNew(String title, int type)
			throws MalformedURLException, IOException, ServiceException {

		DocumentListEntry newEntry = null;
		if (type == ICategory.Documents) {
			newEntry = new DocumentEntry();
		} else if (type == ICategory.Presentations) {
			newEntry = new PresentationEntry();
		} else if (type == ICategory.SpreadSheets) {
			newEntry = new SpreadsheetEntry();
		} else if (type == ICategory.Folders) {
			newEntry = new FolderEntry();
		}

		newEntry.setTitle(new PlainTextConstruct(title));
		return service.insert(ServiceUrlBuilder
				.buildUrl(ServiceUrlBuilder.URL_DEFAULT
						+ ServiceUrlBuilder.URL_DOCLIST_FEED), newEntry);
	}

	@Override
	public void download(URL url, String filePath)
			throws MalformedURLException, IOException, ServiceException {

		MediaContent mc = new MediaContent();
		mc.setUri(url.toString());
		MediaSource ms = service.getMedia(mc);

		InputStream inStream = null;
		FileOutputStream outStream = null;

		try {
			inStream = ms.getInputStream();
			outStream = new FileOutputStream(filePath);

			int c;
			while ((c = inStream.read()) != -1) {
				outStream.write(c);
			}
		} finally {
			if (inStream != null) {
				inStream.close();
			}
			if (outStream != null) {
				outStream.flush();
				outStream.close();
			}
		}

	}

	@Override
	public void downloadDocument(String resourceId, String filePath,
			String format) throws MalformedURLException, IOException,
			ServiceException {
		String[] parameters = { "docID=" + resourceId, "exportFormat=" + format };
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DOWNLOAD
				+ "/documents" + ServiceUrlBuilder.URL_CATEGORY_EXPORT,
				parameters);

		download(url, filePath);
	}

	@Override
	public void downloadPresentation(String resourceId, String filepath,
			String format) throws IOException, MalformedURLException,
			ServiceException {

		String[] parameters = { "docID=" + resourceId, "exportFormat=" + format };
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DOWNLOAD
				+ "/presentations" + ServiceUrlBuilder.URL_CATEGORY_EXPORT,
				parameters);

		download(url, filepath);
	}

	@Override
	public void downloadSpreadsheet(String resourceId, String filePath,
			String format) throws MalformedURLException, IOException,
			ServiceException {
		UserToken docsToken = (UserToken) service.getAuthTokenFactory()
				.getAuthToken();
		UserToken spreadsheetsToken = (UserToken) spreadsheetsService
				.getAuthTokenFactory().getAuthToken();
		service.setUserToken(spreadsheetsToken.getValue());

		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("key", resourceId
				.substring(resourceId.lastIndexOf(':') + 1));
		parameters.put("exportFormat", format);

		// // If exporting to .csv or .tsv, add the gid parameter to specify
		// which
		// // sheet to export
		// if (format.equals(Config.DOWNLOAD_SPREADSHEET_FORMATS.get("csv"))
		// || format.equals(Config.DOWNLOAD_SPREADSHEET_FORMATS.get("tsv"))) {
		// parameters.put("gid", "0"); // download only the first sheet
		// }

		URL url = ServiceUrlBuilder.buildUrl(
				ServiceUrlBuilder.SPREADSHEETS_HOST,
				ServiceUrlBuilder.URL_DOWNLOAD + "/spreadsheets"
						+ ServiceUrlBuilder.URL_CATEGORY_EXPORT, parameters);

		download(url, filePath);

		// Restore docs token for our DocList client
		service.setUserToken(docsToken.getValue());
	}

	@Override
	public AclFeed getAclFeed(String resourceId) throws IOException,
			MalformedURLException, ServiceException {
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + resourceId
				+ ServiceUrlBuilder.URL_ACL);
		return service.getFeed(url, AclFeed.class);
	}

	@Override
	public DocumentListEntry getDocsListEntry(String resourceId)
			throws MalformedURLException, IOException, ServiceException {
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + resourceId);
		return service.getEntry(url, DocumentListEntry.class);
	}

	@Override
	public DocumentListFeed getDocsListFeed(int category)
			throws MalformedURLException, IOException, ServiceException {
		URL url = null;
		switch (category) {
		case ICategory.All:
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED+"?showdeleted=true");
			break;
		case ICategory.Folders:
			String[] params = { ServiceUrlBuilder.PARAMETER_SHOW_FOLDERS };
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_FOLDER, params);

			break;
		case ICategory.Documents:
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_DOCUMENT);
			break;
		case ICategory.SpreadSheets:
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_SPREADSHEET);
			break;
		case ICategory.PDFs:
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_PDF);
			break;
		case ICategory.Presentations:
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_PRESENTATION);
			break;
		case ICategory.Stared:
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_STARRED);
			break;
		case ICategory.Trashed:
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_TRASHED);
			break;
		default:
			url = null;
			break;
		}
		service.setUserToken(token.getValue());
		return service.getFeed(url, DocumentListFeed.class);
	}

	@Override
	public DocumentListFeed getAllDocsListFeed(int category)
			throws MalformedURLException, IOException, ServiceException {
		
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED);

		DocumentListFeed allFeeds = new DocumentListFeed();
		DocumentListFeed tempFeed = service.getFeed(url, DocumentListFeed.class);

		do {
			allFeeds.getEntries().addAll(tempFeed.getEntries());
			URL tempURL = new URL(tempFeed.getNextLink().getHref());
			tempFeed = service.getFeed(tempURL,DocumentListFeed.class);
		} while (tempFeed.getEntries().size() > 0);

		return allFeeds;
	}

	@Override
	public DocumentListFeed getFolderDocsListFeed(String folderResourceId)
			throws MalformedURLException, IOException, ServiceException {
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + folderResourceId
				+ ServiceUrlBuilder.URL_FOLDERS);
		service.setUserToken(token.getValue());
		return service.getFeed(url, DocumentListFeed.class);
	}

	@Override
	public void login(String username, String password)
			throws AuthenticationException {
		service.setUserCredentials(username, password);
		spreadsheetsService.setUserCredentials(username, password);
		token = (UserToken) service.getAuthTokenFactory().getAuthToken();
	}

	@Override
	public DocumentListEntry moveObjectToFolder(String resourceId,
			String folderId) throws IOException, MalformedURLException,
			ServiceException {
		DocumentListEntry doc = new DocumentListEntry();
		doc.setId(ServiceUrlBuilder
				.buildUrl(
						ServiceUrlBuilder.URL_DEFAULT
								+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/"
								+ resourceId).toString());

		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + folderId
				+ ServiceUrlBuilder.URL_FOLDERS);
		return service.insert(url, doc);
	}

	@Override
	public void removeAclRole(String scope, String email, String resourceId)
			throws IOException, MalformedURLException, ServiceException {
		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + resourceId
				+ ServiceUrlBuilder.URL_ACL + "/" + scope + "%3A" + email);
		service.delete(url);
	}

	@Override
	public void removeFromFolder(String resourceId, String folderResourceId)
			throws MalformedURLException, IOException, ServiceException {

		URL url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + folderResourceId
				+ ServiceUrlBuilder.URL_FOLDERS + "/" + resourceId);
		service.delete(url, getDocsListEntry(resourceId).getEtag());

	}

	@Override
	public DocumentListFeed search(Map<String, String> searchParamters)
			throws IOException, MalformedURLException, ServiceException {
		return search(searchParamters, 0);
	}

	@Override
	public DocumentListFeed search(Map<String, String> searchParameters,
			int category) throws MalformedURLException, IOException,
			ServiceException {
		URL url = null;

		if (category == 0) {
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED);
		} else if (category == ICategory.Documents) {
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_DOCUMENT);
		} else if (category == ICategory.SpreadSheets) {
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_SPREADSHEET);
		} else if (category == ICategory.Presentations) {
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_PRESENTATION);
		} else if (category == ICategory.Stared) {
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_STARRED);
		} else if (category == ICategory.Trashed) {
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_TRASHED);
		} else if (category == ICategory.Folders) {
			url = ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT
					+ ServiceUrlBuilder.URL_DOCLIST_FEED
					+ ServiceUrlBuilder.URL_CATEGORY_FOLDER);
		}

		Query qry = new Query(url);

		for (String key : searchParameters.keySet()) {
			qry.setStringCustomParameter(key, searchParameters.get(key));
		}

		return service.query(qry, DocumentListFeed.class);
	}

	@Override
	public void trashObject(String resourceId, boolean delete)
			throws MalformedURLException, IOException, ServiceException {

		String feedUrl = ServiceUrlBuilder.URL_DEFAULT
				+ ServiceUrlBuilder.URL_DOCLIST_FEED + "/" + resourceId;
		
		if (delete) {
			feedUrl += "?delete=true";
		}
		
		service.delete(ServiceUrlBuilder.buildUrl(feedUrl),getDocsListEntry(resourceId).getEtag());
	}

	@Override
	public DocumentListEntry upload(String filePath, String title)
			throws MalformedURLException, IOException, ServiceException {
		File file = new File(filePath);
		String mimeType = DocumentListEntry.MediaType.fromFileName(file.getName()).getMimeType();

		DocumentEntry newDocument = new DocumentEntry();
		newDocument.setFile(file, mimeType);
		newDocument.setTitle(new PlainTextConstruct(title));

		return service.insert(ServiceUrlBuilder.buildUrl(ServiceUrlBuilder.URL_DEFAULT+ ServiceUrlBuilder.URL_DOCLIST_FEED), newDocument);
	}
	
	@Override
	public DocumentListEntry update(DocumentListEntry entry)
			throws IOException, ServiceException {
		return entry.update();
	}
}
