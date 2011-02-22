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
import barrywey.igosyncdocs2011.gui.dialog.ShareItemDialog;
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
	private String action;

	public ShowConfirmDialogAction(MainFrame frMain,String action) {
		this.frMain = frMain;
		this.action = action;
	}

	public void actionPerformed(ActionEvent e) {
		//get selected document from panel
		if(SystemRuntime.SelectedItem.size() == 0)
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.no_item_selected"));
		else {
			if(!frMain.getProgressBar().isIndeterminate()) {
				//prompt to confirm action
				if(action.trim().equals("trash")) {
					ConfirmActionDialog dialog = new ConfirmActionDialog(LanguageResource.getStringValue("main.message.confirm_trash_actin"), "trash",frMain);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);			
				}else if(action.trim().equals("hide")) {
					ConfirmActionDialog dialog = new ConfirmActionDialog(LanguageResource.getStringValue("main.message.confirm_hide_action"), "hide",frMain);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);					
				} else if(action.trim().equals("star")) {
					ConfirmActionDialog dialog = new ConfirmActionDialog(LanguageResource.getStringValue("main.message.confirm_star_action"), "star",frMain);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);						
				} else if(action.trim().equals("share")) {
					ShareItemDialog dialog = new ShareItemDialog(frMain);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} else if(action.trim().equals("delete")) {
					ConfirmActionDialog dialog = new ConfirmActionDialog(LanguageResource.getStringValue("main.message.confirm_del_action"), "delete",frMain);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);					
				} else if(action.trim().equals("download")) {
					ConfirmActionDialog dialog = new ConfirmActionDialog(LanguageResource.getStringValue("dialog.download.confirm_download"), "download",frMain);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);					
				}
			}else
				FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.another_process_running"));
		}
	}//end of actionPerformed
}
