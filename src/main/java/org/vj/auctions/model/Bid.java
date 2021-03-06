package org.vj.auctions.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "BID")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Bid {

	
	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public Long getBiddingUserId() {
		return biddingUserId;
	}

	public void setBiddingUserId(Long biddingUserId) {
		this.biddingUserId = biddingUserId;
	}

	public Long getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Long bidPrice) {
		this.bidPrice = bidPrice;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bidId;

	private Long biddingUserId;
	
	private Long bidPrice;
	
}
