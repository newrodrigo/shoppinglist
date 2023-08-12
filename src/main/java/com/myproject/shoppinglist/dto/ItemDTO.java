package com.myproject.shoppinglist.dto;

import java.time.Instant;

import com.myproject.shoppinglist.entities.Item;
import com.myproject.shoppinglist.entities.enums.ItemPriority;
import com.myproject.shoppinglist.entities.enums.ItemStatus;

public class ItemDTO {

private Long id;
	
	private String name;
	private String quantity;
	private String description;
	private Instant createdAt;
	private Instant updatedAt;
    private ItemPriority priority;
    private ItemStatus status;
    
    public ItemDTO() {
    }

	public ItemDTO(Long id, String name, String quantity, String description, Instant createdAt, Instant updatedAt,
			ItemPriority priority, ItemStatus status) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.priority = priority;
		this.status = status;
	}
	
	public ItemDTO(Item entity) {
		id = entity.getId();
		name = entity.getName();
		quantity = entity.getQuantity();
		description = entity.getDescription();
		createdAt = entity.getCreatedAt();
		updatedAt = entity.getUpdatedAt();
		priority = entity.getPriority();
		status = entity.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ItemPriority getPriority() {
		return priority;
	}

	public void setPriority(ItemPriority priority) {
		this.priority = priority;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}
}
