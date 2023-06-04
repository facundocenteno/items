package com.facundocenteno.springboot.app.items.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facundocenteno.springboot.app.items.models.Item;
import com.facundocenteno.springboot.app.items.models.Product;
import com.facundocenteno.springboot.app.items.models.service.ItemService;


@RestController
public class ItemController {
	
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> list(@RequestParam(name="name", required=false) String name, @RequestHeader(name="token-request", required=false)String token){
		System.out.println(name);
		System.out.println(token);
		return itemService.getAll();
	}
	
//	@HystrixCommand(fallbackMethod="alternativeMethod")
	@GetMapping("/get/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return cbFactory.create("items")
				.run(()-> itemService.getById(id, quantity) ,e -> alternativeMethod(id, quantity, e)); 
	}
	
	public Item alternativeMethod(Long id, Integer quantity, Throwable e) {
		logger.info(e.getMessage());
		Item item = new Item();
		Product product = new Product();
		
		item.setQuantity(quantity);
		product.setId(id);
		product.setName("Camara");
		product.setPrice(500.00);
		item.setProduct(product);
		
		return item;
	}
	
}
	