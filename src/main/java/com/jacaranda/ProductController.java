package com.jacaranda;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pro")
public class ProductController {

//	private List<Product> products = new ArrayList<>() {
//		{
//			add(new Product());
//			add(new Product());
//		}
//	};
//
//	// LEER
//	@GetMapping("/products")
//	public ResponseEntity<?> leeProductos() {
//
//		ResponseEntity<?> response = null;
//
//		if (products.isEmpty()) {
//			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
//		} else {
//			response = ResponseEntity.status(HttpStatus.OK).body(products);
//		}
//
//		return response;
//	}

}
