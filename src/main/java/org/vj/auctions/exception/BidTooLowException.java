package org.vj.auctions.exception;

public class BidTooLowException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long getAuctionId() {
		return auctionId;
	}

	public Long getItemId() {
		return itemId;
	}

	private Long auctionId;
	private Long itemId;

	public BidTooLowException(Long auctionId, Long itemId) {
		super();
		this.auctionId = auctionId;
		this.itemId = itemId;
	}
}
