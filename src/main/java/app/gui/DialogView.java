package app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import app.models.BaseModel;
import app.models.BaseModelModified;
import app.models.Nakladnaya;
import app.models.Platezhka;
import app.models.Zayavka;
import app.values.Constants;
import app.values.DocRepository;

public class DialogView extends SpecJDialog {

	public DialogView(String title, int width, int height, SpecJTextArea jtxtAreaMain) {
		super(title, width, height);

		this.jtxtAreaMain = jtxtAreaMain;

		initComponents();
		addListeners();

		super.setMinimumSize(new Dimension(350, 350));
		super.setResizable(false);

		// dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		dateFormat = new SimpleDateFormat("dd.MM.yyyy");

		fillTextArea();

	}

	private SimpleDateFormat dateFormat;
	private SpecJTextArea jtxtAreaMain;

	private JScrollPane scrollPaneTxtArea;
	private SpecJTextArea jtxtArea;

	private SpecJButton btnOk;

	private SpecJPanel panelTextArea;
	private SpecJPanel panelButton;

	private void initComponents() {

		jtxtArea = new SpecJTextArea(15, 28);
		scrollPaneTxtArea = new JScrollPane(jtxtArea);
		scrollPaneTxtArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTxtArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		btnOk = new SpecJButton("OK");

		panelTextArea = new SpecJPanel("panelTextArea", 350, 300);
		panelTextArea.setMinimumSize(new Dimension(350, 300));
		panelTextArea.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		panelTextArea.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT, Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

		panelButton = new SpecJPanel("panelButton", 350, 50);
		panelButton.setMinimumSize(new Dimension(350, 50));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		panelButton.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT, Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

		panelTextArea.add(scrollPaneTxtArea);

		panelButton.add(btnOk);

		this.getContentPane().add(panelTextArea, BorderLayout.CENTER);
		this.getContentPane().add(panelButton, BorderLayout.SOUTH);

	}

	private void addListeners() {

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

	}

	private void fillTextArea() {

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

				//// Чтение и вывод в JTextArea диалогового окна значений
				//// документа из первой строки.

				BaseModel doc = DocRepository.getDocRepositoryInstance().remove(0);

				StringBuilder sb = new StringBuilder();
				if (doc instanceof Nakladnaya) {
					sb.append(Constants.LBL_NUMBER + ": " + doc.getNumber() + "\n" + Constants.LBL_DATE + ": " + dateFormat.format(doc.getDate()) + "\n" + Constants.LBL_USER + ": " + doc.getUser() + "\n" + Constants.LBL_SUM + ": " + doc.getSum()
							+ "\n" + Constants.LBL_VALUTA + ": " + ((BaseModelModified) doc).getValuta() + "\n" + Constants.LBL_KURS_VALUTY + ": " + ((BaseModelModified) doc).getKursValuty() + "\n" + Constants.LBL_TOVAR + ": "
							+ ((Nakladnaya) doc).getTovar() + "\n" + Constants.LBL_KOLICHESTVO + ": " + ((Nakladnaya) doc).getKolichestvo() + "\n");
				} else if (doc instanceof Platezhka) {
					sb.append(Constants.LBL_NUMBER + ": " + doc.getNumber() + "\n" + Constants.LBL_DATE + ": " + dateFormat.format(doc.getDate()) + "\n" + Constants.LBL_USER + ": " + doc.getUser() + "\n" + Constants.LBL_SUM + ": " + doc.getSum()
							+ "\n" + Constants.LBL_SOTRUDNIK + ": " + ((Platezhka) doc).getSotrudink() + "\n");
				} else if (doc instanceof Zayavka) {
					sb.append(Constants.LBL_NUMBER + ": " + doc.getNumber() + "\n" + Constants.LBL_DATE + ": " + dateFormat.format(doc.getDate()) + "\n" + Constants.LBL_USER + ": " + doc.getUser() + "\n" + Constants.LBL_SUM + ": " + doc.getSum()
							+ "\n" + Constants.LBL_VALUTA + ": " + ((BaseModelModified) doc).getValuta() + "\n" + Constants.LBL_KURS_VALUTY + ": " + ((BaseModelModified) doc).getKursValuty() + "\n" + Constants.LBL_KONTRAGENT + ": "
							+ ((Zayavka) doc).getKontragent() + "\n" + Constants.LBL_KOMISSIYA + ": " + ((Zayavka) doc).getKomissiya() + "\n");
				}

				jtxtArea.setText("");
				jtxtArea.append(sb.toString());

				// Перенос выведенного из первой строки документа в конец
				// списка JTextArea окна фрейма.

				DocRepository.getDocRepositoryInstance().add(doc);

				jtxtAreaMain.setText("");
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
