package com.facundocenteno.springboot.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.facundocenteno.springboot.app.items.client.ProductRestClient;
import com.facundocenteno.springboot.app.items.models.Item;


@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductRestClient feignClient;

	@Override
	public List<Item> getAll() {
		return feignClient.getAll().stream()
				.map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item getById(Long id, Integer quantity) {
		return new Item(feignClient.getById(id), quantity);
	}

}


