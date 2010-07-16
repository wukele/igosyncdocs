/**
 * @(#)Login.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

import barrywei.igosyncdocs.action.LoginAction;
import java.awt.Toolkit;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 7228625408955309649L;
	private JPanel pnlMain = new JPanel();
	private JLabel lblLogo;
	private JPanel pnlInput;
	private JButton btnLogin;
	private JButton btnExit;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/ch/randelshofer/quaqua/images/FileView.computerIcon.png")));
		initComponents();
	}

	private void initComponents() {
		pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setSize(new Dimension(350, 280));
		setContentPane(pnlMain);
		pnlMain.setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
						LoginFrame.class.getResource("/barrywei/igosyncdocs/resource/icon/logo-350-100.jpg")));
		lblLogo.setBounds(0, 0, 350, 100);
		pnlMain.add(lblLogo);

		pnlInput = new JPanel();
		pnlInput.setBorder(new TitledBorder(null, "", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		pnlInput.setBounds(10, 110, 324, 93);
		pnlMain.add(pnlInput);
		pnlInput.setLayout(null);

		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 11, 80, 30);
		pnlInput.add(lblUsername);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 44, 80, 30);
		pnlInput.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(87, 11, 227, 30);
		pnlInput.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(87, 44, 227, 30);
		pnlInput.add(txtPassword);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(147, 209, 89, 30);
		pnlMain.add(btnLogin);

		btnExit = new JButton("Exit");
		btnExit.setBounds(246, 209, 89, 30);
		pnlMain.add(btnExit);

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		btnLogin.addActionListener(new LoginAction(this));
		
		//if user press Enter key,than login
		getRootPane().setDefaultButton(btnLogin);
		txtPassword.requestFocus();
	}

	public String getEnteredUsername() {
		return this.txtUsername.getText().trim();
	}

	public String getEnteredPassword() {
		return new String(this.txtPassword.getPassword());
	}

	public JButton getLoginButton() {
		return this.btnLogin;
	}

	public boolean validateUsernameAndPassword() {
		boolean result = false;
		if (txtUsername.getText().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Please input your username for login.",
					"iGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
		}else if(new String(txtPassword.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this,
					"Please input your password for login.",
					"iGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);			
		}else
			result = true;
		return result;
	}
}
