package org.vj.auctions.request;

public class NewBidRequest {

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public Long getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Long bidPrice) {
		this.bidPrice = bidPrice;
	}

	public NewBidRequest() {

	}

	private Long userId;
	private Long auctionId;
	private Long bidPrice;
}
