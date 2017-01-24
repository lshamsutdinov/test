package app.models;

public class Zayavka extends BaseModelModified {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4761102033765297101L;

	private String kontragent;
	private double komissiya;

	public String getKontragent() {
		return kontragent;
	}

	public void setKontragent(String kontragent) {
		this.kontragent = kontragent;
	}

	public double getKomissiya() {
		return komissiya;
	}

	public void setKomissiya(double komissiya) {
		this.komissiya = komissiya;
	}

}
