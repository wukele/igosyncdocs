/**
 * @(#)MainFrame.java Oct 12, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import barrywei.igosyncdocsv2.action.SystemTrayAction;
import barrywei.igosyncdocsv2.bean.SystemRuntime;
import barrywei.igosyncdocsv2.gui.panel.AllItemPanel;
import barrywei.igosyncdocsv2.gui.panel.MyFolderPanel;
import barrywei.igosyncdocsv2.resource.LanguageResource;

import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JSeparator;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 12, 2010
 * @since JDK1.6
 */
public class MainFrame extends JFrame {
	
	public MainFrame() {
		initComponents();
	}

	private void initComponents() {
		setTitle(LanguageResource.getStringValue("app.title"));
		setMinimumSize(new Dimension(950, 660));
		setSize(new Dimension(950, 660));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnlMain = new JPanel();
		pnlMain.setBorder(new EmptyBorder(0, 0, 5, 0));
		pnlMain.setLayout(new BorderLayout());
		setContentPane(pnlMain);
		
		menuBar = new JMenuBar();
		menuBar.setName("menuBar");
		setJMenuBar(menuBar);
		
		mnFile = new JMenu(LanguageResource.getStringValue("main.menu.file"));
		mnFile.setMnemonic('F');
		mnFile.setName("mnFile");
		menuBar.add(mnFile);
		
		miNewDocument = new JMenuItem(LanguageResource.getStringValue("main.menuitem.new_doc"));
		miNewDocument.setMnemonic('D');
		miNewDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miNewDocument.setName("miNewDocument");
		mnFile.add(miNewDocument);
		
		miSpreadsheet = new JMenuItem(LanguageResource.getStringValue("main.menuitem.new_spre"));
		miSpreadsheet.setMnemonic('S');
		miSpreadsheet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miSpreadsheet.setName("miSpreadsheet");
		mnFile.add(miSpreadsheet);
		
		miPresentation = new JMenuItem(LanguageResource.getStringValue("main.menuitem.new_pres"));
		miPresentation.setMnemonic('P');
		miPresentation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miPresentation.setName("miPresentation");
		mnFile.add(miPresentation);
		
		separator = new JSeparator();
		separator.setName("separator");
		mnFile.add(separator);
		
		miExit = new JMenuItem(LanguageResource.getStringValue("main.menuitem.exit"));
		miExit.setMnemonic('x');
		miExit.setName("miExit");
		mnFile.add(miExit);
		
		mnSetting = new JMenu(LanguageResource.getStringValue("main.menu.setting"));
		mnSetting.setMnemonic('S');
		mnSetting.setName("mnSetting");
		menuBar.add(mnSetting);
		
		mnSearch = new JMenu(LanguageResource.getStringValue("main.menu.search"));
		mnSearch.setMnemonic('a');
		mnSearch.setName("mnSearch");
		menuBar.add(mnSearch);
		
		mnHelp = new JMenu(LanguageResource.getStringValue("main.menu.help"));
		mnHelp.setMnemonic('H');
		mnHelp.setName("mnHelp");
		menuBar.add(mnHelp);
		
		pnlStatusbar = new JPanel();
		pnlStatusbar.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnlStatusbar.setPreferredSize(new Dimension(800, 20));
		pnlStatusbar.setName("pnlStatusbar");
		pnlMain.add(pnlStatusbar, BorderLayout.SOUTH);
		pnlStatusbar.setLayout(new BorderLayout(5, 3));
		
		String strConnectedTo = LanguageResource.getStringValue("main.connected_to");
		lblConnectMessage = new JLabel(strConnectedTo.replace("{1}",SystemRuntime.Settings.UserName));
		lblConnectMessage.setName("lblConnectMessage");
		pnlStatusbar.add(lblConnectMessage, BorderLayout.WEST);
		
		pnlStatusbarRight = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlStatusbarRight.getLayout();
		flowLayout_2.setVgap(3);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnlStatusbarRight.setPreferredSize(new Dimension(350, 15));
		pnlStatusbarRight.setName("pnlStatusbarRight");
		pnlStatusbar.add(pnlStatusbarRight, BorderLayout.EAST);
		
		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(150, 15));
		pnlStatusbarRight.add(progressBar);
		progressBar.setName("progressBar");
		
		lblProcessMessage = new JLabel("no schedule right now.");
		lblProcessMessage.setPreferredSize(new Dimension(180, 15));
		lblProcessMessage.setName("lblProcessMessage");
		pnlStatusbarRight.add(lblProcessMessage);
		
		pnlToolbar = new JPanel();
		pnlToolbar.setPreferredSize(new Dimension(10, 50));
		pnlToolbar.setName("pnlToolbar");
		pnlMain.add(pnlToolbar, BorderLayout.NORTH);
		pnlToolbar.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setName("panel");
		pnlToolbar.add(panel, BorderLayout.WEST);
		
		btnnewButton = new JButton("<html>New <br>button</html>");
		panel.add(btnnewButton);
		btnnewButton.setPreferredSize(new Dimension(60, 40));
		btnnewButton.setName("btnnewButton");
		
		button = new JButton("<html>New <br>button</html>");
		button.setPreferredSize(new Dimension(60, 40));
		button.setName("btnnewButton");
		panel.add(button);
		
		button_1 = new JButton("<html>New <br>button</html>");
		button_1.setPreferredSize(new Dimension(60, 40));
		button_1.setName("btnnewButton");
		panel.add(button_1);
		
		button_2 = new JButton("<html>New <br>button</html>");
		button_2.setPreferredSize(new Dimension(60, 40));
		button_2.setName("btnnewButton");
		panel.add(button_2);
		
		button_3 = new JButton("<html>New <br>button</html>");
		button_3.setPreferredSize(new Dimension(60, 40));
		button_3.setName("btnnewButton");
		panel.add(button_3);
		
		button_7 = new JButton("<html>New <br>button</html>");
		button_7.setPreferredSize(new Dimension(60, 40));
		button_7.setName("btnnewButton");
		panel.add(button_7);
		
		button_6 = new JButton("<html>New <br>button</html>");
		button_6.setPreferredSize(new Dimension(60, 40));
		button_6.setName("btnnewButton");
		panel.add(button_6);
		
		button_5 = new JButton("<html>New <br>button</html>");
		button_5.setPreferredSize(new Dimension(60, 40));
		button_5.setName("btnnewButton");
		panel.add(button_5);
		
		button_4 = new JButton("<html>New <br>button</html>");
		button_4.setPreferredSize(new Dimension(60, 40));
		button_4.setName("btnnewButton");
		panel.add(button_4);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_1.setName("panel_1");
		pnlToolbar.add(panel_1);
		
		txtSearch = new JTextField();
		txtSearch.setName("txtSearch");
		panel_1.add(txtSearch);
		txtSearch.setColumns(30);
		
		btnSearch = new JButton("Search");
		btnSearch.setName("btnSearch");
		panel_1.add(btnSearch);
		
		pnlTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pnlTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pnlTabbedPane.setName("pnlTabbedPane");
		pnlMain.add(pnlTabbedPane, BorderLayout.CENTER);
		
		pnlFolder = new MyFolderPanel();
		pnlFolder.setResizeWeight(0.2);
		pnlFolder.setDividerLocation(170);
		pnlFolder.setName("pnlFolder");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_folder"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/hidden.png")), pnlFolder, null);
		
		pnlAllItem = new AllItemPanel();
		pnlAllItem.setName("pnlAllItem");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_allitem"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/all.png")), pnlAllItem, null);
		
		pnlDocument = new JPanel();
		pnlDocument.setName("pnlDocument");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_document"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/doc.png")), pnlDocument, null);
		
		pnlPresentation = new JPanel();
		pnlPresentation.setName("pnlPresentation");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_presentation"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/presentation.png")), pnlPresentation, null);
		
		pnlSpreadsheet = new JPanel();
		pnlSpreadsheet.setName("pnlSpreadsheet");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_spreadsheet"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/spreadsheet.png")), pnlSpreadsheet, null);
		
		pnlOtherfiles = new JPanel();
		pnlOtherfiles.setName("pnlOtherfiles");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_otherfiles"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/all.png")), pnlOtherfiles, null);
		
		pnlHidden = new JPanel();
		pnlHidden.setName("pnlHidden");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_hidden"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/hidden.png")), pnlHidden, null);
		
		pnlStared = new JPanel();
		pnlStared.setName("pnlStared");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_stared"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/stared.png")), pnlStared, null);
		
		pnlTrashed = new JPanel();
		pnlTrashed.setName("pnlTrashed");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_trashed"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/trashed.png")), pnlTrashed, null);
		
		pnlSharedWithMe = new JPanel();
		pnlSharedWithMe.setName("pnlSharedWithMe");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_sharedwithme"), new ImageIcon(MainFrame.class.getResource("/barrywei/igosyncdocsv2/resource/image/doc.png")), pnlSharedWithMe, null);
		
		pnlTabbedPane.setSelectedIndex(1);
		
		
		//event handler
		addWindowListener(new SystemTrayAction(this));
		
	}

	private static final long serialVersionUID = -9147881941303957656L;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnSetting;
	private JMenu mnSearch;
	private JMenu mnHelp;
	private JPanel pnlStatusbar;
	private JMenuItem miNewDocument;
	private JMenuItem miSpreadsheet;
	private JMenuItem miPresentation;
	private JMenuItem miExit;
	private JPanel pnlMain;
	private JPanel pnlToolbar;
	private JButton btnnewButton;
	private JTabbedPane pnlTabbedPane;
	private JSplitPane pnlFolder;
	private JPanel pnlAllItem;
	private JPanel pnlDocument;
	private JPanel pnlPresentation;
	private JPanel pnlSpreadsheet;
	private JPanel pnlOtherfiles;
	private JPanel pnlHidden;
	private JPanel pnlStared;
	private JPanel pnlTrashed;
	private JLabel lblConnectMessage;
	private JProgressBar progressBar;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JSeparator separator;
	private JPanel pnlSharedWithMe;
	private JPanel pnlStatusbarRight;
	private JLabel lblProcessMessage;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
}
