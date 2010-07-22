/**
 * @(#)LoginSplashFrame.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import barrywei.igosyncdocs.bean.ConfigManager;
import barrywei.igosyncdocs.bean.IConstant;
import barrywei.igosyncdocs.bean.IGoImageManager;
import barrywei.igosyncdocs.bean.UserConfig;
import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.factory.AbstractFactory;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class LoginSplashFrame extends JFrame {

	private static final long serialVersionUID = 2463253402562046441L;
	private LoginFrame frame = null;
	private JPanel pnlMain = new JPanel();
	private JLabel lblLogo = new JLabel(IGoImageManager.getInstance().getIcon(
			"splath-logo-450-300.jpg"));
	private JProgressBar progressBar = new JProgressBar();
	private JPanel pnlSouth = new JPanel();
	private JLabel lblMessage = new JLabel("Logining...");

	public LoginSplashFrame(LoginFrame frame) {
		this.frame = frame;
		init();
	}

	private void init() {
		
		// south panel
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.add(lblMessage);
		pnlSouth.add(progressBar, BorderLayout.SOUTH);

		// main panel
		pnlMain.setLayout(new BorderLayout());
		setUndecorated(true);
		pnlMain.add(lblLogo);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		pnlMain.setBorder(BorderFactory.createEmptyBorder());

		setContentPane(pnlMain);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/ch/randelshofer/quaqua/images/FileView.computerIcon.png")));		

		new Thread(new LoginThread(frame, this)).start();
	}

	private class LoginThread implements Runnable {

		private LoginFrame frame = null;
		private String username = null;
		private String password = null;
		private LoginSplashFrame splash = null;

		public LoginThread(LoginFrame frame, LoginSplashFrame splash) {
			this.frame = frame;
			this.username = frame.getEnteredUsername();
			this.password = frame.getEnteredPassword();
			this.splash = splash;
		}

		@Override
		public void run() {
			try {
				progressBar.setIndeterminate(true);
				lblMessage.setText(" "+IConstant.App_Name+" "+IConstant.App_Version+" is now connecting to Google Docs Service...");
				IGoSyncDocsBiz biz = AbstractFactory.createSyncDocsBizObject();
				if(this.username.lastIndexOf('@') == -1)
					this.username+="@gmail.com";
				biz.login(this.username, this.password);

				// if login success
				lblMessage.setText(" Login Success...Loading Data...");
				biz.cacheAllItems();
				
				frame.dispose();// dispose login frame
				splash.dispose();// dispose splash frame
				
				createNeededFolderAndFile();
				
				UserConfig.Username = this.username;
				IGoSyncDocsMain testMain = new IGoSyncDocsMain();
				testMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				FaceRunner.run(testMain, new Dimension(950,655), IConstant.App_Name+" "+IConstant.App_Version,true);
				
			} catch (Exception e) {
				progressBar.setIndeterminate(false);
				lblMessage.setText("Connecting failed...");
				JOptionPane.showMessageDialog(null,"Server Response:\n"+e.getMessage(),"IGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
				splash.dispose();
				frame.setVisible(true);
			}
		}// end of run
		
		private void createNeededFolderAndFile() throws IOException {
			String userDir = System.getProperty("user.home");
			File file = new File(userDir+File.separator+IConstant.App_Name+"-"+IConstant.App_Version);
			if(!file.exists())
				file.mkdir();
			file.setWritable(true);
			File conf = new File(file.getPath()+File.separator+"Config.conf");
			if(!conf.exists()) {
				conf.createNewFile();
				conf.setWritable(true);
				ConfigManager.setDefaultExitAction("tray");
				ConfigManager.setNeverConfirmForExit("no");
			}//end of if
		}//end of method
	}// end of inner class
}
