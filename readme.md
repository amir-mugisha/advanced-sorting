# Advanced Sorting Application

The Advanced Sorting Application is a Java-based project that
leverages Spring Boot and Maven for building and managing. 
It's designed to perform various sorting operations, 
particularly focusing on sorting lists with specified sorting algorithms.
This application is part of a larger ecosystem that interacts
with a PostgreSQL database to manage movies and actors,
showcasing the use of JPA for database operations.

## Project Setup

- **Language**: Java
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Database**: PostgreSQL

## Features

- Managing movies and actors in a PostgreSQL database.
- Demonstrating CRUD operations with Spring Data JPA.
- Sorting lists with various sorting algorithms.
- 
## Configuration

The application is configured to connect to a PostgreSQL
database with the following settings which you can modify in `resources/application.properties`:

- **URL**: `jdbc:postgresql://localhost:5432/movies`
- **Username**: your db username
- **Password**: your db password

Spring JPA is configured for automatic schema updates and SQL logging to aid in development.

## Running the Application

To run the application, ensure Maven is installed and execute the following command in the project root directory:

```bash
mvn spring-boot:run