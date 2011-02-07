/**
 * 
 * @(#)RefreshItemAction.java Feb 8, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsException;
import barrywey.igosyncdocs2011.gui.MainFrame;
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
public class RefreshItemAction implements ActionListener , Runnable{

	private MainFrame frMain;
	private JProgressBar progressbar;
	private JLabel lblMessage;
	
	public RefreshItemAction(MainFrame frMain) {
		this.frMain = frMain;
	}

	public void actionPerformed(ActionEvent e) {
		progressbar = frMain.getProgressBar();
		lblMessage = frMain.getProcessMessageLabel();
		if(!progressbar.isIndeterminate())
			new Thread(this).start();
	}
	
	public void run() {
		progressbar.setIndeterminate(true);
		try {
			lblMessage.setText(LanguageResource.getStringValue("main.message.refresh_start"));
			IGoSyncDocsBiz.cacheAllItem();
			frMain.refreshAllTableData();
			lblMessage.setText(LanguageResource.getStringValue("main.message.refresh_end"));
			progressbar.setIndeterminate(false);			
		} catch (IGoSyncDocsException e) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.error").replace("{1}",e.getMessage()));
		} catch (Exception e) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.error").replace("{1}",e.getMessage()));
		}
	}
}
