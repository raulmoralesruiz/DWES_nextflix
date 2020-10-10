package com.jacaranda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

import com.jacaranda.entity.ComparaClienteDNI;
import com.jacaranda.entity.ComparaClienteNombre;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Visual;

/**
 * Controller to manage customer related requests
 * 
 * @author raul
 *
 */

@RestController
@RequestMapping(path = "/netflix")
public class CustomerController {
	
	@SuppressWarnings("serial")
	private List<Customer> customers = new ArrayList<>() {
		{
			add(new Customer("Ruben", "Dxc", "Sev", "823"));
			add(new Customer("Alvaro", "Geerw", "Sev", "932"));
			add(new Customer("Yi", "Bwerd", "Sev", "178"));
			add(new Customer("Largo", "Ape", LocalDate.of(1992, 10, 22), "address", "city", "dni", "country", "666444999", "Masculino"));
			add(new Customer("Raul", "Sdfe", "Sev", "323"));
		}
	};

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		// Se ordena la lista de clientes
		Collections.sort(customers, new ComparaClienteDNI());

		// Se devuelve la lista de clientes ordenada
		return customers;
	}
	
	
	@GetMapping("/ordenado")
	public ResponseEntity<?> ordena(@RequestParam String parametro) {
		
		if (parametro.equalsIgnoreCase("dni")) {
			Collections.sort(customers, new ComparaClienteDNI());
		}
		
		if (parametro.equalsIgnoreCase("nombre")) {
			Collections.sort(customers, new ComparaClienteNombre());
		}
				
		return ResponseEntity.ok(customers);
	}

	
	@GetMapping("/sorted")
	public ResponseEntity<?> sorted(@RequestParam String parametro) {
				
		ResponseEntity<?> response = null;
		customers = null; //new ArrayList<>();

		
		if (customers.isEmpty()) {
			response = ResponseEntity.
					status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(customers);
		}
		
		return response;
	}
	

	// ---------------- CREAR CRUD, con los correspondientes mensajes HTTP ----------------

	/**
	 * Método que comprueba si el cliente existe, introduciendo el nombre.	
	 * @param newName (Nombre del cliente)
	 * @return int (id del cliente)
	 */
	private int existeCliente(String newName) {
		boolean customerEncontrado = false;
		int id = -1;

		for (int i = 0; i < customers.size() && customerEncontrado == false; i++) {
			String name = customers.get(i).getName();
			
			if (newName.equalsIgnoreCase(name)) {
				customerEncontrado = true;	
				id = customers.get(i).getId();
			} 
		}

		return id;
	}

	
	
	
	
	/**
	 * GET. Método para revisar el listado de clientes existentes.
	 * @return
	 */
	@GetMapping("/customer")
	public ResponseEntity<?> leeClientes() {
		
		ResponseEntity<?> response = null;

		if (customers.isEmpty()) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("La lista está vacía");
		} else {		
			response = ResponseEntity.status(HttpStatus.OK).body(customers);
		}
		
		return response;
	}

	
	
	/**
	 * POST. Creación de cliente, proporcionando JSON en body
	 * @param nuevoCliente
	 * @return
	 */
	// Creación de usuario, proporcionando JSON en body
	@PostMapping("/customer")
	public ResponseEntity<?> creaCliente(@RequestBody Customer nuevo) {		
		ResponseEntity<?> response = null;
		
		// guardamos el nombre del nuevo cliente
		String newCustomer = nuevo.getName();
		
		// Si el cliente no existe en la lista de clientes...
		if (existeCliente(newCustomer) == -1) {
			//obtenemos el id del último cliente
			int id = customers.get(customers.size() - 1).getId();
			
			//incrementamos el anterior id y lo aplicamos al nuevo cliente
			nuevo.setId(id + 1);
			nuevo.setVisuals(new ArrayList<Visual>());
			
			//insertamos el cliente en la lista de clientes.
			customers.add(nuevo);
			response = ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
			
		// Si el cliente existe en la lista de clientes.
		} else {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR. El cliente " + newCustomer + " ya existe. No se puede crear");
		}
		
		return response;

		
	}

	
//	// Creación de usuario, sólo campo nombre
//	@PostMapping(path = "/crea")
//	public ResponseEntity<?> creaCli(@RequestParam String name) {
//		
//		int ultimoID = customers.get(customers.size() - 1).getId();
//		
//		Customer newCust = new Customer();
//		newCust.setName(name);
//		newCust.setId(ultimoID + 1);
//		
//		customers.add(newCust);
//				
//		return ResponseEntity.status(HttpStatus.CREATED).body(newCust);
//	}
	
	
	
	// ACTUALIZAR	
	@PutMapping(path="/customer")
	public ResponseEntity<?> modCli(@RequestParam String oldName, @RequestParam String newName)  {
		
		ResponseEntity<?> response = null;
		boolean custEncontrado = false;
		
		for (int i = 0; i < customers.size() && custEncontrado == false; i++) {
			String nombre = customers.get(i).getName();
			
			if (nombre.equalsIgnoreCase(oldName)) {
				custEncontrado = true;
				
				customers.get(i).setName(newName);
				
				response = ResponseEntity.status(HttpStatus.OK).body(customers);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Cliente no encontrado.");
			}
		}
		
		return response;
	}

	
	
//	@PutMapping(path="/modificar")
//	public Customer updateCustomer(@RequestBody Customer actualizado) {
//		Customer customerToUpdate = null;
//		
//		for (Customer c : customers) {
//			if (actualizado.getName().equalsIgnoreCase(c.getName())) {
//				actualizado = c;
//			}
//		}
//		
//		actualizado = customers.stream().filter(c -> c.getName().equalsIgnoreCase(c.getName()))
//				.findFirst().orElse(new Customer());
//		
//		return null;
//	}
	
	
	// BORRAR
	@DeleteMapping(path="/customer")
	public ResponseEntity<?> borraCli(@RequestParam String name) {
		
		ResponseEntity<?> response = null;
		boolean custEncontrado = false;
		
		for (int i = 0; i < customers.size() && custEncontrado == false; i++) {
			String nombre = customers.get(i).getName();
			
			if (nombre.equalsIgnoreCase(name)) {
				custEncontrado = true;
				customers.remove(customers.get(i));
				
				response = ResponseEntity.status(HttpStatus.OK).body(customers);
			} else {
				response = ResponseEntity.
						status(HttpStatus.NOT_FOUND).body("ERROR. Cliente no encontrado.");
			}
		}
		
		return response;
	}

	
	// -------------------------------
	
//	private void addVisual(Customer c, Visual v) {
//		c.getVisuals().add(v);
//	}
	
	
	/**
	 * GET. Método para revisar el listado de visualizaciones de un cliente.
	 * @return
	 */
	@GetMapping("/visual")
	public ResponseEntity<?> leeVisualCliente(@RequestParam String c) {
		
		ResponseEntity<?> response = null;

		// guardamos el id de cliente (si no existe, será -1)
		int idCust = existeCliente(c);

		// Si el cliente no existe en la lista de clientes...
		if (idCust == -1) {
			
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).
					body("ERROR. El cliente " + c + " no existe. No se puede mostrar la lista de visualizaciones");
			
			// Si el cliente existe en la lista de clientes.
		} else {
			
			// Si el cliente tiene alguna visualización, se muestra el listado
			if (customers.get(idCust).contadorVisuals() > 0) {
				response = ResponseEntity.status(HttpStatus.OK).body(customers.get(idCust).getVisuals());
				
			// si el cliente no tiene visualizaciones, se muestra el error.
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).
						body("ERROR. El cliente " + c + " todavía no tiene visualizaciones.");
			}
			
		}
		
		return response;
	}
	
	
	
	/**
	 * POST. Creación de visualización para un cliente, proporcionando JSON en body
	 * @param nuevoCliente
	 * @return
	 */
	// Creación de usuario, proporcionando JSON en body
	@PostMapping("/visual")
	public ResponseEntity<?> creaVisualCliente(@RequestParam String c, @RequestBody Visual v) {
		ResponseEntity<?> response = null;

		// guardamos el id de cliente (si no existe, será -1)
		int idCust = existeCliente(c);

		// Si el cliente no existe en la lista de clientes...
		if (idCust == -1) {

			response = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("ERROR. El cliente " + c + " no existe. No se puede mostrar la lista de visualizaciones");

		// Si el cliente existe en la lista de clientes.
		} else {

			// Se guarda el cliente (para ver el código más claro)
			Customer cliente = customers.get(idCust);
			
			// Se calcula el id de visualización y se inserta a la nueva visualización.
			int idVisual = cliente.contadorVisuals();
			v.setIdVisual(idVisual);
			
			
			
			// __________PENDIENTE________
			// Comprobar si el producto existe. ¿lista de productos?
//			int idProduct = v.getIdProduct();
			// __________PENDIENTE________

			
			
			// Insertar visualización a cliente
			customers.get(idCust).addVisual(v);

			response = ResponseEntity.status(HttpStatus.OK).body(customers.get(idCust).getVisuals());
		}

		return response;

	}
	
}
