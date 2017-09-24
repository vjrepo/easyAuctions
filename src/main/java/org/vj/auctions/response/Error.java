package org.vj.auctions.response;

public class Error {

	public Error(String reason) {
		this.reason = reason;
	}
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}