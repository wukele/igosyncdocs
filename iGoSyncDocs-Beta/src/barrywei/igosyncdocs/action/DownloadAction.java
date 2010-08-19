/**
 * @(#)DownloadAction.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.google.gdata.data.docs.DocumentListEntry;

import barrywei.igosyncdocs.bean.ICategory;
import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.biz.impl.SyncDocsException;
import barrywei.igosyncdocs.factory.AbstractFactory;
import barrywei.igosyncdocs.gui.IGoSyncDocsMain;
import barrywei.igosyncdocs.gui.filefilter.DocumentFileFilter;
import barrywei.igosyncdocs.gui.filefilter.PresentationFileFilter;
import barrywei.igosyncdocs.gui.filefilter.SpreadsheetFileFilter;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class DownloadAction implements ActionListener,Runnable{

	private IGoSyncDocsMain mainFrame = null;
	private IGoSyncDocsBiz biz = null;
	private int type;
	
	public DownloadAction(IGoSyncDocsMain mainFrame,int type) {
		this.mainFrame = mainFrame;
		this.type = type;
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
		if(biz!=null) {
			new Thread(this).start();
		}
	}
	
	@Override
	public void run() {
		JFileChooser chooser = new JFileChooser();
		if(type == ICategory.Documents)
			chooser.addChoosableFileFilter(new DocumentFileFilter());
		if(type == ICategory.Presentations)
			chooser.addChoosableFileFilter(new PresentationFileFilter());
		if(type == ICategory.SpreadSheets)
			chooser.addChoosableFileFilter(new SpreadsheetFileFilter());
		int result = chooser.showSaveDialog(mainFrame);
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				mainFrame.disableRemoteTableAndProgressbar();
				DocumentListEntry entry = mainFrame.getRemoteTableSelectedItem().getEntry();
				file = createNewFileForDownload(file,entry);
				if(file!=null && !file.exists())
					file.createNewFile();
				String extension = DocumentFileFilter.getFileExtension(file);
				if(type == ICategory.Documents)
					biz.downloadDocument(entry, file.getPath(),extension);
				else if(type == ICategory.Presentations)
					biz.downloadPresentation(entry, file.getPath(),extension);
				else if(type == ICategory.SpreadSheets)
					biz.downloadSpreadsheet(entry, file.getPath(),extension);
				else
					biz.downloadFile(entry, file.getPath());//only the remote format
			} catch (SyncDocsException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"iGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"File can NOT be created.\n"+e.getMessage(),"iGoSyncDocs Error",JOptionPane.ERROR_MESSAGE);
			} finally {
				mainFrame.enableRemoteTableAndProgressbar();
			}
		}//end of if
	}
	
	private File createNewFileForDownload(File file,DocumentListEntry entry) throws IOException {
		File newFile = null;
		
		if(type== ICategory.OtherFiles) {
			String fileName = entry.getTitle().getPlainText();
			newFile = new File(file.getPath()+"."+DocumentFileFilter.getFileExtension(new File(fileName)));
			return newFile;
		}
		
		if(file.getName().lastIndexOf('.')==-1) {
			if(type == ICategory.Documents)
				newFile = new File(file.getPath()+".doc");
			else if(type == ICategory.SpreadSheets)
				newFile = new File(file.getPath()+".xls");
			else if(type == ICategory.Presentations) 
				newFile = new File(file.getPath()+".ppt");
		}
		
		if(newFile==null)
			newFile = file;
		
		return newFile;
	}
}
