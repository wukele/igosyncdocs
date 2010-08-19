/**
 * @(#)FaceRunner.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywei.igosyncdocs.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public class FaceRunner {

	public static void run(JFrame frame, Dimension size, String title,boolean isCenter) {
		frame.setSize(size);
		if (title != null)
			frame.setTitle(title);
		if (isCenter)
			setComponentLocationCenterScreen(frame);
		frame.setVisible(true);
	}

	public static void run(JDialog dialog, Dimension size, String title,
			boolean isCenter) {
		dialog.setSize(size);
		if (title != null)
			dialog.setTitle(title);
		if (isCenter)
			setComponentLocationCenterScreen(dialog);
		dialog.setVisible(true);
	}

	public static void setComponentLocationCenterScreen(Component component) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		double newHeight = (screen.getHeight() - component.getHeight()) / 2;
		double newWidth = (screen.getWidth() - component.getWidth()) / 2;
		component.setLocation(new Point((int) newWidth, (int) newHeight-20));
	}
}
