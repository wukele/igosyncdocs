/**
 * @(#)IGoSyncDocsFolderTreeModel.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.gdata.data.docs.DocumentListEntry;

import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.biz.impl.SyncDocsException;
import barrywei.igosyncdocs.factory.AbstractFactory;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsFolderTreeModel {

	private static DefaultMutableTreeNode root = null;
	
	
	public static DefaultMutableTreeNode getRoot(){
	
		try {
			IGoSyncDocsBiz biz = AbstractFactory.createSyncDocsBizObject();
			List<DocumentListEntry> list = biz.getAllFolders();
			if(list!=null && list.size()>0) {
				root = new DefaultMutableTreeNode("My Folder");
				for(int i=0;i<list.size();i++) {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode();
					IGoSyncDocsFolderTreeItem item = new IGoSyncDocsFolderTreeItem();
					item.setEntry(list.get(i));
					item.setText(list.get(i).getTitle().getPlainText());
					node.setUserObject(item);
					root.add(node);
				}
			}//end of if
		} catch (Exception e) {
			new SyncDocsException(e.getMessage());
		}
		return root;
	}
}
