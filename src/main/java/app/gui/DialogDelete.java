package app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXTable;

import app.models.BaseModel;
import app.models.DeleteTableModel;
import app.models.Nakladnaya;
import app.models.Platezhka;
import app.models.Zayavka;
import app.values.Constants;
import app.values.DocRepository;

public class DialogDelete extends SpecJDialog {

	public DialogDelete(String title, int width, int height, SpecJTextArea jtxtAreaMain) {
		super(title, width, height);

		this.jtxtAreaMain = jtxtAreaMain;

		dateFormat = new SimpleDateFormat("dd.MM.yyyy");

		initComponents();
		addListeners();

		super.setMinimumSize(new Dimension(450, 350));
		super.setResizable(false);

	}

	private SimpleDateFormat dateFormat;
	private SpecJTextArea jtxtAreaMain;

	private JScrollPane scrollPaneTxtArea;
	private JXTable tableDel;

	private SpecJButton btnOk;
	protected SpecJButton btnCancel;

	private SpecJPanel panelTable;
	private SpecJPanel panelButton;

	private void initComponents() {

		tableDel = new JXTable();
		tableDel.setModel(new DeleteTableModel(DocRepository.getDocRepositoryInstance()));
		tableDel.setAutoResizeMode(JXTable.AUTO_RESIZE_LAST_COLUMN);
		tableDel.getColumnModel().getColumn(0).setMinWidth(300);
		tableDel.getColumnModel().getColumn(1).setMinWidth(50);
		tableDel.setPreferredScrollableViewportSize(new Dimension(400, 230));
		scrollPaneTxtArea = new JScrollPane(tableDel);
		scrollPaneTxtArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTxtArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		btnOk = new SpecJButton("OK");
		btnCancel = new SpecJButton(Constants.BTN_LABEL_CANCEL);

		panelTable = new SpecJPanel("panelTable", 450, 300);
		panelTable.setMinimumSize(new Dimension(450, 300));
		panelTable.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		panelTable.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT, Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

		panelButton = new SpecJPanel("panelButton", 450, 50);
		panelButton.setMinimumSize(new Dimension(450, 50));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		panelButton.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT, Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

		panelTable.add(scrollPaneTxtArea);

		panelButton.add(btnOk);
		panelButton.add(btnCancel);

		this.getContentPane().add(panelTable, BorderLayout.CENTER);
		this.getContentPane().add(panelButton, BorderLayout.SOUTH);

	}

	private void addListeners() {

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tableAction();
			}

		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

	}

	private void tableAction() {

		// Создание, реализация и запуск объекта фонового потока.
		//
		// "new SwingWorker<Void, Void>() {...}" - объект для запуска фонового
		// потока.
		// "new SwingWorker<Void, Void>() {...}.execute()" - запуск фонового
		// потока.
		new SwingWorker<Void, Void>() {

			// Метод выполняется в фоновом потоке.
			@Override
			protected Void doInBackground() throws Exception {

				jtxtAreaMain.setText("");

				ArrayList<BaseModel> newRepository = new ArrayList<BaseModel>();
				newRepository.clear();

				for (BaseModel doc : DocRepository.getDocRepositoryInstance()) {

					if (!doc.isForDel()) {
						newRepository.add(doc);
					}
				}

				DocRepository.getDocRepositoryInstance().clear();
				DocRepository.getDocRepositoryInstance().addAll(newRepository);

				StringBuilder sbNew = new StringBuilder();
				for (BaseModel docNew : DocRepository.getDocRepositoryInstance()) {
					if (docNew instanceof Nakladnaya) {
						sbNew.append(Constants.NAKLADNAYA + " от " + dateFormat.format(docNew.getDate()) + " номер " + docNew.getNumber() + "\n");
					} else if (docNew instanceof Platezhka) {
						sbNew.append(Constants.PLATEZHKA + " от " + dateFormat.format(docNew.getDate()) + " номер " + docNew.getNumber() + "\n");
					} else if (docNew instanceof Zayavka) {
						sbNew.append(Constants.ZAYAVKA + " от " + dateFormat.format(docNew.getDate()) + " номер " + docNew.getNumber() + "\n");
					}
				}
				jtxtAreaMain.append(sbNew.toString());

				return null;

			}

			// Метод выполняется в EDT(Event Dispatch Thread - основной поток
			// в котором выполняется gui swing) после завершения работы метода
			// doInBackground(), который выполняется в фоновом потоке.
			@Override
			protected void done() {

				try {

					// Спецметод используется для получения всех ошибок
					// "SwingWorker'а",
					// который выполняется в фоновом потоке.
					get();

					dispose();

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} catch (ExecutionException ex) {

					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ошибка: " + ex.getCause().getMessage(), "Ошибка программы", JOptionPane.ERROR_MESSAGE);
				}

			}

		}.execute();

	}

}
