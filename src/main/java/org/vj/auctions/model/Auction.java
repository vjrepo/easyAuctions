package org.vj.auctions.model;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.joda.time.DateTime;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.InstantConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.vj.auctions.request.NewBidRequest;

import com.google.gson.Gson;

@Entity
@Table(name = "AUCTION")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Auction {

	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public Long getAuctionStart() {
		return auctionStart;
	}

	public void setAuctionStart(Long auctionStart) {
		this.auctionStart = auctionStart;
	}

	public Long getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(Long auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	public Item getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem(Item auctionItem) {
		this.auctionItem = auctionItem;
	}

	public Integer getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(Integer ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public List<Bid> getBidList() {
		return bidList;
	}

	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}

	public Long getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Long bidPrice) {
		this.bidPrice = bidPrice;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "auction_id")
	private Long auctionId;
	@Column(name = "auction_start_date")
	// @Convert(converter = InstantConverter.class)
	// @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Long auctionStart;
	@Column(name = "auction_end_date")
	// @Convert(converter = InstantConverter.class)
	// @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Long auctionEnd;
	@OneToOne(cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	private Item auctionItem;
	@Column(name = "owner_user")
	private Integer ownerUserId;
	private Long bidPrice;

	@OneToMany(cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	private List<Bid> bidList;

	public static void main(String[] args) {
		Gson gson = new Gson();
		Item item = new Item();
		item.setItemId(1l);
		item.setBasePrice(1234l);
		ItemDetails details = new ItemDetails();
		details.setDescription("Item Details description");
		item.setItemDetails(details);
		item.setName("Item name");
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		Instant ins = Instant.now();
		System.out.println(ins);
		Auction auction = new Auction();
		System.out.println(utc.toString());
		auction.auctionEnd = new DateTime().plusDays(1).getMillis();
		auction.auctionStart = new DateTime().getMillis();
		auction.auctionItem = item;
		auction.setBidPrice(1234l);
		System.out.println(gson.toJson(auction));
		NewBidRequest bidRequest = new NewBidRequest();
		bidRequest.setAuctionId(4l);
		bidRequest.setBidPrice(1235l);
		bidRequest.setUserId(1l);
		System.out.println(gson.toJson(bidRequest));
	}
}
