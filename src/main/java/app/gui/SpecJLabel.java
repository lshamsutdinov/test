package app.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SpecJLabel extends JLabel {

	public SpecJLabel(String text) {
		super(text);
		super.setFont(new Font("Courier", Font.BOLD, 14));
		super.setForeground(Color.BLACK);
	}

}
