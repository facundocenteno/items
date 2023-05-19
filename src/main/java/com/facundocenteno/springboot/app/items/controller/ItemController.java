package com.facundocenteno.springboot.app.items.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.facundocenteno.springboot.app.items.models.Item;
import com.facundocenteno.springboot.app.items.models.service.ItemService;


@RestController
public class ItemController {
	
	
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@GetMapping("/items")
	public List<Item> list(){
		return itemService.getAll();
	}
	
	@GetMapping("/items/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.getById(id, quantity);
	}
	
}
	