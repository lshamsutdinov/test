package app.models;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4104851106688105273L;

	private String number;
	private Date date;
	private String user;
	private double sum;

	private boolean forDel;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public boolean isForDel() {
		return forDel;
	}

	public void setForDel(boolean forDel) {
		this.forDel = forDel;
	}

}
