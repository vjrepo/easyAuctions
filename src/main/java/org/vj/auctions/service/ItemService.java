package org.vj.auctions.service;

import java.util.List;

import org.vj.auctions.model.Item;

public interface ItemService {

	public boolean createItem(Item item);

	public List<Item> findAll();
}
