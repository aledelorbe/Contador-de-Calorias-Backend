# Contador de Calorías (Backend)

El proyecto **Contador de Calorías** es una aplicación backend desarrollada con **Spring Boot** y **MongoDB**. Su objetivo es gestionar actividades con información como nombre, calorías y categoría, proporcionando una API REST para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre estas actividades almacenadas en una base de datos MongoDB.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para construir aplicaciones Java. Particularmente en este proyecto se utiliza la versión `3.4.0`.
- **Java**: Lenguaje de programación principal. Para este proyecto en específico se utilizó el `JDK 17`.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **Jakarta Validation**: Validación de datos de entrada.
- **MongoDB**: Base de datos NoSQL para almacenar las actividades.
- **Postman**: Para simular ser un cliente que hace peticiones al servidor y probar los endpoints.

## Características 

- API REST con rutas organizadas para interactuar con actividades. Operaciones soportadas:
    - Listar todas las actividades.
    - Obtener una actividad específica por su ID.
    - Crear nuevas actividades. Para ello se debe ingresar el nombre, categoría y cantidad de calorías de una actividad.
    - Actualizar actividades existentes.
    - Eliminar actividades individuales.
    - Reinicio del servidor: Consiste en eliminar todas las actividades de la base de datos.
- Integración con MongoDB para la manipulación de datos.
- Validación de datos de entrada. Se emplean las siguientes validaciones:
    - No se permite que los atributos **nombre** y **categoría** de la actividad se reciban vacíos o con puros espacios en blanco.
    - No se permite que el atributo **calorías** se reciba con un valor menor o igual a cero.
- Se emplea el patrón de diseño arquitectónico conocido como **MVC**, para separar en diferentes capas el código del proyecto.

## Estructura del Proyecto

- `controllers/`: Carpeta donde se almacenan las clases que manejan las solicitudes HTTP y definen los endpoints de la API.
- `services/`: Carpeta donde se almacenan las clases que contienen el código relacionado con la lógica de negocio.
- `repositories/`: Carpeta donde se almacenan las interfaces que extienden de una interfaz que permite el manejo de datos.
- `entities/`: Carpeta donde se almacenan las clases que se mapean con sus respectivas colecciones en la base de datos.

## Demo

Puedes ver una demo del proyecto en el siguiente enlace: [Contador de Calorías](https://deft-kataifi-58f6e5.netlify.app/).

**Nota:** La demo del proyecto es únicamente demostrativa. Esta no está enlazada con el backend.

----

# Calorie Counter (Backend)

The **Calorie Counter** project is a backend application developed with **Spring Boot** and **MongoDB**. Its goal is to manage activities containing information such as name, calories, and category, providing a REST API to perform CRUD operations (Create, Read, Update, Delete) on these activities stored in a MongoDB database.

## Technologies Used

- **Spring Boot**: Framework for building Java applications. This project uses version `3.4.0`.
- **Java**: Main programming language. This project specifically uses `JDK 17`.
- **Maven**: For dependency management and project building.
- **Jakarta Validation**: For input data validation.
- **MongoDB**: NoSQL database used to store the activities.
- **Postman**: Used to simulate a client making requests to the server and to test the endpoints.

## Features 

- REST API with organized routes to interact with activities. Supported operations include:
    - List all activities.
    - Retrieve a specific activity by its ID.
    - Create new activities. To do this, you must provide the activity's name, category, and calorie count.
    - Update existing activities.
    - Delete individual activities.
    - Reset the server: This operation deletes all activities from the database.
- Integration with MongoDB for data management.
- Input data validation. The following validations are applied:
    - The **name** and **category** attributes of the activity cannot be empty or consist only of whitespace.
    - The **calories** attribute must be greater than zero.
- Uses the **MVC** architectural design pattern to separate the project code into different layers.

## Project Structure

- `controllers/`: Folder containing classes that handle HTTP requests and define the API endpoints.
- `services/`: Folder containing classes with business logic.
- `repositories/`: Folder containing interfaces extending a base interface for data handling.
- `entities/`: Folder containing classes mapped to their respective collections in the database.

## Demo

You can view a demo of the project at the following link: [Calorie Counter](https://deft-kataifi-58f6e5.netlify.app/).

**Note:** The project demo is for demonstration purposes only. It is not connected to the backend.
