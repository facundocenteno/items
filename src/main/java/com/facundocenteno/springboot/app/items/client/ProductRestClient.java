package com.facundocenteno.springboot.app.items.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.facundocenteno.springboot.app.items.models.Product;

@FeignClient(name = "service-products")
public interface ProductRestClient {
	
	@GetMapping("/list")
	public List<Product> getAll();


	@GetMapping("/get/{id}")
	public Product getById(@PathVariable Long id);
}
 