package org.vj.auctions.service;

import org.springframework.stereotype.Component;

@Component
public class CacheService {
	public boolean scheduleAuction(Long auctionId, Long endDateTime) {
		// schedule the auction for final processing
		return false;
	}
}
