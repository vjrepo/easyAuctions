package org.vj.auctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.vj.auctions.model.Auction;

@Repository
public class AuctionDao extends AbstractDao {

	public Auction createAuction(Auction auction) {
		auction = getEntityManager().merge(auction);
		return auction;
	}

	@SuppressWarnings("unchecked")
	public List<Auction> searchAuctions(String searchText, Long endDate) {
		Query query = getEntityManager().createQuery("from Auction a ");

		// Query query = getEntityManager().createQuery("from Auction a "
		// + "join a.auctionItem i "
		// + "join i.itemDetails id "
		// + "with id.description LIKE CONCAT('%', ?1, '%') "
		// + "where a.auctionEnd > ?2")
		// .setParameter(1, searchText)
		// .setParameter(2, endDate);

		// Query query = getEntityManager()
		// .createNativeQuery(
		// "select * from AUCTION a " + "INNER JOIN ITEM i on
		// a.auction_item_item_id = i.item_id "
		// + "INNER JOIN ITEM_DETAILS id on id.item_detail_id =
		// i.item_details_item_detail_id "
		// // + "WHERE id.description like CONCAT('%', ?1, '%') "
		// // + "AND a.auction_end_date > ?2",
		// ,Auction.class);
		// //.setParameter(1, searchText).setParameter(2, endDate);

		// Query query = getEntityManager()
		// .createNativeQuery(
		// "select * from AUCTION a " + "INNER JOIN ITEM i on
		// a.auction_item_item_id = i.item_id "
		// + "INNER JOIN ITEM_DETAILS id on id.item_detail_id =
		// i.item_details_item_detail_id "
		// + "WHERE id.description like CONCAT('%', ?1, '%') "
		// + "AND a.auction_end_date > ?2",
		// Auction.class)
		// .setParameter(1, searchText).setParameter(2, endDate);

		// working native query
		// select * from easyauctions.AUCTION a INNER JOIN easyauctions.ITEM i
		// on a.auction_item_item_id = i.item_id INNER JOIN
		// easyauctions.ITEM_DETAILS id on id.item_detail_id =
		// i.item_details_item_detail_id WHERE id.description like CONCAT('%',
		// 'Details', '%') and a.auction_end_date > 1506235321905;

		return query.getResultList();
	}

	public Auction getAuction(Long auctionId) {
		Query query = getEntityManager().createQuery("FROM Auction a WHERE a.auctionId = ?", Auction.class)
				.setParameter(1, auctionId);
		return (Auction) query.getSingleResult();
	}

	public boolean updateAuction(Auction auction) {
		getEntityManager().merge(auction);
		getEntityManager().flush();
		return true;
	}
}
