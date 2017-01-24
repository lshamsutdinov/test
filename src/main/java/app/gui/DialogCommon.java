package app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import app.values.Constants;

public abstract class DialogCommon extends SpecJDialog {

	public DialogCommon(String title, int width, int height, SpecJTextArea jtxtAreaMain) {
		super(title, width, height);

		this.jtxtAreaMain = jtxtAreaMain;

		initComponents();
		addListeners();

		super.setMinimumSize(new Dimension(450, 350));

		// dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	}

	protected SimpleDateFormat dateFormat;

	protected SpecJTextArea jtxtAreaMain;

	protected JProgressBar progress;

	protected SpecJLabel lblNumber;
	protected SpecJLabel lblDate;
	protected SpecJLabel lblUser;
	protected SpecJLabel lblSum;

	protected SpecJTextField tfNumber;
	protected JXDatePicker tfDate;
	protected long dateFromDatePicker;
	protected SpecJTextField tfUser;
	protected SpecJTextField tfSum;

	protected SpecJButton btnAdd;
	protected SpecJButton btnCancel;

	protected SpecJPanel panelTextFields;
	protected SpecJPanel panelButton;

	protected void initComponents() {

		progress = new JProgressBar();
		progress.setIndeterminate(true);
		progress.setVisible(false);

		lblNumber = new SpecJLabel(Constants.LBL_NUMBER);
		lblDate = new SpecJLabel(Constants.LBL_DATE);
		lblUser = new SpecJLabel(Constants.LBL_USER);
		lblSum = new SpecJLabel(Constants.LBL_SUM);

		tfNumber = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
		tfDate = new JXDatePicker();
		tfUser = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
		tfSum = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

		btnAdd = new SpecJButton(Constants.BTN_LABEL_ADD);
		btnCancel = new SpecJButton(Constants.BTN_LABEL_CANCEL);

		panelTextFields = new SpecJPanel("panelTextFields", 450, 300);
		panelTextFields.setMinimumSize(new Dimension(450, 300));
		panelTextFields.setLayout(new GridLayout(8, 2, Constants.PANEL_BTN_GRID_LAYOUT_HORIZONTAL_GAP, Constants.PANEL_BTN_GRID_LAYOUT_VERTICAL_GAP));
		panelTextFields.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT, Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

		panelButton = new SpecJPanel("panelButton", 450, 50);
		panelButton.setMinimumSize(new Dimension(450, 50));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		panelButton.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT, Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

		panelTextFields.add(lblNumber);
		panelTextFields.add(tfNumber);
		panelTextFields.add(lblDate);
		panelTextFields.add(tfDate);
		panelTextFields.add(lblUser);
		panelTextFields.add(tfUser);
		panelTextFields.add(lblSum);
		panelTextFields.add(tfSum);

		panelButton.add(btnAdd);
		panelButton.add(btnCancel);

		this.getContentPane().add(progress, BorderLayout.NORTH);
		this.getContentPane().add(panelTextFields, BorderLayout.CENTER);
		this.getContentPane().add(panelButton, BorderLayout.SOUTH);

	}

	private void addListeners() {

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fillTextArea();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {

						progress.setVisible(true);

						// Цикл с задержкой для имитация каких-либо действий.
						for (int i = 0; i < 5; i++) {
							try {
								Thread.sleep(300);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}

						progress.setVisible(false);

						dispose();

					}
				});

				t.start();

			}

		});

		tfDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dateFromDatePicker = tfDate.getDate().getTime();
			}

		});

	}

	// Метод для заполнения данными "SpecJTextArea jtxtAreaMain" в фоновом
	// потоке.
	protected abstract void fillTextArea();

}
