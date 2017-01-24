package app.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class SpecJTextArea extends JTextArea {

	public SpecJTextArea() {
		super();
		commonSettings();
	}

	public SpecJTextArea(int rows, int columns) {
		super(rows, columns);
		commonSettings();
	}

	public SpecJTextArea(String text, int rows, int columns) {
		super(text, rows, columns);
		commonSettings();
	}

	private void commonSettings() {
		Font font = new Font("Courier", Font.PLAIN, 14);
		super.setFont(font);
		super.setForeground(Color.BLACK);

		// Количестов пробелов в одном Tab'е.
		super.setTabSize(5);

		// Параметры переноса слов
		super.setLineWrap(true);
		super.setWrapStyleWord(true);

		super.setEditable(false);
		super.setFocusable(false);
	}

}
