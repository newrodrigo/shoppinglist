package com.myproject.shoppinglist.dto;

import java.time.Instant;

import com.myproject.shoppinglist.entities.Item;
import com.myproject.shoppinglist.entities.enums.ItemStatus;

public class ItemConfirmationDTO {

	private Long id;
	private Instant updatedAt;
	private ItemStatus status;

	public ItemConfirmationDTO() {
	}

	public ItemConfirmationDTO(Long id, Instant updatedAt, ItemStatus status) {
		this.id = id;
		this.updatedAt = updatedAt;
		this.status = status;
	}

	public ItemConfirmationDTO(Item entity) {
		id = entity.getId();
		updatedAt = entity.getUpdatedAt();
		status = entity.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}
}
