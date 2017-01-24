package app.operations;

import javax.swing.JOptionPane;

public class CommonMethods {

	public static void showErrorDialog(String msg, String title, Exception e) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}

	public static void showErrorDialog(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	}

}
