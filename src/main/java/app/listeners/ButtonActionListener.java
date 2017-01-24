package app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import app.gui.DialogDelete;
import app.gui.DialogNakladnaya;
import app.gui.DialogPlatezhka;
import app.gui.DialogView;
import app.gui.DialogZayavka;
import app.gui.SpecJButton;
import app.gui.SpecJTextArea;
import app.models.BaseModel;
import app.models.Nakladnaya;
import app.models.Platezhka;
import app.models.Zayavka;
import app.operations.CommonMethods;
import app.values.Constants;
import app.values.DocRepository;

public class ButtonActionListener implements ActionListener {

	private SpecJTextArea jtxtAreaMain;
	private SimpleDateFormat dateFormat;

	public ButtonActionListener(SpecJTextArea jtxtAreaMain) {
		this.jtxtAreaMain = jtxtAreaMain;
		dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	}

	public void actionPerformed(ActionEvent e) {

		if (!(e.getSource() instanceof SpecJButton)) {
			return;
		}

		SpecJButton btn = (SpecJButton) e.getSource();

		String btnTxt = btn.getActionCommand();

		switch (btnTxt) {

		case Constants.BTN_LABEL_NAKLADNAYA:
			DialogNakladnaya dialogNakladnaya = new DialogNakladnaya(Constants.BTN_LABEL_NAKLADNAYA, 450, 350, jtxtAreaMain);
			dialogNakladnaya.setVisible(true);
			break;

		case Constants.BTN_LABEL_PLATEZHKA:
			DialogPlatezhka dialogPlatezhka = new DialogPlatezhka(Constants.BTN_LABEL_PLATEZHKA, 450, 350, jtxtAreaMain);
			dialogPlatezhka.setVisible(true);
			break;

		case Constants.BTN_LABEL_ZAYAVKA:
			DialogZayavka dialogZayavka = new DialogZayavka(Constants.BTN_LABEL_ZAYAVKA, 450, 350, jtxtAreaMain);
			dialogZayavka.setVisible(true);
			break;

		case Constants.BTN_LABEL_SAVE:
			if (jtxtAreaMain.getText().equals("")) {
				CommonMethods.showErrorDialog("Текстовое поле не заполнено. Сохранять нечего!", "Ошибка");
				break;
			}
			saveOperation();
			break;

		case Constants.BTN_LABEL_LOAD:
			loadOperation();
			break;

		case Constants.BTN_LABEL_VIEW:
			if (DocRepository.getDocRepositoryInstance().size() <= 0) {
				CommonMethods.showErrorDialog("Текстовое поле не заполнено. Просматривать нечего!", "Ошибка");
				break;
			}
			BaseModel doc = DocRepository.getDocRepositoryInstance().get(0);
			StringBuilder sb = new StringBuilder();
			if (doc instanceof Nakladnaya) {
				sb.append(Constants.NAKLADNAYA);
			} else if (doc instanceof Platezhka) {
				sb.append(Constants.PLATEZHKA);
			} else if (doc instanceof Zayavka) {
				sb.append(Constants.ZAYAVKA);
			}
			DialogView dialogView = new DialogView(sb.toString(), 350, 350, jtxtAreaMain);
			dialogView.setVisible(true);
			break;

		case Constants.BTN_LABEL_EXIT:
			System.exit(0);
			break;

		case Constants.BTN_LABEL_DELETE:
			if (jtxtAreaMain.getText().equals("")) {
				CommonMethods.showErrorDialog("Текстовое поле не заполнено. Удалять нечего!", "Ошибка");
				break;
			}
			DialogDelete dialogDelete = new DialogDelete("Удалить", 450, 350, jtxtAreaMain);
			dialogDelete.setVisible(true);
			break;

		default:
			System.out.println("Нииикак!!!!!!");
			break;
		}

	}

	private void saveOperation() {

		JFileChooser fileopen = new JFileChooser();
		File file = null;
		int ret = fileopen.showDialog(null, "Сохранить файл");
		if (ret == JFileChooser.APPROVE_OPTION) {
			file = fileopen.getSelectedFile();

			try {

				if (!file.exists()) {
					file.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(DocRepository.getDocRepositoryInstance());
				oos.flush();
				oos.close();

			} catch (IOException ex) {
				CommonMethods.showErrorDialog("Ошибка при работе программы!", "Ошибка программы", ex);
			}

			// System.out.println("Сохранение в:" + file.getName());

		}
	}

	@SuppressWarnings("unchecked")
	private void loadOperation() {

		ArrayList<BaseModel> docRepository;

		JFileChooser fileopen = new JFileChooser();
		File file = null;
		int ret = fileopen.showDialog(null, "Открыть файл");
		if (ret == JFileChooser.APPROVE_OPTION) {
			file = fileopen.getSelectedFile();

			try {

				if (!file.exists()) {
					return;
				}

				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oin = new ObjectInputStream(fis);
				docRepository = (ArrayList<BaseModel>) oin.readObject();
				oin.close();

				// DocRepository.getDocRepositoryInstance().clear();
				DocRepository.getDocRepositoryInstance().addAll(docRepository);

				jtxtAreaMain.setText("");
				StringBuilder sb = new StringBuilder();
				for (BaseModel doc : DocRepository.getDocRepositoryInstance()) {
					if (doc instanceof Nakladnaya) {
						sb.append(Constants.NAKLADNAYA + " от " + dateFormat.format(doc.getDate()) + " номер " + doc.getNumber() + "\n");
					} else if (doc instanceof Platezhka) {
						sb.append(Constants.PLATEZHKA + " от " + dateFormat.format(doc.getDate()) + " номер " + doc.getNumber() + "\n");
					} else if (doc instanceof Zayavka) {
						sb.append(Constants.ZAYAVKA + " от " + dateFormat.format(doc.getDate()) + " номер " + doc.getNumber() + "\n");
					}
				}
				jtxtAreaMain.append(sb.toString());

			} catch (IOException ex) {
				CommonMethods.showErrorDialog("Ошибка при работе программы!", "Ошибка программы", ex);
			} catch (ClassNotFoundException ex) {
				CommonMethods.showErrorDialog("Ошибка при работе программы!", "Ошибка программы", ex);
			}

			// System.out.println("Открытие из:" + file.getName());

		}
	}

}
