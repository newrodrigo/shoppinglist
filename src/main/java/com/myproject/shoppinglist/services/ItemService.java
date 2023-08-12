package com.myproject.shoppinglist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.shoppinglist.dto.ItemDTO;
import com.myproject.shoppinglist.entities.Item;
import com.myproject.shoppinglist.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public List<ItemDTO> findAll() {
		List<Item> result = itemRepository.findAll();
		return result.stream().map(ItemDTO::new).toList();
	}
}
