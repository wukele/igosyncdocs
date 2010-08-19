/**
 * @(#)UploadFileAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import barrywei.igosyncdocs.bean.ICategory;
import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.biz.impl.SyncDocsException;
import barrywei.igosyncdocs.factory.AbstractFactory;
import barrywei.igosyncdocs.gui.IGoSyncDocsMain;
import barrywei.igosyncdocs.gui.model.IGoSyncDocsRemoteViewTableModel;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class UploadFileAction implements ActionListener,Runnable{
	
	private IGoSyncDocsMain mainFrame = null;
	private IGoSyncDocsBiz biz = null;
	
	public UploadFileAction(IGoSyncDocsMain mainFrame) {
		this.mainFrame = mainFrame;
		try {
			biz = AbstractFactory.createSyncDocsBizObject();
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"iGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"iGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"iGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"iGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
		}		
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(biz!=null)
			new Thread(this).start();
	}
	
	@Override
	public void run() {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		int result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			try {
				mainFrame.getRemoteTable().removeEditor();
				mainFrame.disableRemoteTableAndProgressbar();
				biz.uploadFiles(files);
				biz.cacheAllItems();
				mainFrame.getRemoteTable().setModel(new IGoSyncDocsRemoteViewTableModel(ICategory.All));
			} catch (SyncDocsException e) {
				JOptionPane.showMessageDialog(null,"Server Response:\n"+e.getMessage(),"IGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
			} finally {
				mainFrame.enableRemoteTableAndProgressbar();
				mainFrame.updateRemoteTable();
			}
		}
	}
}
