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

  - incrementar auto el id de visualizacion
  - controlar que los campos no puedan estar vacíos
  - controlar que el idProduct sea válido (exista)

    {
    "idVisual": 0,
    "inicio": "2020-05-01T02:00:00",
    "fin": "2020-05-01T02:15:00",
    "idProduct": 2
    }
