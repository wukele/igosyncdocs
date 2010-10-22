/**
 * @(#)Test.java Oct 14, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.net;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gdata.client.GoogleService.AccountDeletedException;
import com.google.gdata.client.GoogleService.AccountDisabledException;
import com.google.gdata.client.GoogleService.CaptchaRequiredException;
import com.google.gdata.client.GoogleService.InvalidCredentialsException;
import com.google.gdata.client.GoogleService.NotVerifiedException;
import com.google.gdata.client.GoogleService.ServiceUnavailableException;
import com.google.gdata.client.GoogleService.SessionExpiredException;
import com.google.gdata.client.GoogleService.TermsNotAgreedException;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import barrywei.igosyncdocsv2.net.impl.IGoSyncDocsDaoImpl;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 14, 2010
 * @since JDK1.6
 */
public class Test {

	public static void main(String[] args) {

		IGoSyncDocsDao dao = new IGoSyncDocsDaoImpl();

		System.getProperties().put("proxySet", "true");
		System.getProperties().put("https.proxyHost", "localhost");
		System.getProperties().put("https.proxyPort", "8580");

			try {
				dao.login("baratjan@gmail.com", "muhabat1614");
				//System.out.println("login okey\nstart to upload...");
				//dao.upload("hello world 2","d:\\hello\\hello.txt");
				System.out.println(dao.getAllFeeds().getEntries().size());
				System.out.println("it's done");				
			} catch (CaptchaRequiredException e) {
				System.out.println("CaptchaRequiredException");
			} catch (InvalidCredentialsException e) {
				System.out.println("InvalidCredentialsException");
			} catch (AccountDisabledException e) {
				System.out.println("AccountDisabledException");
			} catch (AccountDeletedException e) {
				System.out.println("AccountDeletedException");
			} catch (NotVerifiedException e) {
				System.out.println("NotVerifiedException");
			} catch (ServiceUnavailableException e) {
				System.out.println("ServiceUnavailableException");
			} catch (SessionExpiredException e) {
				System.out.println("SessionExpiredException");
			} catch (TermsNotAgreedException e) {
				System.out.println("TermsNotAgreedException");
			} catch (AuthenticationException e) {
				System.out.println("AuthenticationException");
			} catch (MalformedURLException e) {
				// XXX Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// XXX Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// XXX Auto-generated catch block
				e.printStackTrace();
			}
			
	

	}// end of main
}
