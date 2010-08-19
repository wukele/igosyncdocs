/**
 * @(#)PresentationFileFilter.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.filefilter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import barrywei.igosyncdocs.bean.UserConfig;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class PresentationFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		if (f.isDirectory())
			return true;
		String extension = getFileExtension(f);
		if(extension!=null) {
			for(int i=0;i<UserConfig.PresentationtFileFormat.length;i++) {
				if(extension.equals(UserConfig.PresentationtFileFormat[i]))
					return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Presentations(*.ppt;*.txt;*.pdf;*.png;*.swf)";
	}

	public String getFileExtension(File file) {
		String ext = null;
		String s = file.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
