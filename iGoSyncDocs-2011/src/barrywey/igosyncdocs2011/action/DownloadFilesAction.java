/**
 * 
 * @(#)DownloadFilesAction.java Feb 22, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.io.File;

import com.google.gdata.data.docs.DocumentListEntry;

import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsException;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.dialog.ConfirmActionDialog;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Feb 22, 2011
 * @since JDK1.6
 */
public class DownloadFilesAction implements Runnable {

	private MainFrame frMain;
	private ConfirmActionDialog dialog;
	private String filePath;

	public DownloadFilesAction(ConfirmActionDialog dialog, MainFrame frMain, String filePath) {
		this.dialog = dialog;
		this.frMain = frMain;
		this.filePath = filePath;
	}

	public void run() {
		try {
			dialog.setVisible(false);
			frMain.getProgressBar().setIndeterminate(true);
			for(DocumentListEntry entry : SystemRuntime.SelectedItem) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("dialog.download.download_process").replace("{1}", entry.getTitle().getPlainText()));
				if(entry.getType().trim().equals("document")) {
					IGoSyncDocsBiz.downloadDocument(entry, filePath+ File.separator + entry.getTitle().getPlainText() + ".doc",null);
				}else if(entry.getType().trim().equals("spreadsheet")) {
					IGoSyncDocsBiz.downloadSpreadsheet(entry, filePath+ File.separator + entry.getTitle().getPlainText() + ".xls",null);
				}else if(entry.getType().trim().equals("presentation")) {
					IGoSyncDocsBiz.downloadPresentation(entry, filePath+ File.separator + entry.getTitle().getPlainText() + ".ppt",null);
				}else {
					IGoSyncDocsBiz.download(entry, filePath+ File.separator + entry.getTitle().getPlainText());
				}//end of if
			}//end of for
		}catch (IGoSyncDocsException e) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.error").replace("{1}",e.getMessage()));
		}finally {
			frMain.getProcessMessageLabel().setText("");
			frMain.getProgressBar().setIndeterminate(false);
			dialog.dispose();						
		}
	}// end of run()
}
