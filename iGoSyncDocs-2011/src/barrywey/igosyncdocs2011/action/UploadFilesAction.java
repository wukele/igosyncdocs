/**
 * 
 * @(#)UploadFilesAction.java Feb 17, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.dialog.UploadFileProcessDialog;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 17, 2011
 * @since JDK1.6
 */
public class UploadFilesAction implements ActionListener {

	private MainFrame frMain;
	
	public UploadFilesAction(MainFrame frMain) {
		this.frMain = frMain;
	}

	public void actionPerformed(ActionEvent e) {
		if(!frMain.getProgressBar().isIndeterminate()) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(true);
			int result = fileChooser.showDialog(null, LanguageResource.getStringValue("main.message.upload_label"));
			if(result == JFileChooser.APPROVE_OPTION) {
				SystemRuntime.UploadDialog = new UploadFileProcessDialog(fileChooser.getSelectedFiles(),frMain);
				SystemRuntime.UploadDialog.setLocationRelativeTo(null);
				SystemRuntime.UploadDialog.setVisible(true);
			}
		}else 
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.another_process_running"));
	}
}
