package com.myproject.shoppinglist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.shoppinglist.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
