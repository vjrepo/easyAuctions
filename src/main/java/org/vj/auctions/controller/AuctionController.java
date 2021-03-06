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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/auction")
@Api(value="auction", description="Auction service to search and create auctions")
public class AuctionController {
	
	@Autowired
	private AuctionService auctionService;

	@Autowired
	private ResponseGenerator responseGenerator;

	@RequestMapping(method = RequestMethod.POST, value = "/createAuction")
	@ApiOperation(value = "Create an auction")
	public ResponseEntity<Response> createAuction(
			@RequestBody(required = true) Auction auction) {
		return responseGenerator.generateCreateAuctionResponse(auctionService
				.createAuction(auction));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findAuctions")
	@ApiOperation(value = "Find auctions based on search critera")
	public ResponseEntity<Response> searchAuctions(
			@ApiParam(name="searchText", value="Search text", required=true)
			@RequestParam String searchText) {
		return responseGenerator.generateSearchAuctionsResponse(auctionService
				.searchAuctions(searchText));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/placeBid")
	@ApiOperation(value = "Place a bid on the selected auction")
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
