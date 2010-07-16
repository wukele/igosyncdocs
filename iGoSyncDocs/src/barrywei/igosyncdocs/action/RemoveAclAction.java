/**
 * @(#)RemoveAclAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.factory.AbstractFactory;
import barrywei.igosyncdocs.gui.ShareWithEmailDialog;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class RemoveAclAction implements ActionListener {
	
	private ShareWithEmailDialog dialog = null;
	private IGoSyncDocsBiz biz = null;
	
	public RemoveAclAction(ShareWithEmailDialog dialog) {
		try {
			this.biz = AbstractFactory.createSyncDocsBizObject();
			this.dialog = dialog;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"IGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure to remove it?","iGoSyncDocs",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.YES_OPTION) {
			try {
				biz.removeAclEntry(dialog.getSelectedEntry(), dialog.getSelectedAclEntry());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"IGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
			}			
		}
	}

	
}
