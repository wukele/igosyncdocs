/**
 * @(#)ViewOnLineAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JOptionPane;

import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.factory.AbstractFactory;
import barrywei.igosyncdocs.gui.IGoSyncDocsMain;
import barrywei.igosyncdocs.gui.model.IGoSyncDocsRemoteViewTableItem;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class ViewOnLineAction implements ActionListener,Runnable {
	
	private IGoSyncDocsMain mainFrame = null;
	private IGoSyncDocsBiz biz = null;
	
	public ViewOnLineAction(IGoSyncDocsMain mainFrame) {
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
		IGoSyncDocsRemoteViewTableItem item = mainFrame.getRemoteTableSelectedItem();
		if(item!=null) {
			try {
				URI uri = new URI(item.getEntry().getHtmlLink().getHref());
				Desktop.getDesktop().browse(uri);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,"Server Response:\n"+ex.getMessage(),"IGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
			}//end of try-catch			
		}
	}
}
