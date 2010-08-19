/**
 * @(#)ConfirmExitDialog.java Jul 21, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import barrywei.igosyncdocs.action.ExitOrTrayAction;
import barrywei.igosyncdocs.bean.IConstant;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 21, 2010
 * @since JDK1.6
 */
public class ConfirmExitDialog extends JDialog {

	private static final long serialVersionUID = -452279196802802161L;
	private JFrame frame;
	private JPanel pnlMain = new JPanel();
	private JLabel label = new JLabel(
			"<html><b><font size=5 >Do you mean ...</b></html>");
	private JRadioButton rboTray = new JRadioButton("Add System Tray");
	private JRadioButton rboExit = new JRadioButton("Exit iGoSyncDocs");
	private JCheckBox chkDoNotAskAgain = new JCheckBox("Remember my choice and Do NOT ask me again.");
	private JButton button = new JButton(" O k ");
	private ButtonGroup btnExitOrNot = new ButtonGroup();

	public ConfirmExitDialog(JFrame frame) {
		super(frame);
		this.frame = frame;
		init();
	}

	private void init() {

		btnExitOrNot.add(rboExit);
		btnExitOrNot.add(rboTray);
		rboTray.setSelected(true);
		chkDoNotAskAgain.setSelected(true);
		setTitle(IConstant.App_Name + " " + IConstant.App_Version);
		setContentPane(pnlMain);
		setResizable(false);
		pnlMain.setLayout(null);
		label.setBounds(20, 10, 171, 39);
		setIconImage(Toolkit.getDefaultToolkit().getImage(IGoSyncDocsMain.class.getResource("/ch/randelshofer/quaqua/images/FileView.computerIcon.png")));
		pnlMain.add(label);
		rboTray.setBounds(32, 62, 181, 23);
		
		pnlMain.add(rboTray);
		rboExit.setBounds(32, 87, 274, 23);
		
		pnlMain.add(rboExit);
		chkDoNotAskAgain.setBounds(30, 112, 349, 23);
		
		pnlMain.add(chkDoNotAskAgain);
		button.addActionListener(new ExitOrTrayAction(frame, this));
		button.setBounds(314, 150, 90, 30);
		
		pnlMain.add(button);
	}
	
	public boolean isTrayAction() {
		return this.rboTray.isSelected();
	}
	
	public boolean isNeverAskAgain() {
		return this.chkDoNotAskAgain.isSelected();
	}
}
