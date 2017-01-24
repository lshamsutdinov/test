package app.models;

public class Nakladnaya extends BaseModelModified {

	/**
	 * 
	 */
	private static final long serialVersionUID = -609362762055014819L;

	private String tovar;
	private double kolichestvo;

	public String getTovar() {
		return tovar;
	}

	public void setTovar(String tovar) {
		this.tovar = tovar;
	}

	public double getKolichestvo() {
		return kolichestvo;
	}

	public void setKolichestvo(double kolichestvo) {
		this.kolichestvo = kolichestvo;
	}

}
