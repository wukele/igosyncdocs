/**
 * @(#)ShowShareDocsDialogAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import barrywei.igosyncdocs.gui.FaceRunner;
import barrywei.igosyncdocs.gui.IGoSyncDocsMain;
import barrywei.igosyncdocs.gui.ShareWithDomainDialog;
import barrywei.igosyncdocs.gui.ShareWithEmailDialog;
import barrywei.igosyncdocs.gui.ShareWithGroupsDialog;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class ShowShareDocsDialogAction implements ActionListener {

	private IGoSyncDocsMain frame = null;
	private int type = 1;

	public ShowShareDocsDialogAction(IGoSyncDocsMain frame, int type) {
		this.frame = frame;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(type == 1) {
			ShareWithEmailDialog dialog = new ShareWithEmailDialog(frame);
			dialog.setModal(true);
			FaceRunner.run(dialog, new Dimension(500, 380),
					"iGoSyncDocs Share document : "
							+ frame.getRemoteTableSelectedItem().getEntry()
									.getTitle().getPlainText(), true);			
		}
		
		if(type == 2) {
			ShareWithGroupsDialog dialog = new ShareWithGroupsDialog(frame);
			dialog.setModal(true);
			FaceRunner.run(dialog, new Dimension(500, 380),
					"iGoSyncDocs Share document : "
							+ frame.getRemoteTableSelectedItem().getEntry()
									.getTitle().getPlainText(), true);				
		}
		
		if(type == 3) {
			ShareWithDomainDialog dialog = new ShareWithDomainDialog(frame);
			dialog.setModal(true);
			FaceRunner.run(dialog, new Dimension(500, 380),
					"iGoSyncDocs Share document : "
							+ frame.getRemoteTableSelectedItem().getEntry()
									.getTitle().getPlainText(), true);				
		}
	}
}
