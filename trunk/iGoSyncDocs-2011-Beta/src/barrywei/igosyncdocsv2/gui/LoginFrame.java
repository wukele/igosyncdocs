/**
 * @(#)LoginFrame.java Oct 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import javax.swing.ImageIcon;

import barrywei.igosyncdocsv2.action.LoginAction;
import barrywei.igosyncdocsv2.bean.SystemRuntime;
import barrywei.igosyncdocsv2.gui.util.FaceUtils;
import barrywei.igosyncdocsv2.resource.LanguageResource;

import java.awt.Component;
/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 16, 2010
 * @since JDK1.6
 */
public class LoginFrame extends JFrame {

	public LoginFrame() {
		initComponents();
	}
	
	private void initComponents() {
		setTitle(LanguageResource.getStringValue("app.title"));
		setSize(new Dimension(400, 270));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);		
		
		pnlMain = new JPanel();
		setContentPane(pnlMain);
		pnlMain.setLayout(null);
		
		lblLogoGoesHere = new JLabel("");
		lblLogoGoesHere.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLogoGoesHere.setBorder(null);
		lblLogoGoesHere.setIcon(new ImageIcon(LoginFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/login-logo-380-115.png")));
		lblLogoGoesHere.setMinimumSize(new Dimension(75, 15));
		lblLogoGoesHere.setMaximumSize(new Dimension(75, 15));
		lblLogoGoesHere.setBounds(0, 0, 392, 115);
		lblLogoGoesHere.setName("lblLogoGoesHere");
		pnlMain.add(lblLogoGoesHere);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 125, 380, 105);
		panel.setName("panel");
		pnlMain.add(panel);
		panel.setLayout(null);
		
		lblUsername = new JLabel(LanguageResource.getStringValue("app.login.lblusername"));
		lblUsername.setBounds(52, 19, 103, 16);
		lblUsername.setName("lblUsername");
		panel.add(lblUsername);
		
		lblPassword = new JLabel(LanguageResource.getStringValue("app.login.lblpassword"));
		lblPassword.setBounds(52, 47, 103, 16);
		lblPassword.setName("lblPassword");
		panel.add(lblPassword);
		
		txtUsername = new JTextField(SystemRuntime.Settings.UserName);
		txtUsername.setBounds(154, 17, 163, 20);
		txtUsername.setName("txtUsername");
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(154, 45, 163, 20);
		txtPassword.setName("txtPassword");
		panel.add(txtPassword);
		
		btnLogin = new JButton(LanguageResource.getStringValue("app.login.btnlogin"));
		btnLogin.setBounds(280, 77, 84, 22);
		btnLogin.setName("btnLogin");
		panel.add(btnLogin);
		
		getRootPane().setDefaultButton(btnLogin);
		
		//event handler
		btnLogin.addActionListener(new LoginAction(this));
	}
		
	private static final long serialVersionUID = 88984566598299452L;
	private JPanel pnlMain;
	private JLabel lblLogoGoesHere;
	private JPanel panel;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	
	public boolean inputIsValidated() {
		if(txtUsername.getText().trim().equals("")) {
			FaceUtils.showErrorMessage(this, LanguageResource.getStringValue("app.login.validate_username"));
			return false;
		}
		
		if(new String(txtPassword.getPassword()).trim().equals("")) {
			FaceUtils.showErrorMessage(this, LanguageResource.getStringValue("app.login.validate_password"));
			return false;
		}
		return true;
	}
	
	public String getUsername() {
		return txtUsername.getText().trim();
	}
	
	public String getPassword() {
		return new String(txtPassword.getPassword()).trim();
	}	
}
