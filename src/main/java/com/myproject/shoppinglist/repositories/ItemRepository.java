package com.myproject.shoppinglist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myproject.shoppinglist.entities.Item;
import com.myproject.shoppinglist.entities.enums.ItemPriority;
import com.myproject.shoppinglist.entities.enums.ItemStatus;

public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query("SELECT i FROM Item i " + "WHERE (LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))) "
			+ "AND (:priority IS NULL OR i.priority = :priority) " + "AND (:status IS NULL OR i.status = :status)")
	Page<Item> findAllWithFilters(@Param("name") String name, @Param("priority") ItemPriority priority,
			@Param("status") ItemStatus status, Pageable pageable);

}
