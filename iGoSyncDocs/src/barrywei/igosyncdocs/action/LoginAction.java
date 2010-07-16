/**
 * @(#)LoginAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import barrywei.igosyncdocs.bean.IConstant;
import barrywei.igosyncdocs.gui.FaceRunner;
import barrywei.igosyncdocs.gui.LoginFrame;
import barrywei.igosyncdocs.gui.LoginSplashFrame;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class LoginAction implements ActionListener {

	private LoginFrame frame;

	public LoginAction(JFrame frame) {
		this.frame = (LoginFrame) frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.validateUsernameAndPassword()) {
			LoginSplashFrame login = new LoginSplashFrame(frame);
			frame.setVisible(false);
			FaceRunner.run(login,new Dimension(450,300),IConstant.App_Name+" "+IConstant.App_Version+" Logining...",true);
			login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		}
	}
}
