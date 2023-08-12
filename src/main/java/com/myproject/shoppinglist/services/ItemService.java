package com.myproject.shoppinglist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.myproject.shoppinglist.dto.ItemDTO;
import com.myproject.shoppinglist.entities.Item;
import com.myproject.shoppinglist.repositories.ItemRepository;
import com.myproject.shoppinglist.services.exceptions.ResourceNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public List<ItemDTO> findAll() {
		List<Item> result = itemRepository.findAll();
		return result.stream().map(ItemDTO::new).toList();
	}
	
	@GetMapping(value = "/{id}")
	public ItemDTO findById(Long id) {
		Optional<Item> obj = itemRepository.findById(id);
		Item entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ItemDTO(entity);
	}
}
