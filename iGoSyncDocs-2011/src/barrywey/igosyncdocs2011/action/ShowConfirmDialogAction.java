/**
 * 
 * @(#)TrashItemAction.java Feb 8, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.dialog.ConfirmActionDialog;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 8, 2011
 * @since JDK1.6
 */
public class ShowConfirmDialogAction implements ActionListener {
	
	private MainFrame frMain;

	public ShowConfirmDialogAction(MainFrame frMain) {
		this.frMain = frMain;
	}

	public void actionPerformed(ActionEvent e) {
		//get selected document from panel
		if(SystemRuntime.SelectedItem.size() == 0)
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.no_item_selected"));
		else {
			if(!frMain.getProgressBar().isIndeterminate()) {
				//prompt to confirm action
				ConfirmActionDialog dialog = new ConfirmActionDialog(LanguageResource.getStringValue("main.message.confirm_trash_actin"), "trash",frMain);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);			
			}else
				FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.another_process_running"));
		}
	}//end of actionPerformed
}
