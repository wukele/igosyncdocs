/**
 * @(#)LoginSplashFrame.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

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
//		getRootPane().putClientProperty("JComponent.sizeVariant", "mini");
//		getRootPane().putClientProperty("Quaqua.RootPane.isPalette",Boolean.TRUE);
		pnlMain.add(lblLogo);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		pnlMain.setBorder(BorderFactory.createEmptyBorder());

		setContentPane(pnlMain);
		setResizable(false);

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
				biz.login(this.username, this.password);

				// if login success
				lblMessage.setText(" Login Success...Loading Data...");
				biz.cacheAllItems();

//				MainFrame main = new MainFrame();
//				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				FaceRunner.run(main, new Dimension(1000, 700), "iGoSyncDocs",true);
				
				
				
				frame.dispose();// dispose login frame
				splash.dispose();// dispose splash frame
				
				IGoSyncDocsMain testMain = new IGoSyncDocsMain();
				testMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				UserConfig.Username = this.username;
				FaceRunner.run(testMain, new Dimension(950,650), IConstant.App_Name+" "+IConstant.App_Version,true);
				
			} catch (Exception e) {
				progressBar.setIndeterminate(false);
				lblMessage.setText("Connecting failed...");
				JOptionPane.showMessageDialog(null,"Server Response:\n"+e.getMessage(),"IGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
				splash.dispose();
				frame.setVisible(true);
			}
		}// end of run
	}// end of inner class
}
