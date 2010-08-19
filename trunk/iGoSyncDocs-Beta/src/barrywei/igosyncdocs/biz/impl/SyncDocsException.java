/**
 * @(#)SyncDocsException.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.biz.impl;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class SyncDocsException extends Exception {

	private static final long serialVersionUID = 193312911444027462L;

	public SyncDocsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SyncDocsException(String message) {
		super(message);
	}

}
