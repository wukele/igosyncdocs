/**
 * @(#)PreferencesDialog.java Jul 23, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 23, 2010
 * @since JDK1.6
 */
public class PreferencesDialog extends JDialog {

	private static final long serialVersionUID = 2560975811321112648L;
	private JPanel pnlMain = new JPanel();
	
	public PreferencesDialog(JFrame frame) {
		super(frame);
		init();
	}
	
	private void init() {
		
		
		setContentPane(pnlMain);
	}
}
