package org.vj.auctions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vj.auctions.model.Item;
import org.vj.auctions.service.ItemService;

@RestController(value = "/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.POST, value = "/createItem")
	public Boolean greeting(@RequestBody(required = false) Item item) {
		return itemService.createItem(item);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findAllItems")
	public List<Item> getAllItems() {
		return itemService.findAll();
	}

}
