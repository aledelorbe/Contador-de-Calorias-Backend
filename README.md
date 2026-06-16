# Contador de Calorías (Backend)

El proyecto **Contador de Calorías** es una aplicación backend desarrollada con **Java**, **Spring Boot**, **MongoDB** y **Redis**. Su objetivo es gestionar actividades con información como nombre, calorías y categoría, proporcionando una API REST para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre estas actividades almacenadas en una base de datos.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para construir aplicaciones Java. Particularmente en este proyecto se utiliza la versión `3.4.0`.
  - **Jakarta Validation**: Validación de datos de entrada.
- **Java**: Lenguaje de programación principal. Para este proyecto en específico se utilizó el `JDK 17`.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **MongoDB**: Base de datos NoSQL para almacenar datos de la aplicación.
- **Redis**: Base de datos NoSQL usada como cache.
- **JUnit**: Framework de pruebas unitarias utilizado para verificar el correcto funcionamiento de los métodos.
- **Mockito**: Framework de mocking usado para simular dependencias y facilitar las pruebas unitarias en aislamiento.
- **JaCoCo**: Herramienta de análisis de cobertura de código utilizada para medir el porcentaje de código ejecutado por las pruebas y generar reportes de cobertura.
- **Swagger / OpenAPI**: Herramienta para documentar y probar los endpoints de la API de forma interactiva.
- **Docker**: permite ejecutar esta aplicación en un entorno aislado, sin necesidad de configurar manualmente dependencias o versiones.
- **Postman**: Para simular ser un cliente que hace peticiones al servidor y probar los endpoints.

## Características

### EndPoint's

Rutas organizadas para interactuar con las actividades. Operaciones soportadas:

- **Categoria de Actividades**:
  - Listar todas las categorias de actividades.
- **Actividades**:
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

### Eventos de ciclo de vida de objetos de las clases entity

- Cada vez que se actualiza o inserta la información de alguna actividad, se limpiara automaticamente el campo `name` de ese mismo registro quitando los espacios en blanco al inicio y al final del valor.

### Patrones

#### Patrón arquitectónico

- **MVC**: Usado para separar en diferentes capas el código del proyecto.

#### Patrón de caché

- **Cache-Aside**: Usado para mejorar los tiempos de respuesta y reducir la carga sobre la base de datos.

#### Patrón creacional

- **Singleton Container**: Usado para levantar una unica instancia de los contenedores de MongoDB y Redis, los cuales son contenedores que son usados por todas las pruebas de integración, evitando levantar nuevos contenedores para cada clase de prueba.

#### Patrón de resilencia

- **Circuit breaker**: Usado para proteger las llamadas realizadas a Redis. Su objetivo es evitar que fallos repetidos en Redis afecten el rendimiento o la disponibilidad de la aplicación.

### Docker

Este proyecto utiliza Docker para crear un entorno de ejecución aislado y reproducible, asegurando que la aplicación funcione igual en cualquier sistema.

Archivos relevantes:

- `Dockerfile`: Define la imagen base y cómo se construye el entorno del proyecto.
- `docker-compose.yml`: Orquesta los servicios (aplicación, base de datos y Redis) para facilitar la ejecución local.
- `.env`: Contiene variables de entorno usadas por Docker (no se incluyen en el repositorio por seguridad).

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
- `integrations/`: Contiene las clases de prueba que validan el comportamiento completo de los controladores (tests de integración).
- `resources/`: Almacena los datos en formato JSON utilizados como insumos para las pruebas de integración.

## Demo

Puedes ver una demo del proyecto en el siguiente enlace: [Contador de Calorías](https://deft-kataifi-58f6e5.netlify.app/).

**Nota:** La demo del proyecto es únicamente demostrativa. Esta no está enlazada con el backend.

## Futuras mejoras

- renombrar el nombre del archivo de la clase 'ActivityCategoryServiceImpTest'.
- Crear el perfil dev para conectarse con mongoDb atlas.
- Despliegue en aws.
- Despliegue automatico usando jenkins.
- Actualizar el readme.

----

# Calorie Counter (Backend)

The **Calorie Counter** project is a backend application developed with **Java**, **Spring Boot**, **MongoDB**, and **Redis**. Its purpose is to manage activities containing information such as name, calories, and category, providing a REST API to perform CRUD operations (Create, Read, Update, and Delete) on activities stored in a database.

## Technologies Used

- **Spring Boot**: Framework for building Java applications. This project uses version `3.4.0`.

  - **Jakarta Validation**: Input data validation.
- **Java**: Main programming language. This project was developed using **JDK 17**.
- **Maven**: Dependency management and project build tool.
- **MongoDB**: NoSQL database used to store application data.
- **Redis**: NoSQL database used as a cache layer.
- **JUnit**: Unit testing framework used to verify the correct behavior of application methods.
- **Mockito**: Mocking framework used to simulate dependencies and facilitate isolated unit testing.
- **JaCoCo**: Code coverage analysis tool used to measure the percentage of code executed by tests and generate coverage reports.
- **Swagger / OpenAPI**: Tool used to document and interactively test API endpoints.
- **Docker**: Allows the application to run in an isolated environment without manually configuring dependencies or versions.
- **Postman**: Used to simulate client requests and test API endpoints.

## Features

### Endpoints

Organized routes for interacting with activities. Supported operations include:

- **Activity Categories**

  - Retrieve all activity categories.

- **Activities**

  - Retrieve all activities.
  - Retrieve a specific activity by its ID.
  - Create new activities by providing a name, category, and calorie count.
  - Update existing activities.
  - Delete individual activities.
  - Reset the server by removing all activities from the database.

### Database Management

- Integration with MongoDB for data persistence.
- The NoSQL database contains a single collection that stores activity information.

### Validations

The following validations are applied:

- Activity **name** and **category** cannot be empty or contain only whitespace.
- The **calories** attribute must be greater than zero.

### Entity Lifecycle Events

- Whenever an activity is created or updated, the `name` field is automatically trimmed to remove leading and trailing whitespace.

### Design Patterns

#### Architectural Pattern

- **MVC (Model-View-Controller)**: Used to separate the application into different layers and responsibilities.

#### Caching Pattern

- **Cache-Aside**: Used to improve response times and reduce database load.

#### Creational Pattern

- **Singleton Container**: Used to maintain a single instance of MongoDB and Redis containers shared across all integration tests, avoiding the overhead of creating new containers for every test class.

#### Resilience Pattern

- **Circuit Breaker**: Used to protect calls made to Redis. Its purpose is to prevent repeated Redis failures from affecting the application's performance or availability.

### Docker

This project uses Docker to create an isolated and reproducible execution environment, ensuring the application behaves consistently across different systems.

Relevant files:

- `Dockerfile`: Defines the base image and how the project environment is built.
- `docker-compose.yml`: Orchestrates services (API and database) to simplify local execution.
- `.env`: Contains environment variables used by Docker (not included in the repository for security reasons).

## Project Structure

### Application Source Code

- `controllers/`: Contains classes responsible for handling HTTP requests and defining API endpoints.
- `services/`: Contains classes that implement business logic.
- `repositories/`: Contains interfaces responsible for data access operations.
- `entities/`: Contains classes mapped to their corresponding database collections.

### Test Code

- `controllers/`: Contains test classes that validate controller behavior.
- `services/`: Contains test classes that verify service-layer functionality.
- `data/`: Stores mock data used during test execution.
- `integrations/`: Contains integration tests that validate the complete behavior of controllers and application components.
- `resources/`: Stores JSON files used as input data for integration tests.

## Demo

You can view a demo of the project at the following link:

**Calorie Counter:** https://deft-kataifi-58f6e5.netlify.app/

**Note:** This demo is for demonstration purposes only and is not connected to the backend application.

## Future Improvements

- Create a `dev` profile for connecting to MongoDB Atlas.
- Deploy the application to AWS.
- Implement automated deployment using Jenkins.
- Improve and expand the project documentation.
