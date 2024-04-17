package com.myproject.shoppinglist.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myproject.shoppinglist.dto.ItemConfirmationDTO;
import com.myproject.shoppinglist.dto.ItemDTO;
import com.myproject.shoppinglist.entities.enums.ItemPriority;
import com.myproject.shoppinglist.entities.enums.ItemStatus;
import com.myproject.shoppinglist.services.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public ResponseEntity<Page<ItemDTO>> findAll(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "priority", defaultValue = "") ItemPriority priority,
			@RequestParam(value = "status", defaultValue = "") ItemStatus status, Pageable pageable) {

		Page<ItemDTO> list = itemService.findAllPaged(name, priority, status, pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> findById(@PathVariable Long id) {
		ItemDTO dto = itemService.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ItemDTO> insert(@Valid @RequestBody ItemDTO dto) {
		dto = itemService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> update(@Valid @PathVariable Long id, @RequestBody ItemDTO dto) {
		dto = itemService.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping(value = "/confirmation/{id}")
	public ResponseEntity<ItemConfirmationDTO> confirmationItem(@Valid @PathVariable Long id,
			ItemConfirmationDTO confirmationDto) {
		confirmationDto = itemService.confirmationItem(id, confirmationDto);
		return ResponseEntity.ok().body(confirmationDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
