package org.vj.auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vj.auctions.dao.ItemDao;
import org.vj.auctions.model.Item;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	@Transactional
	public boolean createItem(Item item) {
		return itemDao.createItem(item);
	}

	@Transactional
	public List<Item> findAll() {
		return itemDao.findAll();
	}

}
