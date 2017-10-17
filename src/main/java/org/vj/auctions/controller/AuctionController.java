package org.vj.auctions.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vj.auctions.exception.BidTooLowException;
import org.vj.auctions.model.Auction;
import org.vj.auctions.request.NewBidRequest;
import org.vj.auctions.response.Response;
import org.vj.auctions.response.ResponseGenerator;
import org.vj.auctions.service.AuctionService;

@RestController(value = "/auction")
public class AuctionController {
	@Autowired
	private AuctionService auctionService;

	@Autowired
	private ResponseGenerator responseGenerator;

	@RequestMapping(method = RequestMethod.POST, value = "/createAuction")
	public ResponseEntity<Response> createAuction(
			@RequestBody(required = true) Auction auction) {
		return responseGenerator.generateCreateAuctionResponse(auctionService
				.createAuction(auction));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findAuctions")
	public ResponseEntity<Response> searchAuctions(
			@RequestParam String searchText) {
		return responseGenerator.generateSearchAuctionsResponse(auctionService
				.searchAuctions(searchText));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/placeBid")
	public ResponseEntity<Response> placeBid(
			@RequestBody NewBidRequest bidRequest) throws BidTooLowException {
		return responseGenerator.generatePleaceBidResponse(auctionService
				.placeBid(bidRequest));
	}

	@ExceptionHandler(BidTooLowException.class)
	public ResponseEntity<Response> handleBidTooLowException(
			HttpServletRequest request, Exception ex) {
		return responseGenerator
				.generatePleaceBidResponse((BidTooLowException) ex);
	}

}
