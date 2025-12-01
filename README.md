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

Despliegue en aws.

Despliegue automatico usando jenkins.

----

# Calorie Counter (Backend)

The **Calorie Counter** project is a backend application built with **Spring Boot** and **MongoDB**. Its main purpose is to manage activities with information such as name, calories, and category, providing a REST API to perform CRUD operations (Create, Read, Update, and Delete) on activities stored in a MongoDB database.

## Technologies Used

- **Spring Boot**: Framework for building Java applications. This project uses version `3.4.0`.
  - **Jakarta Validation**: For input data validation.
- **Java**: Main programming language. This project uses `JDK 17`.
- **Maven**: For dependency management and project building.
- **MongoDB**: NoSQL database used to store activities.
- **Postman**: Used to simulate client requests and test API endpoints.
- **JUnit**: Unit testing framework used to verify method functionality.
- **Mockito**: Mocking framework used to simulate dependencies and facilitate isolated unit testing.
- **Swagger / OpenAPI**: Tool for documenting and interactively testing API endpoints.
- **Docker**: Allows running the application in an isolated environment without manually configuring dependencies or versions.

## Features

### Endpoints

Organized routes to interact with the activities. Supported operations:
- List all activities.
- Retrieve a specific activity by its ID.
- Create new activities by providing the name, category, and calorie count.
- Update existing activities.
- Delete individual activities.
- Server reset: deletes all activities from the database.

### Database Manager

- Integrated with MongoDB for data handling.
- The NoSQL database has a single collection that manages activity information.

### Validations

The following validations are applied:
- The **name** and **category** fields cannot be empty or contain only whitespace.
- The **calories** attribute must be greater than zero.

### Design Patterns

- The project follows the **MVC (Model-View-Controller)** architectural pattern to separate code into distinct layers.

### Docker

This project uses Docker to create an isolated and reproducible runtime environment, ensuring consistent behavior across different systems.

Relevant files:

- `Dockerfile`: Defines the base image and how the project environment is built.
- `docker-compose.yml`: Orchestrates the services (API and database) to simplify local execution.
- `.env`: Contains environment variables used by Docker (not included in the repository for security reasons).

## Project Structure

### Application Source Code

- `controllers/`: Contains classes that handle HTTP requests and define the API endpoints.
- `services/`: Contains business logic classes.
- `repositories/`: Contains interfaces that extend data-handling repositories.
- `entities/`: Contains classes mapped to their corresponding MongoDB collections.

### Test Code

- `controllers/`: Contains test classes validating controller method behavior.
- `services/`: Contains test classes verifying service layer functionality.
- `data/`: Stores mock data classes used during test execution.

## Demo

You can view a demo of the project here: [Calorie Counter](https://deft-kataifi-58f6e5.netlify.app/).

**Note:** The demo is for presentation purposes only and is not connected to the backend.

## Future Improvements

- Deployment on AWS.  
- Automated deployment using Jenkins.