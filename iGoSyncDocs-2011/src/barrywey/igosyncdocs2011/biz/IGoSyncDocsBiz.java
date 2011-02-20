/**
 * @(#)IGoSyncDocsBiz.java Oct 14, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.biz;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.client.GoogleService.AccountDeletedException;
import com.google.gdata.client.GoogleService.AccountDisabledException;
import com.google.gdata.client.GoogleService.CaptchaRequiredException;
import com.google.gdata.client.GoogleService.InvalidCredentialsException;
import com.google.gdata.client.GoogleService.NotVerifiedException;
import com.google.gdata.client.GoogleService.ServiceUnavailableException;
import com.google.gdata.client.GoogleService.SessionExpiredException;
import com.google.gdata.client.GoogleService.TermsNotAgreedException;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.net.IGoSyncDocsDao;
import barrywey.igosyncdocs2011.net.impl.IGoSyncDocsDaoImpl;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 14, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsBiz {

	public static String captcha_url = null;
	private static IGoSyncDocsDao dao = new IGoSyncDocsDaoImpl();
	
	/**
	 * Login with email & password
	 * @param email
	 * @param password
	 * @return 'success' if only login is successfully , or the message points out why login has failed.	
	 */
	public static String login(String email,String password) {
		String result = "success";
		if(dao != null) {
			try {
				dao.login(email, password);
			} catch (CaptchaRequiredException e) {
				return LanguageResource.getStringValue("app.login.validate_login_captcha");
			} catch (InvalidCredentialsException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_InvalidCredentials");
			} catch (AccountDisabledException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_AccountDisabled");
			} catch (AccountDeletedException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_AccountDeleted");
			} catch (NotVerifiedException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_NotVerified");
			} catch (ServiceUnavailableException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_ServiceUnavailable");
			} catch (SessionExpiredException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_SessionExpired");
			} catch (TermsNotAgreedException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_TermsNotAgreed");
			} catch (AuthenticationException e) {
				String message = LanguageResource.getStringValue("app.login.validate_login_AuthenticationException");
				result = message.replace("{1}", e.getMessage());
			}
		}//end of if
		return result;
	}//end of login
	
	/**
	 * Cache all items into memory in order to deal with it
	 * @throws IGoSyncDocsException
	 */
	public static void cacheAllItem() throws IGoSyncDocsException {
		try {
			SystemRuntime.CachedDocumentFeed = dao.getAllFeeds();
			for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
				SystemRuntime.ChachedEntryAclFeed.add(dao.getAclFeed(entry));
			}
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		}
	}//end of method
	
	public static List<DocumentListEntry> getAllItems() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(!entry.isHidden() && !entry.isTrashed())
				list.add(entry);
		}
		return list;
	}
	
	public static List<DocumentListEntry> getAllDocuments() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.getType().equals("document") && !entry.isHidden() && !entry.isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<DocumentListEntry> getAllPresentations() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.getType().equals("presentation") && !entry.isHidden() && !entry.isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<DocumentListEntry> getAllSpreadsheets() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.getType().equals("spreadsheet") && !entry.isHidden() && !entry.isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<DocumentListEntry> getAllOthers() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if( !entry.getType().equals("document") && !entry.getType().equals("presentation") && !entry.getType().equals("spreadsheet") && !entry.isHidden() && entry.isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<DocumentListEntry> getHiddenObjects() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.isHidden() && !entry.isTrashed())
				list.add(entry);
		}
		return list;
	}
	
	public static List<DocumentListEntry> getStaredObjects() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.isStarred() && !entry.isHidden())
				list.add(entry);
		}
		return list;
	}
	
	public static List<DocumentListEntry> getTrashedObjects() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.isTrashed() && !entry.isHidden())
				list.add(entry);
		}		
		return list;
	}
	
	public static List<DocumentListEntry> getSharedWithMeObjects() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		List<AclFeed> aclFeed = SystemRuntime.ChachedEntryAclFeed;
		List<DocumentListEntry> allEntries = SystemRuntime.CachedDocumentFeed.getEntries();
		for(int i=0;i<aclFeed.size();i++) {
			AclFeed feed = aclFeed.get(i);// sequence of document entry
			for(AclEntry entry : feed.getEntries()) {
				if(entry.getRole().getValue().equalsIgnoreCase("owner") && !entry.getScope().getValue().equals(SystemRuntime.Settings.UserName)) {
					list.add(allEntries.get(i));
				}
			}//end of for
		}//end of for
		return list;		
	}
	
	public static List<AclEntry> getAclEntry(DocumentListEntry entry) {
		List<DocumentListEntry> entires = SystemRuntime.CachedDocumentFeed.getEntries();
		int index = 0;
		for(index=0;index<entires.size();index++) {
			if(entires.get(index).getResourceId().equals(entry.getResourceId()))
				break;
		}
		return SystemRuntime.ChachedEntryAclFeed.get(index).getEntries();
	}//end of method
	
	public static String getOwner(DocumentListEntry entry) {
		String owner = "";
		List<AclEntry> enties = getAclEntry(entry);
		for (int i = 0; i < enties.size(); i++) {
			AclEntry acl = enties.get(i);
			if(acl.getRole().getValue().equals("owner")) {
				owner = acl.getScope().getValue();
			}
		}// end of for
		return owner;
	}// end of method
	
	public static void trashItem(DocumentListEntry entry) throws IGoSyncDocsException {
		try {
			dao.trash(entry);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		}
	}
	
	public static void hideItem(DocumentListEntry entry) throws IGoSyncDocsException{
		try {
			dao.hide(entry);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		}
	}
	
	public static void starItem(DocumentListEntry entry) throws IGoSyncDocsException {
		try {
			dao.star(entry);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		}		
	}
	
	public static void shareItem(String email,boolean canWrite,DocumentListEntry entry)  throws IGoSyncDocsException{
		try {
			AclRole role ;
			if(canWrite)
				role = new AclRole("writer");
			else
				role = new AclRole("reader");
			AclScope scope = new AclScope(AclScope.Type.USER,email.trim());
			dao.addAclEntry(role, scope, entry);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		}
	}
	
	public static void upload(File file)  throws IGoSyncDocsException {
		try {
			dao.upload(file.getName().substring(0,file.getName().lastIndexOf('.')), file.getPath());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		}		
	}
	
	public static void delete(DocumentListEntry entry) throws IGoSyncDocsException {
		try {
			dao.delEntry(entry);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		}		
	}
}
