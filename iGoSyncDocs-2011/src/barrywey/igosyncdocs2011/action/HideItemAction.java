/**
 * 
 * @(#)HideItemAction.java Feb 17, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

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
 * @version 1.0, Feb 17, 2011
 * @since JDK1.6
 */
public class HideItemAction implements Runnable {

	private MainFrame frMain;
	private ConfirmActionDialog dialog;

	public HideItemAction(ConfirmActionDialog dialog,
			MainFrame frMain) {
		this.frMain = frMain;
		this.dialog = dialog;
	}

	public void run() {
		try {
			dialog.setVisible(false);
			frMain.getProgressBar().setIndeterminate(true);
			for(DocumentListEntry entry : SystemRuntime.SelectedItem) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("main.message.hide_running").replace("{1}", entry.getTitle().getPlainText()));
				IGoSyncDocsBiz.hideItem(entry);
			}
			IGoSyncDocsBiz.cacheAllItem();
		}catch (IGoSyncDocsException e) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.error").replace("{1}",e.getMessage()));
		} catch (Exception e) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.error").replace("{1}",e.getMessage()));
		} finally {
			frMain.getProcessMessageLabel().setText("");
			frMain.getProgressBar().setIndeterminate(false);
			dialog.dispose();			
			frMain.refreshAllTableData();
		}
	}

}