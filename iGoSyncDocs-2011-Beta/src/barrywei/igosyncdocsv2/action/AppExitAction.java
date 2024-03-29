/**
 * @(#)AppExitAction.java Oct 24, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import barrywei.igosyncdocsv2.gui.util.FaceUtils;
import barrywei.igosyncdocsv2.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 24, 2010
 * @since JDK1.6
 */
public class AppExitAction extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
		String message = LanguageResource.getStringValue("app.confirm_exit");
		int result = FaceUtils.showConfirmMessage(null, message);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(1);
		}
	}
}
