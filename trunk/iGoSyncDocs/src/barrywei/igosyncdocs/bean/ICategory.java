/**
 * @(#)ICategory.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.bean;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public interface ICategory {
	public static final int All = 1;
	public static final int Folders = 2;
	public static final int Documents = 3;
	public static final int SpreadSheets = 4;
	public static final int PDFs = 5;
	public static final int Presentations = 6;
	public static final int Stared = 7;
	public static final int Trashed = 8;
	public static final int Hidden = 9;
	public static final int Drafted = 10;
	public static final int OtherFiles = 11;
}
