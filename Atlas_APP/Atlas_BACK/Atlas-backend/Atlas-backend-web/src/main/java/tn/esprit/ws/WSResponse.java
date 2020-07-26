package tn.esprit.ws;

import java.io.Serializable;

public class WSResponse implements Serializable {
	public WSResponse() {
		super();
	}

	private boolean exists;

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

}
