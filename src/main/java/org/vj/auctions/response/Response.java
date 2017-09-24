package org.vj.auctions.response;

public class Response {

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	private String message;
	private Error error = new Error("");

}
