/**
 * 
 * @(#)UploadFileProcessDialog.java Feb 18, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.dialog;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsException;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.model.UploadFileListModel;
import barrywey.igosyncdocs2011.gui.renderer.UploadFileListCellRenderer;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;
import javax.swing.border.EmptyBorder;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 18, 2011
 * @since JDK1.6
 */
public class UploadFileProcessDialog extends JDialog {

	
	private static final long serialVersionUID = -2397575486182737667L;
	private File[] files;
	private MainFrame frMain;
	private final JPanel pnlMain = new JPanel();
	private final JScrollPane pnlScroll = new JScrollPane();
	private final JPanel pnlSouth = new JPanel();
	private final JButton btnHide = new JButton(LanguageResource.getStringValue("main.dialog.upload.btn_hide"));
	private final JList listFiles = new JList();
	private final JPanel pnlMessage = new JPanel();
	private final JLabel lblfromMenuTheres = new JLabel(LanguageResource.getStringValue("main.dialog.upload.trip_message"));

	public UploadFileProcessDialog(File[] selectedFiles , MainFrame frMain) {
		this.files = selectedFiles;
		this.frMain = frMain;
		initComponets();
	}
	
	private void initComponets() {
		setTitle(LanguageResource.getStringValue("app.title"));
		setContentPane(pnlMain);
		pnlMain.setLayout(new BorderLayout(0, 0));
		pnlScroll.setBorder(new TitledBorder(null, LanguageResource.getStringValue("main.dialog.upload.panel_title"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnlMain.add(pnlScroll, BorderLayout.CENTER);
		listFiles.setModel(new UploadFileListModel(this.files));
		listFiles.setCellRenderer(new UploadFileListCellRenderer());
		
		pnlScroll.setViewportView(listFiles);
		FlowLayout fl_pnlSouth = (FlowLayout) pnlSouth.getLayout();
		fl_pnlSouth.setAlignment(FlowLayout.RIGHT);
		
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		pnlSouth.add(btnHide);
		pnlMessage.setBorder(new EmptyBorder(0, 5, 0, 5));
		
		pnlMain.add(pnlMessage, BorderLayout.NORTH);
		pnlMessage.setLayout(new BorderLayout(0, 0));
		
		pnlMessage.add(lblfromMenuTheres);
		setModal(true);
		setSize(new Dimension(444, 236));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); //hide dialog when it's closed
		
		btnHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideDialog();
			}
		});
		
		new Thread(new UploadFileThread()).start(); //start upload files
	}//end of initComponents()
	
	private void hideDialog() {
		this.setVisible(false);
	}//end of method
	
	private class UploadFileThread implements Runnable {

		public void run() {			
			try {
				frMain.getTabbedPane().setEnabled(false);
				frMain.getProgressBar().setIndeterminate(true);
				for(File file : files) {
					frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("main.dialog.upload.upload_process").replace("{1}", file.getName()));
					IGoSyncDocsBiz.upload(file);
				}
				IGoSyncDocsBiz.cacheAllItem();
				frMain.refreshAllTableData();
			} catch (IGoSyncDocsException e) {
				FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.dialog.upload.upload_error").replace("{1}",e.getMessage()));
			} finally {
				//dispose upload process dialog and set it to null reference
				SystemRuntime.UploadDialog.setVisible(false);
				SystemRuntime.UploadDialog.dispose();
				SystemRuntime.UploadDialog = null;
				frMain.getProgressBar().setIndeterminate(false);
				frMain.getProcessMessageLabel().setText("");
				frMain.getTabbedPane().setEnabled(true);
			}//end of try-catch-finally
		}//end of method run
	}//end of inner class;
}
