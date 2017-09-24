package org.vj.auctions.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "ITEM")
public class Item {

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemDetails getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(ItemDetails itemDetails) {
		this.itemDetails = itemDetails;
	}

	public Long getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Long basePrice) {
		this.basePrice = basePrice;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long itemId;
	@Column(name = "item_name")
	private String name;
	// @Column(name = "item_details")
	@OneToOne(cascade = CascadeType.ALL)
	private ItemDetails itemDetails;
	private Long basePrice;

	public static void main(String[] args) {
		Gson gson = new Gson();
		Item item = new Item();
		item.itemId = 1l;
		ItemDetails details = new ItemDetails();
		details.setDescription("Item Details description");
		item.itemDetails = details;
		item.name = "Item name";
		System.out.println(gson.toJson(item));
	}
}
