/**
 * @(#)ProgressDialog.java Oct 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui.dialog;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import barrywei.igosyncdocsv2.action.AppExitAction;
import barrywei.igosyncdocsv2.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 16, 2010
 * @since JDK1.6
 */
public class SplashProgressDialog extends JDialog {
	
	public SplashProgressDialog() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle(LanguageResource.getStringValue("app.title"));
		
		panel = new JPanel();
		panel.setName("panel");
		setContentPane(panel);
		setSize(new Dimension(450,300));
		panel.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(6, 249, 430, 16);
		progressBar.setName("progressBar");
		panel.add(progressBar);
		
		lblMessage = new JLabel(LanguageResource.getStringValue("main.splash.init_message"));
		lblMessage.setBounds(6, 229, 430, 16);
		lblMessage.setName("lblMessage");
		panel.add(lblMessage);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(SplashProgressDialog.class.getResource("/barrywei/igosyncdocsv2/resource/image/splash-440-220.png")));
		lblLogo.setBounds(0, 0, 440, 220);
		lblLogo.setName("lblLogo");
		panel.add(lblLogo);
		
		addWindowListener(new AppExitAction());
	}
	
	public synchronized void setMessage(String message) {
		lblMessage.setText(message);
	}

	private static final long serialVersionUID = -4640664209398480427L;
	private JPanel panel;
	private JProgressBar progressBar;
	private JLabel lblMessage;
	private JLabel lblLogo;
}
