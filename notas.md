Entidad Producto.

Tiene alguna función? --> NO
Debe tener controller? --> NO
Las acciones se controlan desde las entidades hijas.

---

Entidad Rental.

Función? --> videoclub / formato físico
Se sustituye por Visual? --> ok

---

Servicio controlando título de serie. --> OK
Bien situado? --> OK

---

Suscripción y Categoría, deben realizar CRUD? --> NO

---

Las visualizaciones las realiza el cliente.
Deben registrarse en el cliente.

---

Product
serie --> categ, suscr,
movie -->
documentary -->

Category (genre)

Visual

Customer

Suscription

Customer
Visualiza productos --> (serie, mov, doc)
Tiene suscripción --> basic, premium

---

Corregir:

- Al hacer post, si la serie existe...
  no debe incrementarse el id de producto.

- Al crear visualización en un cliente.

  - controlar que los campos no puedan estar vacíos
  - controlar que el idProduct sea válido (exista)
    ? --> idProdMax = series.size() + movies.size();
    ? --> crear enum TipoContenido (serie, peli), si es de tipo serie se busca en la lista de series

    {
    "inicio": "2020-05-01T02:00:00",
    "fin": "2020-05-01T02:15:00",
    "idProduct": 2
    }

---

---

# Contenido

- CustomerController.

  - Lista de clientes
  - CRUD de clientes.
  - CRUD de visualizaciones

- ProductController
  - Lista de productos (series y pelis)
  - CRUD de productos (series y pelis)

{
"inicio": "2020-05-01T02:00:00",
"fin": "2020-05-01T02:15:00",
"producto": {
"idProduct": 1,
"tipoSuscripcion": "STANDARD",
"idMovie": 1,
"title": "La naranja mecánica",
"categoria": "DRAMA",
"idSerie": -11
}
}

CRUD - REST

    Url del proyecto en git
    Archivo pdf con la presentación. Debe incluir:

    Introducción a vuestra propuesta (objetivo y cómo has elaborado tu solución)

    UML para el diagrama de clases

    Estructura del proyecto Java y un par de capturas de las entidades

    Controladores

    Peticiones desde el front (vía curl, postman, soapUI o navegador)

    Dificultades encontradas (si las hay) y cómo las has solucionado
    Propuestas de mejora
      * Estructura. Ubicación y creación de lista de productos.
      Al principio tenía lista de series y lista de películas.
      Ahora tengo una lista de productos en ProductController.

      * Producto. Entidad abstracta?

      * Controlar campos.
      Por ejemplo, al crear una visualización, el idProducto debe ser válido (existir en la lista).
      Solución: método público en ProductController que calcula máximo de productos
