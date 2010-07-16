/**
 * @(#)ShowAboutDialogAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import barrywei.igosyncdocs.gui.AboutDialog;
import barrywei.igosyncdocs.gui.FaceRunner;
import barrywei.igosyncdocs.gui.IGoSyncDocsMain;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class ShowAboutDialogAction implements ActionListener {
	
	private IGoSyncDocsMain frame = null;

	public ShowAboutDialogAction(IGoSyncDocsMain frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AboutDialog dialog = new AboutDialog(frame);
		FaceRunner.run(dialog, new Dimension(471, 306), "iGoSyncDocs Help", true);
	}

}
