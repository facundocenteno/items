package com.facundocenteno.springboot.app.items.models.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.facundocenteno.springboot.app.items.models.Item;
import com.facundocenteno.springboot.app.items.models.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate restClient;

	@Override
	public List<Item> getAll() {
		List<Product> products = Arrays
				.asList(restClient.getForObject("http://service-products/products", Product[].class));
		return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item getById(Long id, Integer quantity) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Product product = restClient
				.getForObject("http://service-products/products/{id}", Product.class, pathVariables);
		return new Item(product, quantity);
	}

}
