/**
 * @(#)ShareWithGroupAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;

import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.biz.impl.SyncDocsException;
import barrywei.igosyncdocs.factory.AbstractFactory;
import barrywei.igosyncdocs.gui.FaceRunner;
import barrywei.igosyncdocs.gui.ShareWithGroupsDialog;
import barrywei.igosyncdocs.gui.SplashDialogProgress;
import barrywei.igosyncdocs.gui.model.IGoSyncDocsRemoteViewTableItem;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class ShareWithGroupAction implements ActionListener,Runnable {

	private ShareWithGroupsDialog dialog = null;
	private IGoSyncDocsBiz biz = null;
	private IGoSyncDocsRemoteViewTableItem item = null;
	private SplashDialogProgress frame = new SplashDialogProgress();

	public ShareWithGroupAction(ShareWithGroupsDialog dialog,
			IGoSyncDocsRemoteViewTableItem item) {
		try {
			this.dialog =  dialog;
			biz = AbstractFactory.createSyncDocsBizObject();
			this.item = item;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"IGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (biz != null) {
			new Thread(this).start();
		}
	}

	private void addAclEntry() {
		String emailAddresses = dialog.getUserEnterdAddress();
		String[] address = emailAddresses.split(",");
		try {
			for (String email : address) {
				AclRole role = null;
				if (dialog.isWriteableChecked())
					role = new AclRole("writer");
				else
					role = new AclRole("reader");
				AclScope scope = new AclScope(AclScope.Type.GROUP, email);
				frame.setMessageLabelText("Shareing document with your friend "+email);
				AclEntry aclEntry = new AclEntry();
				aclEntry.setRole(role);
				aclEntry.setScope(scope);
				biz.addAclEntry(item.getEntry(), aclEntry);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Faled to add AclEntry for Docs:"+ item.getEntry().getResourceId(),"IGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void run() {
		dialog.setVisible(false);
		FaceRunner.run(frame, new Dimension(450, 300),"iGoSyncDocs Logining...", true);
		frame.startProgress();
		addAclEntry();
		try {
			this.dialog.initNeededData();
		} catch (SyncDocsException e) {
			JOptionPane.showMessageDialog(null, "Server Response:" + e.getMessage(), "IGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
		}
		frame.setVisible(false);
		dialog.setVisible(true);
		frame.dispose();
	}
}
