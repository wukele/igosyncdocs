/**
 * @(#)SystemTrayAction.java Jul 19, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import barrywei.igosyncdocs.bean.ICategory;
import barrywei.igosyncdocs.bean.IConstant;
import barrywei.igosyncdocs.bean.ConfigManager;
import barrywei.igosyncdocs.gui.ConfirmExitDialog;
import barrywei.igosyncdocs.gui.FaceRunner;
import barrywei.igosyncdocs.gui.IGoSyncDocsMain;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 19, 2010
 * @since JDK1.6
 */
public class SystemTrayAction extends WindowAdapter {

	private IGoSyncDocsMain frame;

	public SystemTrayAction(IGoSyncDocsMain frame) {
		this.frame = frame;
	}

	public void windowClosing(WindowEvent e) {
		try {
			if (ConfigManager.isNeverConfirmForExit()) {
				doDefaultAction();
			} else {
				ConfirmExitDialog dialog = new ConfirmExitDialog(frame);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModal(true);
				FaceRunner.run(dialog, new Dimension(420, 220),IConstant.App_Name + " " + IConstant.App_Version, true);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void doDefaultAction() {
		try {
			if (ConfigManager.isDefaultExitActionTray()) {
				windowIconified(null);
			} else {
				frame.dispose();
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void windowIconified(WindowEvent e) {
		if (SystemTray.isSupported()) {
			createTray();
		}
	}

	private void createTray() {
		try {
			if (!SystemTray.isSupported()) {
				return;
			}
			frame.setVisible(false);
			final SystemTray tray = SystemTray.getSystemTray();
			// create popupmenu
			final PopupMenu pm = new PopupMenu();
			final MenuItem miExit = new MenuItem("Close iGoSyncDocs ");
			final MenuItem miShow = new MenuItem("Restore iGoSyncDocs");
			miShow.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
			final MenuItem miRefresh = new MenuItem("Refresh All");
			final MenuItem miNewDoc = new MenuItem("Create New Document");
			final MenuItem miNewPre = new MenuItem("Create New Presentation");
			final MenuItem miNewSpr = new MenuItem("Create New Spreadsheet");

			pm.add(miShow);
			pm.addSeparator();
			pm.add(miNewDoc);
			pm.add(miNewPre);
			pm.add(miNewSpr);
			pm.add(miRefresh);
			pm.addSeparator();
			pm.add(miExit);

			// create image
			final Image trayImage = new ImageIcon(
					SystemTrayAction.class
							.getResource("/ch/randelshofer/quaqua/images/FileView.computerIcon.png")).getImage();

			// create tool tips
			String tooltip = new String("iGoSyncDocs System Tray");

			// create tray icon
			final TrayIcon trayIcon = new TrayIcon(trayImage, tooltip, pm);

			tray.add(trayIcon);

			// listen for user to double-click on tray icon
			trayIcon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL);
					tray.remove(trayIcon);
				}
			});

			// exit action
			miExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null,
							"Are you sure to Exit?", "iGoSyncDocs",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						frame.dispose();
						System.exit(1);
					}
				}
			});

			// restore action
			miShow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL);
					tray.remove(trayIcon);
				}
			});

			miRefresh.addActionListener(new RefreshAction(frame));
			miNewDoc.addActionListener(new CreateNewAction(ICategory.Documents,
					frame));
			miNewPre.addActionListener(new CreateNewAction(
					ICategory.Presentations, frame));
			miNewSpr.addActionListener(new CreateNewAction(
					ICategory.SpreadSheets, frame));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "System tray can not added.\n"
					+ e.getMessage(), "iGoSyncDocs",
					JOptionPane.INFORMATION_MESSAGE);
			frame.setVisible(true);
		}
	}// end of method
}
