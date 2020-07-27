package tn.esprit.ws;

import java.io.Serializable;

public class WSResponse implements Serializable {
	public WSResponse() {
		super();
	}

	private boolean exists;
	private float soldeCongee;

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public float getSoldeCongee() {
		return soldeCongee;
	}

	public void setSoldeCongee(float soldeCongee) {
		this.soldeCongee = soldeCongee;
	}

}
