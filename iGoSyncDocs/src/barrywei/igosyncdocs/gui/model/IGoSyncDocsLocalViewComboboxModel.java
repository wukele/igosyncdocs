/**
 * @(#)IGoSyncDocsLocalViewComboboxModel.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import java.awt.Image;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsLocalViewComboboxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = 7852080666071652157L;

	public IGoSyncDocsLocalViewComboboxModel() {
		initData();
	}

	private void initData() {
		File[] roots = File.listRoots();
		for (int i = 0; i < roots.length; i++) {
			IGoSyncDocsLocalViewComboboxItem item = new IGoSyncDocsLocalViewComboboxItem();
			item.setPath(roots[i].getAbsolutePath());
			item.setParentPath(roots[i].getParent());
			item.setText(roots[i].getPath());
			item.setIcon(getIconOfPath(roots[i].getAbsolutePath()));
			addElement(item);
		}
	}

	private Icon getIconOfPath(String path) {
		Icon icon = null;
		icon = FileSystemView.getFileSystemView().getSystemIcon(new File(path));
		if(icon instanceof ImageIcon) {
			ImageIcon image = (ImageIcon)icon;
			icon = new ImageIcon(image.getImage().getScaledInstance(18,18,Image.SCALE_SMOOTH));
		}
		return icon;
	}
}
