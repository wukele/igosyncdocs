/**
 * @(#)ProxySettingDialog.java Oct 17, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui.dialog;

import javax.swing.JDialog;
import javax.swing.JPanel;

import barrywei.igosyncdocsv2.bean.SystemRuntime;
import barrywei.igosyncdocsv2.gui.util.FaceUtils;
import barrywei.igosyncdocsv2.resource.LanguageResource;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 17, 2010
 * @since JDK1.6
 */
public class ProxySettingDialog extends JDialog implements ActionListener {
	
	public ProxySettingDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		setSize(new Dimension(480, 270));
		setLocationRelativeTo(null);
		setTitle(LanguageResource.getStringValue("app.proxy.title"));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		pnlMain = new JPanel();
		pnlMain.setName("pnlMain");
		setContentPane(pnlMain);
		pnlMain.setLayout(null);
		
		pnlSettings = new JPanel();
		pnlSettings.setBorder(new TitledBorder(null, LanguageResource.getStringValue("app.proxy.pnl_title"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSettings.setBounds(6, 6, 460, 194);
		pnlSettings.setName("pnlSettings");
		pnlMain.add(pnlSettings);
		pnlSettings.setLayout(null);
		
		lblServer = new JLabel(LanguageResource.getStringValue("app.proxy.lblserver"));
		lblServer.setBounds(24, 30, 58, 16);
		lblServer.setName("lblServer");
		pnlSettings.add(lblServer);
		
		lblPort = new JLabel(LanguageResource.getStringValue("app.proxy.lblport"));
		lblPort.setBounds(24, 62, 58, 16);
		lblPort.setName("lblPort");
		pnlSettings.add(lblPort);
		
		lblUsername = new JLabel(LanguageResource.getStringValue("app.proxy.lblusername"));
		lblUsername.setBounds(233, 30, 58, 16);
		lblUsername.setName("lblUsername");
		pnlSettings.add(lblUsername);
		
		lblPassword = new JLabel(LanguageResource.getStringValue("app.proxy.lblpassword"));
		lblPassword.setBounds(233, 62, 58, 16);
		lblPassword.setName("lblPassword");
		pnlSettings.add(lblPassword);
		
		txtServer = new JTextField();
		txtServer.setText("localhost");
		txtServer.setBounds(76, 28, 135, 20);
		txtServer.setName("txtServer");
		pnlSettings.add(txtServer);
		txtServer.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setText("8580");
		txtPort.setColumns(10);
		txtPort.setBounds(76, 60, 135, 20);
		txtPort.setName("txtPort");
		pnlSettings.add(txtPort);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(300, 28, 135, 20);
		txtUsername.setName("txtUsername");
		pnlSettings.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(300, 60, 135, 20);
		txtPassword.setName("txtPassword");
		pnlSettings.add(txtPassword);
		
		txtMessage = new JTextPane();
		txtMessage.setEditable(false);
		txtMessage.setContentType("text/html");
		txtMessage.setText(LanguageResource.getStringValue("app.proxy.lblmessage"));
		txtMessage.setBackground(SystemColor.inactiveCaptionText);
		txtMessage.setBounds(24, 90, 411, 61);
		txtMessage.setName("txtMessage");
		pnlSettings.add(txtMessage);
		
		btnTestConnection = new JButton(LanguageResource.getStringValue("app.proxy.btn_test"));
		btnTestConnection.setBounds(24, 159, 99, 22);
		pnlSettings.add(btnTestConnection);
		btnTestConnection.setName("btnTest");
		
		chkUseProxy = new JCheckBox(LanguageResource.getStringValue("app.proxy.chk"));
		chkUseProxy.setBounds(16, 212, 209, 22);
		pnlMain.add(chkUseProxy);
		chkUseProxy.setName("chkUseProxy");
		
		btnOk = new JButton(LanguageResource.getStringValue("app.proxy.btn_ok"));
		btnOk.addActionListener(this);
		btnOk.setBounds(367, 212, 99, 22);
		btnOk.setName("btnOk");
		pnlMain.add(btnOk);
		
		getRootPane().setDefaultButton(btnOk);
		changePanelStatus(pnlSettings,SystemRuntime.Settings.UseProxy);
		
		//event handler
		chkUseProxy.addItemListener(new CheckboxItemStateChanged());
		btnTestConnection.addActionListener(new TestProxyConnection());
		readSettings();
		
	}

	private static final long serialVersionUID = -6642531062523248721L;
	private JPanel pnlMain;
	private JPanel pnlSettings;
	private JLabel lblServer;
	private JLabel lblPort;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JButton btnTestConnection;
	private JTextField txtServer;
	private JTextField txtPort;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextPane txtMessage;
	private JCheckBox chkUseProxy;
	private JButton btnOk;
	
	private class TestProxyConnection implements ActionListener ,Runnable {
		
		public void actionPerformed(ActionEvent e) {
			btnTestConnection.setEnabled(false);
			new Thread(this).start();
		}//end of method
		
		public void run() {
			boolean result = SystemRuntime.testProxyConnection();
			if(result)
				FaceUtils.showInformationMessage(ProxySettingDialog.this, LanguageResource.getStringValue("app.proxy.proxy_reachable"));
			else
				FaceUtils.showErrorMessage(ProxySettingDialog.this, LanguageResource.getStringValue("app.proxy.proxy_unreachable"));
			btnTestConnection.setEnabled(true);
		}
	}//end of inner class
	
	private class CheckboxItemStateChanged implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			JCheckBox chk = (JCheckBox)e.getSource();
			if(chk.isSelected()) {
				changePanelStatus(pnlSettings,true);
				SystemRuntime.Settings.UseProxy = true;				
			}else {
				changePanelStatus(pnlSettings,false);
				SystemRuntime.Settings.UseProxy = false;				
			}
		}//end of method
	}//end of class
	
	private void readSettings() {
		try {
			SystemRuntime.loadUserSettings();
			if(SystemRuntime.Settings.UseProxy) {
				changePanelStatus(pnlSettings,true);
				chkUseProxy.setSelected(true);
			}else {
				changePanelStatus(pnlSettings, false);
				chkUseProxy.setSelected(false);
			}
			if(!SystemRuntime.Settings.Proxy_Server.equals(""))
				txtServer.setText(SystemRuntime.Settings.Proxy_Server);
			if(!SystemRuntime.Settings.Proxy_Port.equals(""))
				txtPort.setText(SystemRuntime.Settings.Proxy_Port);
			if(!SystemRuntime.Settings.Proxy_Password.equals(""))
				txtPassword.setText(SystemRuntime.Settings.Proxy_Password);
			if(!SystemRuntime.Settings.Proxy_Username.equals(""))
				txtUsername.setText(SystemRuntime.Settings.Proxy_Username);			
		} catch (FileNotFoundException e) {
			String message = LanguageResource.getStringValue("app.proxy.error_filenotfound");
			message.replace("{1}", e.getMessage());
			FaceUtils.showErrorMessage(this, message);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("app.proxy.error_ioerror");
			message.replace("{1}", e.getMessage());
			FaceUtils.showErrorMessage(this, message);			
		} catch (ClassNotFoundException e) {
			String message = LanguageResource.getStringValue("app.proxy.error_clz");
			message.replace("{1}", e.getMessage());
			FaceUtils.showErrorMessage(this, message);			
		}
		
	}
	
	private static void changePanelStatus(JPanel pnl,boolean status) {
		Component[] components = pnl.getComponents();
		for(Component comp : components) {
			comp.setEnabled(status);
		}
	}	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			btnOkActionPerformed(e);
		}
	}
	
	protected void btnOkActionPerformed(ActionEvent e) {
		if(SystemRuntime.Settings.UseProxy) {
			SystemRuntime.Settings.Proxy_Server = txtServer.getText().trim();
			SystemRuntime.Settings.Proxy_Port = txtPort.getText().trim();
			SystemRuntime.Settings.Proxy_Username = txtUsername.getText().trim();
			SystemRuntime.Settings.Proxy_Password = new String(txtPassword.getPassword()).trim();
			changeProxyStatus(true);
		}else
			changeProxyStatus(false);
		
		try {
			SystemRuntime.saveUserSettings(SystemRuntime.Settings);
		} catch (FileNotFoundException e1) {
			String message = LanguageResource.getStringValue("app.proxy.error_filenotfound");
			message.replace("{1}", e1.getMessage());
		} catch (IOException e1) {
			String message = LanguageResource.getStringValue("app.proxy.error_ioerror");
			message.replace("{1}", e1.getMessage());
			FaceUtils.showErrorMessage(this, message);	
		}
		this.dispose();
		this.getOwner().setVisible(true);
	}//end of method
	
	private void changeProxyStatus(boolean enable) {
		if(enable) {
			System.getProperties().put("proxySet", "true");
			System.getProperties().put("https.proxyHost", SystemRuntime.Settings.Proxy_Server);
			System.getProperties().put("https.proxyPort", SystemRuntime.Settings.Proxy_Port);
			System.getProperties().put("http.proxyHost", SystemRuntime.Settings.Proxy_Server);
			System.getProperties().put("http.proxyPort", SystemRuntime.Settings.Proxy_Port);			
		}else {
			System.getProperties().put("proxySet", "false");
		}//end of if
	}//end of method
}
