package com.myproject.shoppinglist.services;

import java.time.Instant;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.shoppinglist.dto.ItemConfirmationDTO;
import com.myproject.shoppinglist.dto.ItemDTO;
import com.myproject.shoppinglist.entities.Item;
import com.myproject.shoppinglist.entities.enums.ItemPriority;
import com.myproject.shoppinglist.entities.enums.ItemStatus;
import com.myproject.shoppinglist.repositories.ItemRepository;
import com.myproject.shoppinglist.services.exceptions.DatabaseException;
import com.myproject.shoppinglist.services.exceptions.ResourceNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public Page<ItemDTO> findAllPaged(String name, ItemPriority priority, ItemStatus status, Pageable pageable) {
		Page<Item> result = itemRepository.findAllWithFilters(name, priority, status, pageable);
		return result.map(ItemDTO::new);
	}

	@Transactional(readOnly = true)
	public ItemDTO findById(Long id) {
		Item entity = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ItemDTO(entity);
	}

	@Transactional
	public ItemDTO insert(ItemDTO dto) {
		Item entity = new Item();
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		copyDtoToEntity(dto, entity);
		entity = itemRepository.save(entity);
		return new ItemDTO(entity);
	}

	@Transactional
	public ItemDTO update(Long id, ItemDTO dto) {
		Item entity = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));

		entity.setUpdatedAt(Instant.now());
		copyDtoToEntity(dto, entity);

		entity = itemRepository.save(entity);
		return new ItemDTO(entity);
	}

	@Transactional
	public ItemConfirmationDTO confirmationItem(Long id, ItemConfirmationDTO confirmationItem) {
		Item entity = itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));

		entity.setUpdatedAt(Instant.now());
		confirmationItem.setStatus(ItemStatus.PURCHASED);
		copyDtoToEntityConfirmDto(confirmationItem, entity);

		entity = itemRepository.save(entity);
		return new ItemConfirmationDTO(entity);
	}

	public void delete(Long id) {
		try {
			itemRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(ItemDTO dto, Item entity) {
		BeanUtils.copyProperties(dto, entity, "id", "createdAt", "updatedAt");
	}

	private void copyDtoToEntityConfirmDto(ItemConfirmationDTO confirmationItem, Item entity) {
		BeanUtils.copyProperties(confirmationItem, entity, "id", "updatedAt");
	}
}
