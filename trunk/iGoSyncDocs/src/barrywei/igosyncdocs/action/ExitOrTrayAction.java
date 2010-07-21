/**
 * @(#)ExitOrTrayAction.java Jul 21, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import barrywei.igosyncdocs.bean.ConfigManager;
import barrywei.igosyncdocs.gui.ConfirmExitDialog;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 21, 2010
 * @since JDK1.6
 */
public class ExitOrTrayAction implements ActionListener {

	private JFrame frame;
	private ConfirmExitDialog dialog;

	public ExitOrTrayAction(JFrame frame, ConfirmExitDialog dialog) {
		this.frame = frame;
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			if (dialog.isNeverAskAgain()) {
				ConfigManager.setNeverConfirmForExit("yes");
			}else {
				ConfigManager.setNeverConfirmForExit("no");
			}

			if (dialog.isTrayAction()) {
				ConfigManager.setDefaultExitAction("tray");
				dialog.dispose();
				frame.getWindowListeners()[0].windowIconified(null);
			} else {
				ConfigManager.setDefaultExitAction("exit");
				dialog.dispose();
				frame.dispose();
				System.exit(1);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
