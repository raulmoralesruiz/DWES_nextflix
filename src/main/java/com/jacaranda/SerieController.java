package com.jacaranda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Product;
import com.jacaranda.entity.Serie;
import com.jacaranda.entity.SuscriptionEnum;

@RestController
@RequestMapping(path = "/netflix")
public class SerieController {
	
	@Autowired
	private SerieService service;

	
	private List<Serie> series = new ArrayList<>() {
		{
//			add(new Serie("Black Mirror"));
			add(new Serie("Black Mirror", SuscriptionEnum.PREMIUM));
//			add(new Serie("Dark"));
			add(new Serie("Dark", SuscriptionEnum.STANDARD));
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
	
	
	
	// CREAR.		Creación de serie, proporcionando JSON en body
	@PostMapping("/series")
	public ResponseEntity<?> creaSerie(@RequestBody Serie nuevaSerie) {
		series.add(nuevaSerie);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSerie);
	}
	
	
	
	// ACTUALIZAR	Se introduce el titulo de la serie para actulizarla
	@PutMapping(path="/series")
	public ResponseEntity<?> modSerie(@RequestParam String oldTitle, @RequestParam String newTitle)  {
		
		ResponseEntity<?> response = null;
		boolean serieEncontrada = false;
		
		for (int i = 0; i < series.size() && serieEncontrada == false; i++) {
			String title = series.get(i).getTitle();
			
			if (title.equalsIgnoreCase(oldTitle)) {
				serieEncontrada = true;
				
				series.get(i).setTitle(newTitle);
				
				response = ResponseEntity.status(HttpStatus.OK).body(series);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Serie no encontrada.");
			}
		}
		
		return response;
	}
	
	
	
	// BORRAR		Se introduce el titulo de la serie a eliminar
	@DeleteMapping(path="/series")
	public ResponseEntity<?> borraSerie(@RequestParam String title) {
		
		ResponseEntity<?> response = null;
		boolean serieEncontrada = false;
		
		for (int i = 0; i < series.size() && serieEncontrada == false; i++) {
			String tit = series.get(i).getTitle();
			
			if (tit.equalsIgnoreCase(title)) {
				serieEncontrada = true;
				series.remove(series.get(i));
				
				response = ResponseEntity.status(HttpStatus.OK).body(series);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Serie no encontrada.");
			}
		}
		
		return response;
	}


	
		

}
