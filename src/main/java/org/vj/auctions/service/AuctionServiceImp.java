package org.vj.auctions.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vj.auctions.dao.AuctionDao;
import org.vj.auctions.exception.BidTooLowException;
import org.vj.auctions.model.Auction;
import org.vj.auctions.model.Bid;
import org.vj.auctions.request.NewBidRequest;

@Service
public class AuctionServiceImp implements AuctionService {

	@Autowired
	private AuctionDao auctionDao;

	@Autowired
	private CacheService cacheService;

	@Transactional
	public boolean createAuction(Auction auction) {
		// apply all validation rules here, isValid userId, is any data null
		// which is not supposed to be
		auction.setBidPrice(auction.getAuctionItem().getBasePrice());
		auction = auctionDao.createAuction(auction);
		if (auction != null) {
			cacheService.scheduleAuction(auction.getAuctionId(), auction.getAuctionEnd());
		}
		return true;
	}

	@Transactional
	public List<Auction> searchAuctions(String searchText) {
		return auctionDao.searchAuctions(searchText, new DateTime().getMillis());
	}

	@Transactional
	public boolean placeBid(NewBidRequest bidRequest) throws BidTooLowException {
		Auction auction = auctionDao.getAuction(bidRequest.getAuctionId());
		// validate whether price is greater than all previous bids
		// if fine else error

		// validate bid against current bid for price
		if (!isNewBidValid(bidRequest, auction)) {
			throwError(bidRequest);
		}

		// other validations
		Bid newBid = new Bid();
		newBid.setBidPrice(bidRequest.getBidPrice());
		newBid.setBiddingUserId(bidRequest.getUserId());
		auction.getBidList().add(newBid);
//		auction.setBidList(bidList);
		auctionDao.updateAuction(auction);
		return true;
	}

	private boolean isNewBidValid(NewBidRequest bidRequest, Auction auction) throws BidTooLowException {
		if (auction.getBidPrice() >= bidRequest.getBidPrice()) {
			return false;
		}
		for (Bid bid : auction.getBidList()) {
			if (bid.getBidPrice() >= bidRequest.getBidPrice()) {
				return false;
			}
		}
		return true;
	}

	private void throwError(NewBidRequest bidRequest) throws BidTooLowException {
		throw new BidTooLowException(bidRequest.getAuctionId(), bidRequest.getUserId());
	}

}
