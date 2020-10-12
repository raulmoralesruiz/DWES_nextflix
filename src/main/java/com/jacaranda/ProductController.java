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
//import com.jacaranda.entity.Movie;
import com.jacaranda.entity.Product;
//import com.jacaranda.entity.Serie;
import com.jacaranda.entity.SuscriptionEnum;
import com.jacaranda.entity.TipoContenido;

@RestController
@RequestMapping(path = "/netflix")
public class ProductController {

	
	@SuppressWarnings("serial")
	private static List<Product> products = new ArrayList<>() {
		{
			add(new Product("Senderos de gloria", Category.DRAMA, TipoContenido.MOVIE, SuscriptionEnum.PREMIUM));
			add(new Product("La naranja mecánica", Category.DRAMA, TipoContenido.MOVIE, SuscriptionEnum.BASIC));
			add(new Product("12 hombres sin piedad", Category.DRAMA, TipoContenido.MOVIE, SuscriptionEnum.BASIC));
			add(new Product("Origen", Category.SCIFI, TipoContenido.MOVIE, SuscriptionEnum.BASIC));
			add(new Product("El show de Truman", Category.SCIFI, TipoContenido.MOVIE, SuscriptionEnum.BASIC));
			add(new Product("Black Mirror", Category.SCIFI, TipoContenido.SERIE, SuscriptionEnum.PREMIUM));
			add(new Product("Dark", Category.SCIFI, TipoContenido.SERIE, SuscriptionEnum.BASIC));
			add(new Product("Breaking Bad", Category.DRAMA, TipoContenido.SERIE, SuscriptionEnum.BASIC));
			add(new Product("Stranger Things", Category.SCIFI, TipoContenido.SERIE, SuscriptionEnum.BASIC));
			add(new Product("Friends", Category.COMEDIA, TipoContenido.SERIE, SuscriptionEnum.BASIC));
		}
	};


	public static int totalProductos() {
		return products.size();
	}
	
	// pendiente.
	public static ArrayList<Product> getListaProductosBasic() {
		ArrayList<Product> basics = new ArrayList<>();
		
		for (Product p : products) {
			if (p.getTipoSuscripcion().equals(SuscriptionEnum.BASIC)) {
				basics.add(p);
			}
		}
		
		return basics;
	}






	/**
	 * Método que comprueba si el producto existe, introduciendo el título.	
	 * @param newTitle (Título del producto)
	 * @return int (id del producto)
	 */
	private int existeProducto(String newTitle) {
		boolean productoEncontrado = false;
		int id = -1;

		for (int i = 0; i < products.size() && productoEncontrado == false; i++) {
			String title = products.get(i).getTitle();
			
			if (newTitle.equalsIgnoreCase(title)) {
				productoEncontrado = true;	
				id = products.get(i).getIdProduct();
			} 
		}

		return id;
	}

	
	
	/**
	 * GET. Método para revisar el listado de productos existentes.
	 * @return
	 */
	@GetMapping("/products")
	public ResponseEntity<?> leeProductos() {
		
		ResponseEntity<?> response = null;

		if (products.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista de productos está vacía");
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(products);
		}

		return response;
	}
	

	
	
	// --------------------------------------------------------- MOVIES ---------------------------------------------------------
	/**
	 * POST. Creación de película, proporcionando JSON en body
	 * @param nuevaPeli
	 * @return
	 */
	@PostMapping("/products/movie")
	public ResponseEntity<?> creaPeli(@RequestBody Product nuevaPeli) {

		ResponseEntity<?> response = null;
		
		// guardamos el título de la nueva película
		String newTitle = nuevaPeli.getTitle();
		
		// guardamos el ID de la nueva película
		int idPeli = existeProducto(newTitle);
		
		// Si la película no existe en la lista de películas...
		if (idPeli == -1) {

			//insertamos la película en la lista de películas.
			products.add(nuevaPeli);
			response = ResponseEntity.status(HttpStatus.CREATED).body(nuevaPeli);
			
		// Si la película existe en la lista de películas.
		} else {
			response = ResponseEntity.status(HttpStatus.CONFLICT)
					.body("ERROR. La película " + newTitle + " ya existe. No se puede crear");
		}
		
		return response;
	}
	
	
	
	/**
	 * PUT. Actualización del título de una película.
	 * @param oldTitle (Título existente en la lista)
	 * @param newTitle (Nuevo título)
	 * @return
	 */
	@PutMapping(path="/products/movie")
	public ResponseEntity<?> modPeli(@RequestParam String oldTitle, @RequestParam String newTitle)  {
		
		ResponseEntity<?> response = null;

		// guardamos el id de las películas old y new (si no existe, será -1)
		int idOldMovie = existeProducto(oldTitle);
		int idNewMovie = existeProducto(newTitle);
		
		// Si la película old no existe en la lista de películas...
		if (idOldMovie == -1) {
			
			//mostramos el error
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. La película " + oldTitle + " no existe. No se puede actualizar");

		// Si la old película existe en la lista de películas.
		} else {
			
			// si la nueva película no existe en la lista...
			if (idNewMovie == -1) {
				//modificamos el título y devolvemos la película modificada.
				products.get(idOldMovie).setTitle(newTitle);
				response = ResponseEntity.status(HttpStatus.CREATED).body(products.get(idOldMovie));
				
			// si la nueva película ya existe en la lista...
			} else {
				
				//mostramos el error
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. La película " + newTitle + " ya existe. No se puede actualizar");
			}
			
		}
		
		return response;
	}
	
	
	
	/**
	 * DELETE. Eliminación de una película.
	 * @param title (Título de la película a eliminar)
	 * @return
	 */
	@DeleteMapping(path="/products/movie")
	public ResponseEntity<?> borraPeli(@RequestParam String title) {		
		
		ResponseEntity<?> response = null;

		// guardamos el id de la película (si no existe, será -1)
		int idPeli = existeProducto(title);
				
		// Si la película no existe en la lista de películas...
		if (idPeli == -1) {
			
			//mostramos el error
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. La película " + title + " no existe. No se puede borrar");

		// Si la película existe en la lista de películas.
		} else {
			
			//borramos la película y devolvemos el listado de películas.
			products.remove(idPeli);
			response = ResponseEntity.status(HttpStatus.OK).body(products);
		}
		
		return response;
	}
	// --------------------------------------------------------- MOVIES ---------------------------------------------------------

	
	
	// --------------------------------------------------------- SERIES ---------------------------------------------------------		
	/**
	 * POST. Creación de serie, proporcionando JSON en body
	 * @param nuevaSerie
	 * @return
	 */
	@PostMapping("/products/serie")
	public ResponseEntity<?> creaSerie(@RequestBody Product nuevaSerie) {

		ResponseEntity<?> response = null;
		
		// guardamos el título de la nueva serie
		String newTitle = nuevaSerie.getTitle();
		
		// guardamos el ID de la nueva serie
		int idSerie = existeProducto(newTitle);
		
		// Si la serie no existe en la lista de series...
		if (idSerie == -1) {
			//insertamos la serie en la lista de series.
			products.add(nuevaSerie);
			response = ResponseEntity.status(HttpStatus.CREATED).body(nuevaSerie);
			
		// Si la serie existe en la lista de series.
		} else {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR. La serie " + newTitle + " ya existe. No se puede crear");
		}
		
		return response;
	}
	
	
	
	/**
	 * PUT. Actualización del título de una serie.
	 * @param oldTitle (Título existente en la lista)
	 * @param newTitle (Nuevo título)
	 * @return
	 */
	@PutMapping(path="/products/serie")
	public ResponseEntity<?> modSerie(@RequestParam String oldTitle, @RequestParam String newTitle)  {
		
		ResponseEntity<?> response = null;
		
		// guardamos el id de las series old y new (si no existe, será -1)
		int idOldSerie = existeProducto(oldTitle);
		int idNewSerie = existeProducto(newTitle);	
		
		// Si la serie old no existe en la lista de series...
		if (idOldSerie == -1) {
			
			//mostramos el error
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. La serie " + oldTitle + " no existe. No se puede actualizar");

		// Si la serie old existe en la lista de series.
		} else {
			
			// si la nueva serie no existe en la lista...
			if (idNewSerie == -1) {
				//modificamos el título y devolvemos la serie modificada.
				products.get(idOldSerie).setTitle(newTitle);
				response = ResponseEntity.status(HttpStatus.CREATED).body(products.get(idOldSerie));
				
			// si la nueva serie ya existe en la lista...
			} else {
				
				//mostramos el error
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. La serie " + newTitle + " ya existe. No se puede actualizar");
			}
			
		}
		
		return response;
	}
	
	
	
	/**
	 * DELETE. Eliminación de una serie.
	 * @param title (Título de la serie a eliminar)
	 * @return
	 */
	@DeleteMapping(path="/products/serie")
	public ResponseEntity<?> borraSerie(@RequestParam String title) {		
		
		ResponseEntity<?> response = null;

		// guardamos el id de la serie (si no existe, será -1)
		int idSerie = existeProducto(title);
				
		// Si la serie no existe en la lista de series...
		if (idSerie == -1) {
			
			//mostramos el error
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. La serie " + title + " no existe. No se puede borrar");

		// Si la serie existe en la lista de series.
		} else {
			
			//borramos la serie y devolvemos el listado de series.
			products.remove(idSerie);
			response = ResponseEntity.status(HttpStatus.OK).body(products);
		}
		
		return response;
	}
	// --------------------------------------------------------- SERIES ---------------------------------------------------------




}
