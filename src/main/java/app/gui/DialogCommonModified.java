package app.gui;

import app.values.Constants;

public abstract class DialogCommonModified extends DialogCommon {

	public DialogCommonModified(String title, int width, int height, SpecJTextArea jtxtAreaMain) {
		super(title, width, height, jtxtAreaMain);
	}

	protected SpecJLabel lblValuta;
	protected SpecJLabel lblKursValuty;

	protected SpecJTextField tfValuta;
	protected SpecJTextField tfKursValuty;

	protected void initComponents() {

		super.initComponents();

		lblValuta = new SpecJLabel(Constants.LBL_VALUTA);
		lblKursValuty = new SpecJLabel(Constants.LBL_KURS_VALUTY);

		tfValuta = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
		tfKursValuty = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

		panelTextFields.add(lblValuta);
		panelTextFields.add(tfValuta);
		panelTextFields.add(lblKursValuty);
		panelTextFields.add(tfKursValuty);

	}

}
