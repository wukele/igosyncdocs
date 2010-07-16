/**
 * @(#)ShareWithEmailDialog.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;

import barrywei.igosyncdocs.action.DialogCloseAction;
import barrywei.igosyncdocs.action.RemoveAclAction;
import barrywei.igosyncdocs.action.ShareWithEmailAction;
import barrywei.igosyncdocs.biz.IGoSyncDocsBiz;
import barrywei.igosyncdocs.biz.impl.SyncDocsException;
import barrywei.igosyncdocs.factory.AbstractFactory;
import barrywei.igosyncdocs.gui.model.IGoSyncDocsAclDataTableModel;
import barrywei.igosyncdocs.gui.model.IGoSyncDocsRemoteViewTableItem;
import barrywei.igosyncdocs.gui.renderer.IGoSyncDocsAclDataTableCellRenderer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.docs.DocumentListEntry;
import java.awt.Toolkit;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class ShareWithEmailDialog extends JDialog {

	private static final long serialVersionUID = 1470381477268075374L;
	private JPanel pnlMain = new JPanel();
	private JPanel panel;
	private IGoSyncDocsMain frame = null;
	private IGoSyncDocsBiz biz = null;
	private JScrollPane scrollPane;
	private JTable tblData;
	private JTextArea textArea;
	private JRadioButton rboWrite;
	private JRadioButton rboRead;
	private JButton btnClose;
	private JButton btnShare;
	private ButtonGroup bgGroup = new ButtonGroup();
	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem miRemove = new JMenuItem("Remove");
	private JMenuItem miSetReader = new JMenuItem("Make as Reader");
	private JMenuItem miSetWriter = new JMenuItem("Make as Writer");
	private AclEntry selctedAclEntry = null;

	public ShareWithEmailDialog(IGoSyncDocsMain frame) {
		super(frame);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShareWithEmailDialog.class.getResource("/ch/randelshofer/quaqua/images/FileView.computerIcon.png")));
		try {
			this.frame = frame;
			biz = AbstractFactory.createSyncDocsBizObject();
			initComponents();
			initNeededData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"IGoSyncDocs Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	private void initComponents() {
		
		popup.add(miRemove);
		popup.add(new JSeparator());
		popup.add(miSetReader);
		popup.add(new JSeparator());
		popup.add(miSetWriter);
		
		pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setContentPane(pnlMain);
		pnlMain.setLayout(new BorderLayout(5, 5));

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 180));
		pnlMain.add(scrollPane, BorderLayout.NORTH);

		tblData = new JTable(10, 3);
		tblData.setShowGrid(false);
		scrollPane.setViewportView(tblData);

		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Add People"));
		panel.setPreferredSize(new Dimension(500, 150));
		pnlMain.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		btnShare = new JButton("Share");
		btnShare.setBounds(283, 117, 93, 29);
		panel.add(btnShare);

		btnClose = new JButton("Close");
		btnClose.setBounds(384, 117, 93, 29);
		panel.add(btnClose);

		rboWrite = new JRadioButton("Write");
		rboWrite.setBounds(384, 46, 68, 23);
		panel.add(rboWrite);

		rboRead = new JRadioButton(" Read");
		rboRead.setSelected(true);
		rboRead.setBounds(384, 71, 68, 23);
		panel.add(rboRead);

		bgGroup.add(rboRead);
		bgGroup.add(rboWrite);

		JScrollPane scrollTxt = new JScrollPane();
		scrollTxt.setBounds(10, 32, 366, 75);
		panel.add(scrollTxt);

		textArea = new JTextArea();
		scrollTxt.setViewportView(textArea);
		
		textArea.setText(" Enter email address and \n Split email address with ',' if you need.");
		
		//event handler
		btnClose.addActionListener(new DialogCloseAction(this, false));
		
		btnShare.addActionListener(new ShareWithEmailAction(this,frame.getRemoteTableSelectedItem(),1));
		
		textArea.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(textArea.getText().length()==0) {
					textArea.setText(" Enter email address and \n Split email address with ',' if you need.");
				}				
			}
			public void focusGained(FocusEvent e) {
				textArea.setText("");
			}
		});
		
		miRemove.addActionListener(new RemoveAclAction(this));
		miSetReader.addActionListener(null);
		miSetWriter.addActionListener(null);
	}
	
	public AclEntry getSelectedAclEntry() {
		return selctedAclEntry;
	}
	
	public DocumentListEntry getSelectedEntry() {
		return this.frame.getRemoteTableSelectedItem().getEntry();
	}
	
	public String getUserEnterdAddress() {
		return textArea.getText().trim();
	}
	
	public boolean isWriteableChecked() {
		return rboWrite.isSelected()?true:false;
	}

	public void initNeededData() throws  SyncDocsException{
		IGoSyncDocsRemoteViewTableItem item = frame.getRemoteTableSelectedItem();
		AclFeed feeds = biz.getAclFeed(item.getEntry());
		fillTableWithData(feeds);
	}

	public void fillTableWithData(AclFeed feeds) {
		tblData.setModel(new IGoSyncDocsAclDataTableModel(feeds));
		int count = tblData.getModel().getColumnCount();
		for(int i=0;i<count;i++) {
			tblData.getColumnModel().getColumn(i).setCellRenderer(new IGoSyncDocsAclDataTableCellRenderer());
		}
		tblData.getColumnModel().getColumn(0).setPreferredWidth(300);
		tblData.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblData.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblData.setRowHeight(25);
		tblData.setShowGrid(false);
		tblData.setDragEnabled(true);

		tblData.setAutoCreateColumnsFromModel(true);
		tblData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblData.getTableHeader().setReorderingAllowed(false);	
		tblData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					int selectedRow = tblData.getSelectedRow();
					if(selectedRow!=-1) {
						selctedAclEntry = ((IGoSyncDocsAclDataTableModel)tblData.getModel()).getData().get(selectedRow);
						enablePopupMenu(selctedAclEntry);
					}//end of if					
					popup.show(tblData, e.getX(),e.getY());
				}
			}
		});
	}
	
	private void enablePopupMenu(AclEntry entry) {
		if(entry.getRole().getValue().equals("reader")) {
			miSetReader.setEnabled(false);
			miSetWriter.setEnabled(true);
			miRemove.setEnabled(true);
		}else if(entry.getRole().getValue().equals("writer")) {
			miSetReader.setEnabled(true);
			miSetWriter.setEnabled(false);
			miRemove.setEnabled(true);			
		}else  {
			miSetReader.setEnabled(false);
			miSetWriter.setEnabled(false);
			miRemove.setEnabled(false);			
		}
	}
}
