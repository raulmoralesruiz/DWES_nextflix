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

import com.jacaranda.entity.Movie;
import com.jacaranda.entity.SuscriptionEnum;

@RestController
@RequestMapping(path = "/netflix")
public class MovieController {
	
	private List<Movie> movies = new ArrayList<>() {
		{
			add(new Movie("Senderos de gloria", SuscriptionEnum.PREMIUM));
			add(new Movie("La naranja mecánica", SuscriptionEnum.STANDARD));
			add(new Movie("12 hombres sin piedad"));
			add(new Movie("Origen"));
			add(new Movie("El show de Truman"));
		}
	};

	
	
	// LEER
	@GetMapping("/movies")
	public ResponseEntity<?> leePeliculas() {

		ResponseEntity<?> response = null;

		if (movies.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(movies);
		}

		return response;
	}
	
	
	
	// CREAR.		Creación de película, proporcionando JSON en body
	@PostMapping("/movies")
	public ResponseEntity<?> creaPeli(@RequestBody Movie nuevaPeli) {
		movies.add(nuevaPeli);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPeli);
	}
	
	
	
	// ACTUALIZAR	Se introduce el titulo de la película para actulizarla
	@PutMapping(path="/movies")
	public ResponseEntity<?> modPeli(@RequestParam String oldTitle, @RequestParam String newTitle)  {
		
		ResponseEntity<?> response = null;
		boolean peliEncontrada = false;
		
		for (int i = 0; i < movies.size() && peliEncontrada == false; i++) {
			String title = movies.get(i).getTitle();
			
			if (title.equalsIgnoreCase(oldTitle)) {
				peliEncontrada = true;
				
				movies.get(i).setTitle(newTitle);
				
				response = ResponseEntity.status(HttpStatus.OK).body(movies);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Película no encontrada.");
			}
		}
		
		return response;
	}
	
	
	
	// BORRAR		Se introduce el titulo de la película a eliminar
	@DeleteMapping(path="/movies")
	public ResponseEntity<?> borraPeli(@RequestParam String title) {
		
		ResponseEntity<?> response = null;
		boolean peliEncontrada = false;
		
		for (int i = 0; i < movies.size() && peliEncontrada == false; i++) {
			String tit = movies.get(i).getTitle();
			
			if (tit.equalsIgnoreCase(title)) {
				peliEncontrada = true;
				movies.remove(movies.get(i));
				
				response = ResponseEntity.status(HttpStatus.OK).body(movies);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Película no encontrada.");
			}
		}
		
		return response;
	}


}
