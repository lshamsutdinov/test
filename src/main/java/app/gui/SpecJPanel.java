package app.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class SpecJPanel extends JPanel {

	public SpecJPanel(String name, int width, int height) {
		super.setName(name);
		super.setPreferredSize(new Dimension(width, height));
		// super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// super.setBorder(BorderFactory.createEtchedBorder());
	}

	public SpecJPanel(String name, int width, int height, Component comp) {
		this(name, width, height);
		super.add(comp);
	}

	public SpecJPanel(String name, int width, int height, LayoutManager layout) {
		this(name, width, height);
		super.setLayout(layout);
	}

	public SpecJPanel(String name, int width, int height, Component comp, LayoutManager layout) {
		this(name, width, height);
		super.setLayout(layout);
		super.add(comp);
	}
}