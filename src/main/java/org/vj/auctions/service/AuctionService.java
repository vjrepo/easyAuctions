package org.vj.auctions.service;

import java.util.List;

import org.vj.auctions.exception.BidTooLowException;
import org.vj.auctions.model.Auction;
import org.vj.auctions.request.NewBidRequest;

public interface AuctionService {
	
	public boolean createAuction(Auction auction);
	
	public List<Auction> searchAuctions(String searchText);
	
	public boolean placeBid(NewBidRequest bidRequest) throws BidTooLowException;

}
