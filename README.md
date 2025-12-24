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
- **Swagger / OpenAPI**: Herramienta para documentar y probar los endpoints de la API de forma interactiva.
- **Docker**: permite ejecutar esta aplicación en un entorno aislado, sin necesidad de configurar manualmente dependencias o versiones. 

## Características

### EndPoint's

Rutas organizadas para interactuar con las actividades. Operaciones soportadas:
    - Listar todas las actividades.
    - Obtener una actividad específica por su ID.
    - Crear nuevas actividades. Para ello se debe ingresar el nombre, categoría y cantidad de calorías de una actividad.
    - Actualizar actividades existentes.
    - Eliminar actividades individuales.
    - Reinicio del servidor: Consiste en eliminar todas las actividades de la base de datos.

### Gestor de base de datos

- Integración con MongoDB para la manipulación de datos.
- La base de datos NoSQL cuenta con una unica coleccion, la cual gestiona la información de las actividades.

### Validaciones

Se emplean las siguientes validaciones:
    - No se permite que los atributos **nombre** y **categoría** de la actividad se reciban vacíos o con puros espacios en blanco.
    - No se permite que el atributo **calorías** se reciba con un valor menor o igual a cero.

### Patrones de diseño

- Se emplea el patrón de diseño arquitectónico conocido como **MVC**, para separar en diferentes capas el código del proyecto.

### Docker

Este proyecto utiliza Docker para crear un entorno de ejecución aislado y reproducible, asegurando que la aplicación funcione igual en cualquier sistema. 

Archivos relevantes:

- `Dockerfile`: define la imagen base y cómo se construye el entorno del proyecto.
- `docker-compose.yml`: orquesta los servicios (API y base de datos) para facilitar la ejecución local.
- `.env`: contiene variables de entorno usadas por Docker (no se incluye en el repositorio por seguridad).


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

## Futuras mejoras

- Desarrollar el servicio de catalogos de categoria de gastos.
- Desarrollar las pruebas para este servicio.
- Incorporar este servicio al swagger.
- Crear el perfil dev para conectarse con mongoDb atlas.
- Quizas meterle redis y el patron de diseño Cache-aside pattern para que se quede en cache el catalogo.
- Actualizar el readme.
- Despliegue en aws.
- Despliegue automatico usando jenkins.

----

# Calorie Counter (Backend)

The **Calorie Counter** project is a backend application developed with **Spring Boot** and **MongoDB**. Its goal is to manage activities with information such as name, calories, and category, providing a REST API to perform CRUD operations (Create, Read, Update, and Delete) on these activities stored in a MongoDB database.

## Technologies Used

- **Spring Boot**: Framework for building Java applications. This project uses version `3.4.0`.
  - **Jakarta Validation**: For validating input data.
- **Java**: Main programming language. This specific project uses `JDK 17`.
- **Maven**: For dependency management and project build.
- **MongoDB**: NoSQL database used to store activities.
- **Postman**: Used to simulate a client making requests to the server and to test API endpoints.
- **JUnit**: Unit testing framework used to verify the correct behavior of methods.
- **Mockito**: Mocking framework for simulating dependencies and facilitating isolated unit tests.
- **Swagger / OpenAPI**: Tool used to document and interactively test API endpoints.
- **Docker**: Allows this application to run in an isolated environment without manually configuring dependencies or versions.

## Features

### Endpoints

Organized routes to interact with activities. Supported operations:
- List all activities.
- Get a specific activity by its ID.
- Create new activities. Requires entering the activity name, category, and calorie count.
- Update existing activities.
- Delete individual activities.
- Server reset: Removes all activities from the database.

### Database Manager

- Integration with MongoDB for data manipulation.
- The NoSQL database contains a single collection responsible for managing activity information.

### Validations

The following validations are applied:
- The **name** and **category** attributes of an activity cannot be empty or contain only whitespace.
- The **calories** attribute cannot be less than or equal to zero.

### Design Patterns

- The architectural design pattern **MVC** is used to separate project code into different layers.

### Docker

This project uses Docker to create an isolated and reproducible execution environment, ensuring consistent behavior across different systems.

Relevant files:

- `Dockerfile`: Defines the base image and how the project environment is built.
- `docker-compose.yml`: Orchestrates the services (API and database) to simplify local execution.
- `.env`: Contains environment variables used by Docker (not included in the repository for security).

## Project Structure

### Application Source Code

- `controllers/`: Contains classes that handle HTTP requests and define API endpoints.
- `services/`: Contains classes that implement business logic.
- `repositories/`: Contains interfaces that extend data-handling interfaces.
- `entities/`: Contains classes mapped to their corresponding collections in the database.

### Test Code

- `controllers/`: Contains test classes that validate controller method behavior.
- `services/`: Contains test classes that verify the correct functionality of service methods.
- `data/`: Contains mock data classes used during test execution.

## Demo

You can see a demo of the project at the following link:  
[Calorie Counter](https://deft-kataifi-58f6e5.netlify.app/)

**Note:** The project demo is for demonstration purposes only. It is not connected to the backend.

## Future Improvements

- Develop the category catalog service.
- Create tests for this service.
- Add this service to Swagger.
- Create the `dev` profile to connect to MongoDB Atlas.
- Possibly add Redis and apply the Cache-aside pattern to cache the catalog.
- Update the README.
- Deploy on AWS.
- Set up automatic deployment using Jenkins.
