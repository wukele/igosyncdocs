/**
 * @(#)IGoSyncDocsCategoryComboboxModel.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui.model;

import javax.swing.DefaultComboBoxModel;

import barrywei.igosyncdocs.bean.ICategory;
import barrywei.igosyncdocs.bean.IGoImageManager;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsCategoryComboboxModel extends DefaultComboBoxModel {

	private static final long serialVersionUID = -7148454083236740746L;

	public IGoSyncDocsCategoryComboboxModel() {
		init();
	}
	
	private void init() {
			
		IGoSyncDocsCategoryComboboxItem all = new IGoSyncDocsCategoryComboboxItem();
		IGoSyncDocsCategoryComboboxItem documents = new IGoSyncDocsCategoryComboboxItem();
		IGoSyncDocsCategoryComboboxItem spreadsheet = new IGoSyncDocsCategoryComboboxItem();
		IGoSyncDocsCategoryComboboxItem presentations = new IGoSyncDocsCategoryComboboxItem();
		IGoSyncDocsCategoryComboboxItem stared = new IGoSyncDocsCategoryComboboxItem();
		IGoSyncDocsCategoryComboboxItem trashed = new IGoSyncDocsCategoryComboboxItem();
		IGoSyncDocsCategoryComboboxItem hidden = new IGoSyncDocsCategoryComboboxItem();
		IGoSyncDocsCategoryComboboxItem others = new IGoSyncDocsCategoryComboboxItem();
		//IGoSyncDocsCategoryComboboxItem draft = new IGoSyncDocsCategoryComboboxItem();
		
		all.setIcon(IGoImageManager.getInstance().getIcon("listicon/all.png"));
		all.setText("All Items");
		all.setType(ICategory.All);
		
		documents.setIcon(IGoImageManager.getInstance().getIcon("listicon/doc.png"));
		documents.setText("Document");
		documents.setType(ICategory.Documents);
		
		spreadsheet.setIcon(IGoImageManager.getInstance().getIcon("listicon/spreadsheet.png"));
		spreadsheet.setText("Spreadsheet");
		spreadsheet.setType(ICategory.SpreadSheets);
		
		presentations.setIcon(IGoImageManager.getInstance().getIcon("listicon/presentation.png"));
		presentations.setText("Presentation");
		presentations.setType(ICategory.Presentations);
		
		stared.setIcon(IGoImageManager.getInstance().getIcon("listicon/stared.png"));
		stared.setText("Stared");
		stared.setType(ICategory.Stared);
		
		trashed.setIcon(IGoImageManager.getInstance().getIcon("listicon/trashed.png"));
		trashed.setText("Trashed");
		trashed.setType(ICategory.Trashed);
		
		hidden.setIcon(IGoImageManager.getInstance().getIcon("listicon/hidden.png"));
		hidden.setText("Hidden");
		hidden.setType(ICategory.Hidden);
		
		others.setIcon(IGoImageManager.getInstance().getIcon("listicon/all.png"));
		others.setText("Other Files");
		others.setType(ICategory.OtherFiles);
		
		addElement(all);
		addElement(documents);
		addElement(spreadsheet);
		addElement(presentations);
		addElement(stared);
		addElement(trashed);
		addElement(hidden);
		addElement(others);
		
	}
}
