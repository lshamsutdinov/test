package app.gui;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import app.models.Zayavka;
import app.values.Constants;
import app.values.DocRepository;

public class DialogZayavka extends DialogCommonModified {

	public DialogZayavka(String title, int width, int height, SpecJTextArea jtxtAreaMain) {
		super(title, width, height, jtxtAreaMain);

	}

	private SpecJLabel lblKontragent;
	private SpecJLabel lblKomissiya;

	private SpecJTextField tfKontragent;
	private SpecJTextField tfKomissiya;

	protected void initComponents() {

		super.initComponents();

		lblKontragent = new SpecJLabel(Constants.LBL_KONTRAGENT);
		lblKomissiya = new SpecJLabel(Constants.LBL_KOMISSIYA);

		tfKontragent = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
		tfKomissiya = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

		panelTextFields.add(lblKontragent);
		panelTextFields.add(tfKontragent);
		panelTextFields.add(lblKomissiya);
		panelTextFields.add(tfKomissiya);

	}

	// Метод для заполнения данными "SpecJTextArea jtxtAreaMain" в фоновом
	// потоке.
	protected void fillTextArea() {

		// Отображение индикатора процесса.
		progress.setVisible(true);

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

				// Цикл с задержкой для имитация загрузки.
				for (int i = 0; i < 5; i++) {
					Thread.sleep(300);
				}

				Zayavka zayavka = new Zayavka();
				zayavka.setNumber(tfNumber.getText());
				zayavka.setDate(new Date(dateFromDatePicker));
				zayavka.setUser(tfUser.getText());
				zayavka.setSum(Double.parseDouble(tfSum.getText()));
				zayavka.setValuta(tfValuta.getText());
				zayavka.setKursValuty(Double.parseDouble(tfKursValuty.getText()));
				zayavka.setKontragent(tfKontragent.getText());
				zayavka.setKomissiya(Double.parseDouble(tfKomissiya.getText()));

				DocRepository.getDocRepositoryInstance().add(zayavka);

				StringBuilder sb = new StringBuilder();
				sb.append(Constants.ZAYAVKA + " от " + dateFormat.format(zayavka.getDate()) + " номер " + zayavka.getNumber() + "\n");

				jtxtAreaMain.append(sb.toString());

				// jtxtAreaMain.update(jtxtAreaMain.getGraphics());

				return null;

			}

			// Метод выполняется в EDT(Event Dispatch Thread - основной поток
			// в котором выполняется gui swing) после завершения работы метода
			// doInBackground(), который выполняется в фоновом потоке.
			@Override
			protected void done() {

				// Скрытие индикатора процесса поиска.
				progress.setVisible(false);

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
