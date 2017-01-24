package app.gui;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import app.models.Platezhka;
import app.values.Constants;
import app.values.DocRepository;

public class DialogPlatezhka extends DialogCommon {

	public DialogPlatezhka(String title, int width, int height, SpecJTextArea jtxtAreaMain) {
		super(title, width, height, jtxtAreaMain);

	}

	private SpecJLabel lblSotrudink;

	private SpecJTextField tfSotrudink;

	protected void initComponents() {

		super.initComponents();

		lblSotrudink = new SpecJLabel(Constants.LBL_SOTRUDNIK);

		tfSotrudink = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

		panelTextFields.add(lblSotrudink);
		panelTextFields.add(tfSotrudink);

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

				Platezhka platezhka = new Platezhka();
				platezhka.setNumber(tfNumber.getText());
				platezhka.setDate(new Date(dateFromDatePicker));
				platezhka.setUser(tfUser.getText());
				platezhka.setSum(Double.parseDouble(tfSum.getText()));
				platezhka.setSotrudink(tfSotrudink.getText());

				DocRepository.getDocRepositoryInstance().add(platezhka);

				StringBuilder sb = new StringBuilder();
				sb.append(Constants.PLATEZHKA + " от " + dateFormat.format(platezhka.getDate()) + " номер " + platezhka.getNumber() + "\n");

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
