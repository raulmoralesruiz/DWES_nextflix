package com.jacaranda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Category;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Product;
import com.jacaranda.entity.Suscription;
import com.jacaranda.entity.SuscriptionEnum;

@RestController
@RequestMapping(path = "/pro")
public class ProductController {

	private List<Product> products = new ArrayList<>() {
		{
			add(new Product(SuscriptionEnum.BASIC));
			add(new Product(SuscriptionEnum.BASIC));
			add(new Product(SuscriptionEnum.STANDARD));
			add(new Product(SuscriptionEnum.STANDARD));
			add(new Product(SuscriptionEnum.PREMIUM));
			add(new Product(SuscriptionEnum.PREMIUM));
		}
	};

	// LEER
	@GetMapping("/products")
	public ResponseEntity<?> leeProductos() {

		ResponseEntity<?> response = null;

		if (products.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(products);
		}

		return response;
	}

}
