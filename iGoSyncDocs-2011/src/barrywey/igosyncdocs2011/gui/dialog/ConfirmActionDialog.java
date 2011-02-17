/**
 * 
 * @(#)ConfirmActionDialog.java Feb 11, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

import barrywey.igosyncdocs2011.action.HideItemAction;
import barrywey.igosyncdocs2011.action.TrashItemAction;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.model.ConfirmListModel;
import barrywey.igosyncdocs2011.gui.renderer.ConfirmListRenderer;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Feb 11, 2011
 * @since JDK1.6
 */
public class ConfirmActionDialog extends JDialog {

	private static final long serialVersionUID = -1452168472121468458L;
	private final JPanel pnlMain = new JPanel();
	private String confirmMessage;
	private String actionType;
	private MainFrame frMain;
	private final JLabel lblLogo = new JLabel("");
	private final JLabel lblMessage = new JLabel("Are you sure to XXX  item(s) ?");
	private final JScrollPane pnlScroll = new JScrollPane();
	private JList listItems ;
	private final JButton btnYes = new JButton("Yes");
	private final JButton btnNo = new JButton("No");

	public ConfirmActionDialog(String confimMessage,String actionType, MainFrame frMain) {
		this.confirmMessage = confimMessage;
		this.actionType = actionType;
		this.frMain = frMain;
		initComponents();
	}

	private void initComponents() {
		setTitle("Confirm Action");
		setContentPane(pnlMain);
		setSize(new Dimension(460,220));
		setModal(true);
		pnlMain.setLayout(null);
		lblLogo.setIcon(new ImageIcon(ConfirmActionDialog.class.getResource("/barrywey/igosyncdocs2011/resource/image/prompt-logo-130-150.png")));
		lblLogo.setBounds(0, 0, 136, 185);
		
		pnlMain.add(lblLogo);
		lblMessage.setBounds(148, 6, 287, 31);
		lblMessage.setText(this.confirmMessage);
		
		pnlMain.add(lblMessage);
		pnlScroll.setBounds(148, 41, 287, 110);
		
		pnlMain.add(pnlScroll);
		listItems = new JList(new ConfirmListModel()); //new this list
		listItems.setCellRenderer(new ConfirmListRenderer()); // cell renderer
		pnlScroll.setViewportView(listItems);
		btnYes.setBounds(351, 163, 84, 22);
		
		pnlMain.add(btnYes);
		btnNo.setBounds(255, 163, 84, 22);
		
		pnlMain.add(btnNo);
		
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yesButtonPressed();
			}
		});
	}
	
	private void cancel() {
		this.dispose();
	}
	
	private void yesButtonPressed() {
		if(actionType.trim().equals("trash")) {
			new Thread(new TrashItemAction(this, frMain)).start();
		} else if(actionType.trim().equals("hide")) {
			new Thread(new HideItemAction(this,frMain)).start();
		}
	}
}