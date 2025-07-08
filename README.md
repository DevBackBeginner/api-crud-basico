# Customer Management API - Spring Boot & JPA

API RESTful para gestión de clientes con **Spring Boot 3**, **Hibernate/JPA** y **MapStruct**.  
## Features clave
- **CRUD completo** con validaciones y manejo de errores
- **Arquitectura en capas** (Controller-Service-Repository)
- **DTOs segregados**: `RequestCustomerDto` (creación) y `ResponseCustomerDto` (consulta)
- **Mapeo automático** con MapStruct (¡sin código boilerplate!)
- **Base de datos H2** (configurable para MySQL/PostgreSQL)

## Tech Stack
```java
Spring Boot 3 | Java 17 | Hibernate/JPA 
MapStruct | Lombok | H2 Database
