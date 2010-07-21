/**
 * @(#)IGoSyncDocs.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicFileChooserUI;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;

import barrywei.igosyncdocs.bean.IConstant;
import barrywei.igosyncdocs.gui.FaceRunner;
import barrywei.igosyncdocs.gui.LoginFrame;
import ch.randelshofer.quaqua.QuaquaLookAndFeel;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class IGoSyncDocs {

	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(new QuaquaLookAndFeel());
			UIManager.put("FileChooserUI ", BasicFileChooserUI.class.getName());
			UIManager.put("MenuBarUI", BasicMenuBarUI.class.getName());
			UIManager.put("MenuItemUI", BasicMenuItemUI.class.getName());
			UIManager.put("MenuUI", BasicMenuUI.class.getName());

			//UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
			LoginFrame login = new LoginFrame();
			FaceRunner.run(login, new Dimension(350, 280), IConstant.App_Name+" "+IConstant.App_Version, true);
			login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Server Response:\n"
					+ e.getMessage(), "iGoSyncDocs Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
