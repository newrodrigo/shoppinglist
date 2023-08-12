package com.myproject.shoppinglist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.shoppinglist.dto.ItemDTO;
import com.myproject.shoppinglist.services.ItemService;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public List<ItemDTO> findAll() {
		List<ItemDTO> result = itemService.findAll();
		return result;
	}
}
