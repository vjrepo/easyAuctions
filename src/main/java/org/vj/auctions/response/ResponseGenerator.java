package org.vj.auctions.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.vj.auctions.exception.BidTooLowException;
import org.vj.auctions.model.Auction;

@Component
public class ResponseGenerator {

	private static final String CREATE_AUCTION_ERROR = "An error occured while creating the auction. Please try again.";
	private static final String CREATE_AUCTION_SUCCESS = "Auction created successfully.";
	private static final String SEARCH_AUCTIONS_SUCCESSFUL = "Successful search. These are the available auctions.";
	private static final String PLACE_BID_UNKNOWN_ERROR = "An unknown erro occured while placing your bid. Please contact customer support";
	private static final String BID_MESSAGE_SUCCESS = "Bid was placed successfully. You are currently the highest bidder";
	private static final String SEARCH_AUCTIONS_UNSUCCESSFUL = "Search for auctions didn't find any results. Please try refining the search criteria.";

	private ResponseEntity<Response> createFinalResponse(Response bidResponse, HttpStatus httpStatus) {
		ResponseEntity<Response> responseEntity;
		responseEntity = new ResponseEntity<Response>(bidResponse, httpStatus);
		return responseEntity;
	}

	public ResponseEntity<Response> generatePleaceBidResponse(boolean placeBidResult) {
		Response bidResponse = new Response();
		if (placeBidResult) {
			bidResponse.setMessage(BID_MESSAGE_SUCCESS);
			return createFinalResponse(bidResponse, HttpStatus.ACCEPTED);
		} else {
			bidResponse.setMessage("Unkown error");
			bidResponse.getError().setReason(PLACE_BID_UNKNOWN_ERROR);
			return createFinalResponse(bidResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Response> generatePleaceBidResponse(BidTooLowException e) {
		Response bidResponse = new Response();
		bidResponse.setMessage(
				"Error while placing you bid.");
		bidResponse.getError().setReason(String.format("Your bid for auction id %d with item id %d was unsuccessful. Try Again.",
				e.getAuctionId(), e.getItemId()));
		return createFinalResponse(bidResponse, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Response> generateSearchAuctionsResponse(List<Auction> searchedAuctions) {
		AuctionSearchResponse searchResponse = new AuctionSearchResponse();
		searchResponse.setAuctionList(searchedAuctions);
		if (searchedAuctions.size() != 0) {
			searchResponse.setMessage(SEARCH_AUCTIONS_SUCCESSFUL);
			return createFinalResponse(searchResponse, HttpStatus.ACCEPTED);
		} else {
			searchResponse.setMessage(SEARCH_AUCTIONS_UNSUCCESSFUL);
			return createFinalResponse(searchResponse, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Response> generateCreateAuctionResponse(boolean createAuctionResult) {
		Response response = new Response();
		if (createAuctionResult) {
			response.setMessage(CREATE_AUCTION_SUCCESS);
			return createFinalResponse(response, HttpStatus.ACCEPTED);
		} else {
			response.setMessage(CREATE_AUCTION_ERROR);
			return createFinalResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
