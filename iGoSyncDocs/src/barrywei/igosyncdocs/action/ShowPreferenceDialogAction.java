/**
 * @(#)ShowPreferenceDialogAction.java Jul 23, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import barrywei.igosyncdocs.bean.IConstant;
import barrywei.igosyncdocs.gui.FaceRunner;
import barrywei.igosyncdocs.gui.IGoSyncDocsMain;
import barrywei.igosyncdocs.gui.PreferencesDialog;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 23, 2010
 * @since JDK1.6
 */
public class ShowPreferenceDialogAction implements ActionListener{
	
	private IGoSyncDocsMain frame;
	
	public ShowPreferenceDialogAction(IGoSyncDocsMain frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		PreferencesDialog dialog = new PreferencesDialog(frame);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setModal(true);
		FaceRunner.run(dialog,new Dimension(500,400),IConstant.App_Name+" "+IConstant.App_Version, true);
	}
}
