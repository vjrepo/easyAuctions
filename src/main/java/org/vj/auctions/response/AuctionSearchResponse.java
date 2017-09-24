package org.vj.auctions.response;

import java.util.ArrayList;
import java.util.List;

import org.vj.auctions.model.Auction;

public class AuctionSearchResponse extends Response {
	private List<Auction> auctionList = new ArrayList<Auction>();

	public List<Auction> getAuctionList() {
		return auctionList;
	}

	public void setAuctionList(List<Auction> auctionList) {
		this.auctionList = auctionList;
	}

}
