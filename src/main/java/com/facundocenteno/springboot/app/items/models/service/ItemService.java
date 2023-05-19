package com.facundocenteno.springboot.app.items.models.service;

import java.util.List;

import com.facundocenteno.springboot.app.items.models.Item;

public interface ItemService {
	public List<Item> getAll();
	public Item getById(Long id, Integer quantity);

}
