/**
 * @(#)SplashDialogProgress.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import barrywei.igosyncdocs.bean.IGoImageManager;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class SplashDialogProgress extends JDialog {

	private static final long serialVersionUID = 4847428822157997956L;
	private JPanel pnlMain = new JPanel();
	private JPanel pnlSouth = new JPanel();
	private JLabel lblLogo = new JLabel(IGoImageManager.getInstance().getIcon(
	"splath-logo-450-300.jpg"));
	private JProgressBar progressbar  = new JProgressBar();
	private JLabel lblMessage = new JLabel("Shareing docs with your friends...");

	public SplashDialogProgress( ) {
		pnlMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		pnlMain.setLayout(new BorderLayout());
		pnlMain.add(lblLogo,BorderLayout.CENTER);
		pnlMain.add(pnlSouth,BorderLayout.SOUTH);
		
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.add(lblMessage,BorderLayout.CENTER);
		pnlSouth.add(progressbar,BorderLayout.SOUTH);
		
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(450,300));
		setResizable(false);
		setContentPane(pnlMain);
	}
	
	public synchronized void setMessageLabelText(String text) {
		lblMessage.setText(text);
	}
	
	public synchronized void startProgress() {
		progressbar.setIndeterminate(true);
	}
	
	public synchronized void endProgress() {
		progressbar.setIndeterminate(false);
		
	}
}
