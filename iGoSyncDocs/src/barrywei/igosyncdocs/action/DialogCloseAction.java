/**
 * @(#)DialogCloseAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class DialogCloseAction implements ActionListener{
	
	private JDialog dialog = null;
	private boolean isExitApp = false;

	public DialogCloseAction(JDialog dialog,boolean isExitApp) {
		this.dialog = dialog;
		this.isExitApp = isExitApp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		dialog.dispose();
		
		if(isExitApp) {
			System.exit(1);
		}
	}
}
