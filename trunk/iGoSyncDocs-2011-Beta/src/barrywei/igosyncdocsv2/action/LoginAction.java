/**
 * @(#)LoginAction.java Oct 17, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import barrywei.igosyncdocsv2.bean.SystemRuntime;
import barrywei.igosyncdocsv2.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocsv2.biz.IGoSyncDocsException;
import barrywei.igosyncdocsv2.gui.LoginFrame;
import barrywei.igosyncdocsv2.gui.MainFrame;
import barrywei.igosyncdocsv2.gui.dialog.SplashProgressDialog;
import barrywei.igosyncdocsv2.gui.util.FaceUtils;
import barrywei.igosyncdocsv2.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 17, 2010
 * @since JDK1.6
 */
public class LoginAction implements ActionListener ,Runnable{
	
	private LoginFrame frLogin;
	
	public LoginAction(LoginFrame frLogin) {
		this.frLogin = frLogin;
	}

	public void actionPerformed(ActionEvent e) {
		if(frLogin.inputIsValidated()) {
			new Thread(this).start();
		}//end of if
	}//end of method

	public void run() {
		this.frLogin.setVisible(false);
		SplashProgressDialog splash = new SplashProgressDialog();
		splash.setLocationRelativeTo(null);
		splash.setVisible(true);
		
		//auto-complete user name
		String userName = frLogin.getUsername();
		if(userName.lastIndexOf("@") == -1)
			userName+="@gmail.com";
		
		String message = IGoSyncDocsBiz.login(userName, frLogin.getPassword());
		if(message.equals("success")) {
			try {
				SystemRuntime.Settings.UserName = userName;
				splash.setMessage(LanguageResource.getStringValue("main.splash.loading"));				
				IGoSyncDocsBiz.cacheAllItem();
				//if success,start to load data from server.
				MainFrame frMain = new MainFrame();
				frMain.setVisible(true);
				frMain.setLocationRelativeTo(null);
				frLogin.dispose();
				splash.dispose();				
			} catch (IGoSyncDocsException e) {
				FaceUtils.showErrorMessage(splash, e.getMessage());
				splash.setVisible(false);
				this.frLogin.setVisible(true);				
			}
		}else {
			FaceUtils.showErrorMessage(frLogin, message);
			splash.setVisible(false);
			this.frLogin.setVisible(true);			
		}//end of if success
	}//end of method run()
}//end of class
