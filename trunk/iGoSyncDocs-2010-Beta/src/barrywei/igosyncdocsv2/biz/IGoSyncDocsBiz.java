/**
 * @(#)IGoSyncDocsBiz.java Oct 14, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.biz;

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
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import barrywei.igosyncdocsv2.bean.SystemRuntime;
import barrywei.igosyncdocsv2.net.IGoSyncDocsDao;
import barrywei.igosyncdocsv2.net.impl.IGoSyncDocsDaoImpl;
import barrywei.igosyncdocsv2.resource.LanguageResource;

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
	
	public static void cacheAllItem() throws IGoSyncDocsException {
		try {
			SystemRuntime.CachedDocumentFeed = dao.getAllFeeds();
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
	
	public static List<DocumentListEntry> getAllDocuments() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.getType().equals("document"))
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<DocumentListEntry> getAllPresentations() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.getType().equals("presentation"))
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<DocumentListEntry> getAllSpreadsheets() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if(entry.getType().equals("spreadsheet"))
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<DocumentListEntry> getAllOthers() {
		List<DocumentListEntry> list = new ArrayList<DocumentListEntry>();
		for(DocumentListEntry entry : SystemRuntime.CachedDocumentFeed.getEntries()) {
			if( !entry.getType().equals("document") && !entry.getType().equals("presentation") && !entry.getType().equals("spreadsheet"))
				list.add(entry);
		}
		return list;
	}//end of method
}
