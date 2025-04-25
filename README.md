# Contador de Calorías (Backend)

El proyecto **Contador de Calorías** es una aplicación backend desarrollada con **Spring Boot** y **MongoDB**. Su objetivo es gestionar actividades con información como nombre, calorías y categoría, proporcionando una API REST para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre estas actividades almacenadas en una base de datos MongoDB.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para construir aplicaciones Java. Particularmente en este proyecto se utiliza la versión `3.4.0`.
  - **Jakarta Validation**: Validación de datos de entrada.
- **Java**: Lenguaje de programación principal. Para este proyecto en específico se utilizó el `JDK 17`.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **MongoDB**: Base de datos NoSQL para almacenar las actividades.
- **Postman**: Para simular ser un cliente que hace peticiones al servidor y probar los endpoints.
- **JUnit**: Framework de pruebas unitarias utilizado para verificar el correcto funcionamiento de los métodos.
- **Mockito**: Framework de mocking usado para simular dependencias y facilitar las pruebas unitarias en aislamiento.

## Características

- API REST con rutas organizadas para interactuar con actividades. Operaciones soportadas:
  - Listar todas las actividades.
  - Obtener una actividad específica por su ID.
  - Crear nuevas actividades. Para ello se debe ingresar el nombre, categoría y cantidad de calorías de una actividad.
  - Actualizar actividades existentes.
  - Eliminar actividades individuales.
  - Reinicio del servidor: Consiste en eliminar todas las actividades de la base de datos.
- Integración con MongoDB para la manipulación de datos.
- La base de datos NoSQL cuenta con una unica coleccion, la cual gestiona la información de las actividades.
- Validación de datos de entrada. Se emplean las siguientes validaciones:
  - No se permite que los atributos **nombre** y **categoría** de la actividad se reciban vacíos o con puros espacios en blanco.
  - No se permite que el atributo **calorías** se reciba con un valor menor o igual a cero.
- Se emplea el patrón de diseño arquitectónico conocido como **MVC**, para separar en diferentes capas el código del proyecto.

## Estructura del Proyecto

### Código fuente de la aplicación

- `controllers/`: Carpeta donde se almacenan las clases que manejan las solicitudes HTTP y definen los endpoints de la API.
- `services/`: Carpeta donde se almacenan las clases que contienen el código relacionado con la lógica de negocio.
- `repositories/`: Carpeta donde se almacenan las interfaces que extienden de una interfaz que permite el manejo de datos.
- `entities/`: Carpeta donde se almacenan las clases que se mapean con sus respectivas colecciones en la base de datos.

### Código de pruebas

- `controllers/`: Contiene las clases de prueba que validan el comportamiento de los métodos en los controladores del código fuente.
- `services/`: Incluye las clases de prueba dedicadas a verificar el correcto funcionamiento de los métodos dentro de los servicios de la aplicación.
- `data/`: Almacena clases con datos simulados (mock data) utilizados durante la ejecución de las pruebas.

## Demo

Puedes ver una demo del proyecto en el siguiente enlace: [Contador de Calorías](https://deft-kataifi-58f6e5.netlify.app/).

**Nota:** La demo del proyecto es únicamente demostrativa. Esta no está enlazada con el backend.

----
# Calorie Counter (Backend)

The **Calorie Counter** project is a backend application developed with **Spring Boot** and **MongoDB**. Its purpose is to manage activities with information such as name, calories, and category, providing a REST API to perform CRUD operations (Create, Read, Update, and Delete) on these activities stored in a MongoDB database.

## Technologies Used

- **Spring Boot**: Framework for building Java applications. This project uses version `3.4.0`.
  - **Jakarta Validation**: Used for input data validation.
- **Java**: Main programming language. This project specifically uses `JDK 17`.
- **Maven**: Used for dependency management and project build.
- **MongoDB**: NoSQL database used to store activity data.
- **Postman**: Used to simulate a client making requests to the server and test the API endpoints.
- **JUnit**: Unit testing framework used to verify the correct behavior of methods.
- **Mockito**: Mocking framework used to simulate dependencies and facilitate isolated unit testing.

## Features

- REST API with organized routes to interact with activities. Supported operations:
  - List all activities.
  - Get a specific activity by its ID.
  - Create new activities. Requires entering the name, category, and calorie count.
  - Update existing activities.
  - Delete individual activities.
  - Server reset: Deletes all activities from the database.
- Integration with MongoDB for data handling.
- The NoSQL database contains a single collection that manages the activity data.
- Input data validation. The following validations are applied:
  - The **name** and **category** attributes must not be empty or contain only blank spaces.
  - The **calories** attribute must be greater than zero.
- The project follows the **MVC architectural pattern**, separating the code into different layers.

## Project Structure

### Application source code

- `controllers/`: Contains classes that handle HTTP requests and define the API endpoints.
- `services/`: Contains classes responsible for the business logic.
- `repositories/`: Contains interfaces that extend from a data handling interface.
- `entities/`: Contains classes that map to their respective collections in the database.

### Test code

- `controllers/`: Contains test classes that validate the behavior of methods in the controllers.
- `services/`: Includes test classes focused on verifying the correct behavior of service methods.
- `data/`: Stores classes with mock data used during test execution.

## Demo

You can view a demo of the project at the following link: [Calorie Counter](https://deft-kataifi-58f6e5.netlify.app/).

**Note:** The project demo is for presentation purposes only. It is not connected to the backend.
