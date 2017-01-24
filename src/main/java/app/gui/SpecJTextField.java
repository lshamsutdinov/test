package app.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class SpecJTextField extends JTextField {

	public SpecJTextField(int columns) {
		super.setColumns(columns);
		Font font = new Font("Courier", Font.PLAIN, 14);
		super.setFont(font);
		super.setForeground(Color.BLACK);
	}

	public SpecJTextField(String text, int columns) {
		this(columns);
		super.setText(text);
	}

	public SpecJTextField(int columns, Color textColor) {
		super.setColumns(columns);
		Font font = new Font("Courier", Font.PLAIN, 14);
		super.setFont(font);
		super.setForeground(textColor);
	}
}
