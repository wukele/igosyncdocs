/**
 * @(#)BaseSearchAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gdata.data.docs.DocumentListEntry;
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
public class BaseSearchAction implements ActionListener, Runnable {

	private IGoSyncDocsMain frame;
	private IGoSyncDocsBiz biz = null;

	public BaseSearchAction(IGoSyncDocsMain frame) {
		this.frame = frame;
		try {
			biz = AbstractFactory.createSyncDocsBizObject();
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"iGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"iGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"iGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"iGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void run() {

		try {
			frame.getRemoteTable().removeEditor();
			frame.disableRemoteTableAndProgressbar();
			List<DocumentListEntry> list = biz.fullTextSearch(frame.getSearchText());
			frame.getRemoteTable().setModel(new IGoSyncDocsRemoteViewTableModel(list));
		} catch (SyncDocsException e) {
			JOptionPane.showMessageDialog(null, "Server Response:\n"
					+ e.getMessage(), "iGoSyncDocs Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			frame.enableRemoteTableAndProgressbar();
			frame.updateRemoteTable();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = frame.getSearchText();
		if (text != null && text.length() > 0) {
			new Thread(this).start();
		}
	}
}
