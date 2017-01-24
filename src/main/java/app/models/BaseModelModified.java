package app.models;

public abstract class BaseModelModified extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2990715878413585912L;

	private String valuta;
	private double kursValuty;

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public double getKursValuty() {
		return kursValuty;
	}

	public void setKursValuty(double kursValuty) {
		this.kursValuty = kursValuty;
	}

}
