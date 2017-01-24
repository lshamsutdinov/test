package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import app.gui.SpecJButton;
import app.gui.SpecJFrame;
import app.gui.SpecJPanel;
import app.gui.SpecJTextArea;
import app.listeners.ButtonActionListener;
import app.operations.CommonMethods;
import app.values.Constants;

public class Start extends Constants {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			CommonMethods.showErrorDialog("Ошибка при работе программы!", "Ошибка программы", e);
		} catch (InstantiationException e) {
			CommonMethods.showErrorDialog("Ошибка при работе программы!", "Ошибка программы", e);
		} catch (IllegalAccessException e) {
			CommonMethods.showErrorDialog("Ошибка при работе программы!", "Ошибка программы", e);
		} catch (UnsupportedLookAndFeelException e) {
			CommonMethods.showErrorDialog("Ошибка при работе программы!", "Ошибка программы", e);
		}

		JFrame.setDefaultLookAndFeelDecorated(true);

		Start start = new Start();

		start.createTextArea();
		start.createButtons();
		start.createPanels();

		start.createFrame();

		start.addButtonListeners();

		/*
		 * Создание и отображение формы.
		 * 
		 * Благодаря запуску через
		 * "java.awt.EventQueue.invokeLater(new Runnable() {...}" создание и
		 * отображение формы произойдет после того, как все ожидаемые события
		 * обработаются, т.е. фрейм создасться и отобразится, когда все ресурсы
		 * будут готовы. Это необходимо, чтобы все элементы гарантированно
		 * отобразились в окне. (Если сделать "frame.setVisible(true)" из
		 * главного потока, то есть вероятность, что какой-либо элемент не
		 * отобразиться в окне).
		 * 
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});

	}

	private JScrollPane scrollPaneTxtAreaMain;
	private SpecJTextArea jtxtAreaMain;

	private SpecJButton btnNakladnaya;
	private SpecJButton btnPlatezhka;
	private SpecJButton btnZayavka;
	private SpecJButton btnSave;
	private SpecJButton btnLoad;
	private SpecJButton btnView;
	private SpecJButton btnExit;
	private SpecJButton btnDelete;

	private SpecJPanel panelTextArea;
	private SpecJPanel panelButton;
	private static SpecJFrame frame;

	private void createTextArea() {

		jtxtAreaMain = new SpecJTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
		scrollPaneTxtAreaMain = new JScrollPane(jtxtAreaMain);

		scrollPaneTxtAreaMain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTxtAreaMain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	}

	private void createButtons() {

		btnNakladnaya = new SpecJButton(BTN_LABEL_NAKLADNAYA);
		btnPlatezhka = new SpecJButton(BTN_LABEL_PLATEZHKA);
		btnZayavka = new SpecJButton(BTN_LABEL_ZAYAVKA);
		btnSave = new SpecJButton(BTN_LABEL_SAVE);
		btnLoad = new SpecJButton(BTN_LABEL_LOAD);
		btnView = new SpecJButton(BTN_LABEL_VIEW);
		btnExit = new SpecJButton(BTN_LABEL_EXIT);
		btnDelete = new SpecJButton(BTN_LABEL_DELETE);

	}

	private void createPanels() {

		panelTextArea = new SpecJPanel("textAreaPanel", PANEL_TEXT_PREF_WIDTH, PANEL_TEXT_PREF_HEIGHT);
		panelTextArea.setMinimumSize(new Dimension(PANEL_TEXT_MIN_WIDTH, PANEL_TEXT_MIN_HEIGHT));
		panelTextArea.setLayout(new BorderLayout(PANEL_TEXT_BORDER_LAYOUT_HORIZONTAL_GAP, PANEL_TEXT_BORDER_LAYOUT_VERTICAL_GAP));
		panelTextArea.setBorder(new EmptyBorder(PANEL_EMPTY_BORDER_TOP, PANEL_EMPTY_BORDER_LEFT, PANEL_EMPTY_BORDER_BOTTOM, PANEL_EMPTY_BORDER_RIGHT));

		panelButton = new SpecJPanel("buttonPanel", PANEL_BTN_PREF_WIDTH, PANEL_BTN_PREF_HEIGHT);
		panelButton.setMinimumSize(new Dimension(PANEL_BTN_MIN_WIDTH, PANEL_BTN_MIN_HEIGHT));
		panelButton.setLayout(new GridLayout(PANEL_BTN_GRID_LAYOUT_ROWS, PANEL_BTN_GRID_LAYOUT_COLUMNS, PANEL_BTN_GRID_LAYOUT_HORIZONTAL_GAP, PANEL_BTN_GRID_LAYOUT_VERTICAL_GAP));
		panelButton.setBorder(new EmptyBorder(PANEL_EMPTY_BORDER_TOP, PANEL_EMPTY_BORDER_LEFT, PANEL_EMPTY_BORDER_BOTTOM, PANEL_EMPTY_BORDER_RIGHT));

		panelTextArea.add(scrollPaneTxtAreaMain, BorderLayout.CENTER);

		panelButton.add(btnNakladnaya);
		panelButton.add(btnPlatezhka);
		panelButton.add(btnZayavka);
		panelButton.add(btnSave);
		panelButton.add(btnLoad);
		panelButton.add(btnView);
		panelButton.add(btnExit);
		panelButton.add(btnDelete);

	}

	private void createFrame() {
		frame = new SpecJFrame("Test", FRAME_PREF_WIDTH, FRAME_PREF_HEIGHT, new BorderLayout(2, 2));
		frame.setMinimumSize(new Dimension(FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT));

		frame.getContentPane().add(panelTextArea, BorderLayout.CENTER);
		frame.getContentPane().add(panelButton, BorderLayout.EAST);

	}

	private void addButtonListeners() {

		ButtonActionListener buttonListener = new ButtonActionListener(this.jtxtAreaMain);

		this.btnNakladnaya.addActionListener(buttonListener);
		this.btnPlatezhka.addActionListener(buttonListener);
		this.btnZayavka.addActionListener(buttonListener);
		this.btnSave.addActionListener(buttonListener);
		this.btnLoad.addActionListener(buttonListener);
		this.btnView.addActionListener(buttonListener);
		this.btnExit.addActionListener(buttonListener);
		this.btnDelete.addActionListener(buttonListener);

	}

}
