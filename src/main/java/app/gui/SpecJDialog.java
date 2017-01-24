package app.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JDialog;

import app.values.Constants;

public class SpecJDialog extends JDialog {

	public SpecJDialog(String title, int width, int height) {
		super.setTitle(title);
		super.setSize(width, height);
		super.setModal(true);
		super.setLocationRelativeTo(null);
		super.setLayout(new BorderLayout(Constants.PANEL_TEXT_BORDER_LAYOUT_HORIZONTAL_GAP, Constants.PANEL_TEXT_BORDER_LAYOUT_VERTICAL_GAP));

		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public SpecJDialog(String title, int width, int height, Component comp) {
		this(title, width, height);
		super.getContentPane().add(comp);
	}

	public SpecJDialog(String title, int width, int height, LayoutManager layout) {
		super.setTitle(title);
		super.setSize(width, height);
		super.setModal(true);
		super.setLocationRelativeTo(null);
		super.setLayout(layout);
		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
