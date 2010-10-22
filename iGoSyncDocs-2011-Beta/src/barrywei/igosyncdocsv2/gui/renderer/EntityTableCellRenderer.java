/**
 * @(#)EntityTableCellRenderer.java Oct 21, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocsv2.gui.renderer;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableCellRenderer;

import barrywei.igosyncdocsv2.bean.SystemRuntime;
import barrywei.igosyncdocsv2.resource.ImageResource;

import com.google.gdata.data.docs.DocumentListEntry;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 21, 2010
 * @since JDK1.6
 */
public class EntityTableCellRenderer extends JLabel implements TableCellRenderer{

	private static final long serialVersionUID = -5369190951366653081L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		if(value instanceof DocumentListEntry) {
			DocumentListEntry entry = (DocumentListEntry)value;
			if(column == 0) {
				//star icon
				if(entry.isStarred())
					setIcon(ImageResource.getIcon("stared.png"));
				else
					setIcon(ImageResource.getIcon("stared-not.png"));
			}else if(column == 1) {
				//get type
				setIcon(getValiedIcon(entry));
			}else if(column == 2) {
				setText(entry.getTitle().getPlainText());
			}else if(column == 3) {
				setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(entry.getUpdated().getValue())));
			}
		}//end of if
		
		if (isSelected) {
			setBackground(table.getSelectionBackground());
			setForeground(table.getSelectionForeground());
		} else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
		}

		setEnabled(table.isEnabled());
		setFont(table.getFont());
		setOpaque(true);
		setIconTextGap(10);			
		return this;
	}//end of method
	
	private Icon getValiedIcon(DocumentListEntry entry) {
		if (entry.getType().equals("document"))
			return ImageResource.getIcon("doc.png");
		else if (entry.getType().equals("spreadsheet"))
			return ImageResource.getIcon("spreadsheet.png");
		else if (entry.getType().equals("presentation"))
			return ImageResource.getIcon("presentation.png");
		else {
			String fileExtensiton = entry.getType();
			String path = SystemRuntime.Settings.App_Data_Home+File.separator+"iGoSyncDocs 2011 beta temp."+fileExtensiton;
			File file = new File(path);
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
			if (icon instanceof ImageIcon) {
				ImageIcon image = (ImageIcon) icon;
				Image temp = image.getImage().getScaledInstance(18, 18,
						Image.SCALE_SMOOTH);
				icon = new ImageIcon(temp);
			}
			return icon;
		}
	}//end of method	
}//end of class
