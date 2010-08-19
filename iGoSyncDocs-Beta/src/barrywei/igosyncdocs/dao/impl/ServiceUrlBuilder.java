/**
 * @(#)ServiceUrlBuilder.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.dao.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class ServiceUrlBuilder {

	public static String MainFrameTitle = "iGoSyncDocs";
	public static String AppName = "BarryWei-iGoSyncDocs-Beta";

	public static final String DEFAULT_AUTH_PROTOCOL = "https";
	public static final String DEFAULT_AUTH_HOST = "docs.google.com";

	public static final String DEFAULT_PROTOCOL = "http";
	public static final String DEFAULT_HOST = "docs.google.com";

	public static final String SPREADSHEETS_SERVICE_NAME = "wise";
	public static final String SPREADSHEETS_HOST = "spreadsheets.google.com";

	public static final String URL_FEED = "/feeds";
	public static final String URL_DOWNLOAD = "/download";
	public static final String URL_DOCLIST_FEED = "/private/full";

	public static final String URL_DEFAULT = "/default";
	public static final String URL_FOLDERS = "/contents";
	public static final String URL_ACL = "/acl";
	public static final String URL_REVISIONS = "/revisions";

	public static final String URL_CATEGORY_DOCUMENT = "/-/document";
	public static final String URL_CATEGORY_SPREADSHEET = "/-/spreadsheet";
	public static final String URL_CATEGORY_PDF = "/-/pdf";
	public static final String URL_CATEGORY_PRESENTATION = "/-/presentation";
	public static final String URL_CATEGORY_STARRED = "/-/starred";
	public static final String URL_CATEGORY_TRASHED = "/-/trashed";
	public static final String URL_CATEGORY_FOLDER = "/-/folder";
	public static final String URL_CATEGORY_EXPORT = "/Export";

	public static final String PARAMETER_SHOW_FOLDERS = "showfolders=true";

	/**
	 * Builds a URL from a patch
	 * 
	 * @param path
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL buildUrl(String path) throws MalformedURLException {
		return buildUrl(path, null);
	}

	/**
	 * Builds a URL with parameters.
	 * 
	 * @param path
	 * @param parameters
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL buildUrl(String path, String[] parameters)
			throws MalformedURLException {
		return buildUrl(DEFAULT_AUTH_HOST, path, parameters);
	}

	/**
	 * Builds a URL with parameters.
	 * 
	 * @param domain
	 * @param path
	 * @param parameters
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL buildUrl(String domain, String path, String[] parameters)
			throws MalformedURLException {
		StringBuffer url = new StringBuffer();
		url.append(DEFAULT_PROTOCOL + "://" + domain + URL_FEED + path);

		if (parameters != null && parameters.length > 0) {
			url.append("?");
			for (int i = 0; i < parameters.length; i++) {
				url.append(parameters[i]);
				if (i != (parameters.length - 1)) {
					url.append("&");
				}
			}
		}
		return new URL(url.toString());
	}

	/**
	 * Builds a URL with parameters.
	 * 
	 * @param domain
	 * @param path
	 * @param parameters
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL buildUrl(String domain, String path,
			Map<String, String> parameters) throws MalformedURLException {
		StringBuffer url = new StringBuffer();
		url.append(DEFAULT_PROTOCOL + "://" + domain + URL_FEED + path);

		if (parameters != null && parameters.size() > 0) {
			Set<Map.Entry<String, String>> params = parameters.entrySet();
			Iterator<Map.Entry<String, String>> itr = params.iterator();

			url.append("?");
			while (itr.hasNext()) {
				Map.Entry<String, String> entry = itr.next();
				url.append(entry.getKey() + "=" + entry.getValue());
				if (itr.hasNext()) {
					url.append("&");
				}
			}
		}
		return new URL(url.toString());
	}

}
