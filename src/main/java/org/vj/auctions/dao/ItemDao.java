package org.vj.auctions.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.vj.auctions.model.Item;

@Component
public class ItemDao extends AbstractDao {

	public boolean createItem(Item item) {
//		String sql = "INSERT INTO item(item_id, item_name) VALUES(?,?)";
//		if (getJdbcTemplate().update(sql, item.getItemId(), item.getName()) != 0)
//			return true;
		
		getEntityManager().persist(item);
		return true;
	}

	public List<Item> findAll(){

//		String sql = "SELECT * FROM item";
//
//		List<Item> items = new ArrayList<Item>();
//
//		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
//		for (Map row : rows) {
//			Item customer = new Item();
//			customer.setItemId(((Long)(row.get("ITEM_ID"))));
//			customer.setName((String)row.get("ITEM_NAME"));
//			items.add(customer);
//		}
//
//		return items;
		Query query = getEntityManager().createQuery("from Item");
		return query.getResultList();
	}
}
