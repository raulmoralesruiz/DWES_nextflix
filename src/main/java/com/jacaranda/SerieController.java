package com.jacaranda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Product;
import com.jacaranda.entity.Serie;
import com.jacaranda.entity.SuscriptionEnum;

@RestController
@RequestMapping(path = "/serie")
public class SerieController {

	private List<Serie> series = new ArrayList<>() {
		{
			add(new Serie("Black Mirror"));
			add(new Serie("Dark"));
			add(new Serie("Breaking Bad"));
			add(new Serie("Stranger Things"));
			add(new Serie("Friends"));
		}
	};

	// LEER
	@GetMapping("/series")
	public ResponseEntity<?> leeSeries() {

		ResponseEntity<?> response = null;

		if (series.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(series);
		}

		return response;
	}

}
