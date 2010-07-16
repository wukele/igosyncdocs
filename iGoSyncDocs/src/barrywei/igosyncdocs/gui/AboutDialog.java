/**
 * @(#)AboutDialog.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JButton;

import barrywei.igosyncdocs.action.DialogCloseAction;
import barrywei.igosyncdocs.bean.JLinkLabel;

import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class AboutDialog extends JDialog {
	
	private static final long serialVersionUID = -1928746320304732900L;
	private JPanel pnlMain = new JPanel();
	private JLabel lblLogo;
	private JButton btnClose;
	private JLabel lblMessage1;
	private JLinkLabel labelMessage2;
	private JLabel lblBlodMessage;
	
	public AboutDialog(IGoSyncDocsMain frame) {
		super(frame);
		initComponents();
	}
	
	private void initComponents() {
		
		setTitle("iGoSyncDocs Help");
		setSize(new Dimension(471, 306));
		setContentPane(pnlMain);
		pnlMain.setLayout(null);
		pnlMain.setBorder(new EmptyBorder(5,5,5,5));
		lblLogo = new JLabel();
		lblLogo.setBorder(new TitledBorder(""));
		lblLogo.setBounds(10, 10, 180, 250);
		pnlMain.add(lblLogo);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(362, 236, 93, 30);
		pnlMain.add(btnClose);
		
		lblBlodMessage = new JLabel("iGoSyncDocs Beta");
		lblBlodMessage.setFont(new Font("SimSun", Font.BOLD, 15));
		lblBlodMessage.setBounds(200, 10, 208, 23);
		pnlMain.add(lblBlodMessage);
		
		lblMessage1 = new JLabel("<html>iGoSyncDocs is a desktop application(Swing based) that offers an easy way to access and synchronize files to Google Docs across multiple computers.</html>");
		lblMessage1.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblMessage1.setVerticalAlignment(SwingConstants.TOP);
		lblMessage1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessage1.setBounds(210, 43, 245, 99);
		pnlMain.add(lblMessage1);
		
		String text = "<html>Right now it's open source software.Please visit Google Code site to check the newest version.</html>";
		String url = "http://code.google.com/p/igosyncdocs";
		labelMessage2 = new JLinkLabel(text, url);
		//labelMessage2 = new JLabel("<html>Right now it's open source software.please visit Google Code site to check the newest version.</html>");
		labelMessage2.setVerticalAlignment(SwingConstants.TOP);
		labelMessage2.setFont(new Font("SimSun", Font.PLAIN, 14));
		labelMessage2.setBounds(210, 138, 245, 63);
		pnlMain.add(labelMessage2);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		btnClose.addActionListener(new DialogCloseAction(this, false));
	}
}
