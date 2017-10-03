package org.vj.auctions.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ITEM_DETAILS")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class ItemDetails {
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long itemDetailId;
	private String description;
	@ElementCollection
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	private List<String> tags;
}
