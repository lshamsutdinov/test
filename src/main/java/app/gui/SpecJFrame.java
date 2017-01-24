package app.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;

public class SpecJFrame extends JFrame {

	public SpecJFrame(String title, int width, int height) {
		super(title);
		super.setSize(width, height);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new FlowLayout());

	}

	public SpecJFrame(String title, int width, int height, Component comp) {
		this(title, width, height);
		super.getContentPane().add(comp);
	}

	public SpecJFrame(String title, int width, int height, LayoutManager layout) {
		super(title);
		super.setSize(width, height);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(layout);

	}
}