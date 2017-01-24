package app.gui;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import app.models.Nakladnaya;
import app.values.Constants;
import app.values.DocRepository;

public class DialogNakladnaya extends DialogCommonModified {

	public DialogNakladnaya(String title, int width, int height, SpecJTextArea jtxtAreaMain) {
		super(title, width, height, jtxtAreaMain);

	}

	private SpecJLabel lblTovar;
	private SpecJLabel lblKolichestvo;

	private SpecJTextField tfTovar;
	private SpecJTextField tfKolichestvo;

	protected void initComponents() {

		super.initComponents();

		lblTovar = new SpecJLabel(Constants.LBL_TOVAR);
		lblKolichestvo = new SpecJLabel(Constants.LBL_KOLICHESTVO);

		tfTovar = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
		tfKolichestvo = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

		panelTextFields.add(lblTovar);
		panelTextFields.add(tfTovar);
		panelTextFields.add(lblKolichestvo);
		panelTextFields.add(tfKolichestvo);

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

				Nakladnaya nakladnaya = new Nakladnaya();
				nakladnaya.setNumber(tfNumber.getText());
				nakladnaya.setDate(new Date(dateFromDatePicker));
				nakladnaya.setUser(tfUser.getText());
				nakladnaya.setSum(Double.parseDouble(tfSum.getText()));
				nakladnaya.setValuta(tfValuta.getText());
				nakladnaya.setKursValuty(Double.parseDouble(tfKursValuty.getText()));
				nakladnaya.setTovar(tfTovar.getText());
				nakladnaya.setKolichestvo(Double.parseDouble(tfKolichestvo.getText()));

				DocRepository.getDocRepositoryInstance().add(nakladnaya);

				StringBuilder sb = new StringBuilder();
				sb.append(Constants.NAKLADNAYA + " от " + dateFormat.format(nakladnaya.getDate()) + " номер " + nakladnaya.getNumber() + "\n");

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
