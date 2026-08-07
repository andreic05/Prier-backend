# Clothing E-Commerce Platform

A full-stack e-commerce application for a clothing business.

The project is currently in the early stages of development, with the backend being built first. The goal is to create a complete web application that supports the main functionality required by an online clothing store.

## Project Status

🚧 **Work in progress**

The backend is currently under active development.

Current progress:

- Initial database structure created
- Spring Boot backend initialized
- Domain entities are being implemented
- DTOs are being added
- Service layer is being developed
- REST controllers are being implemented
- H2 is currently used as the development database

The database structure and API design may change as the project evolves.

The frontend has not been started yet.

## Tech Stack

### Backend

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database

### Frontend

Planned:

- React
- JavaScript
- HTML
- CSS

## Project Structure

The backend follows a layered architecture separating responsibilities between:

- Entities
- DTOs
- Repositories
- Services
- Controllers

Additional layers and architectural decisions may be introduced as the project grows.

## Planned Features

The application is intended to eventually include functionality such as:

- Product catalogue
- Product categories
- Product variants such as sizes and colours
- Inventory management
- Shopping cart
- Customer accounts
- Order management
- Checkout workflow
- Administrative functionality

The exact scope is still evolving.

## Database

H2 is currently used during development.

A persistent relational database will be introduced later as the project approaches deployment.

## Running the Backend

The project is still in active development, so setup instructions may change.

For now, the backend can be started using the Spring Boot application entry point from an IDE or with Maven:

```bash
./mvnw spring-boot:run
```

## API Documentation

API documentation will be added as the backend becomes more stable.

## Roadmap

Current priorities:

1. Complete the initial domain model
2. Implement remaining repositories, services, DTOs, and controllers
3. Finalize the initial REST API
4. Add validation and error handling
5. Add tests
6. Replace or configure the production database
7. Start frontend development
8. Connect the frontend to the REST API
9. Prepare the application for deployment

## About This Project

This is a solo full-stack project being developed as a practical e-commerce application and as an opportunity to deepen my experience with Spring Boot, backend architecture, REST APIs, databases, and frontend development.

The project is being developed incrementally, and both the architecture and feature set will evolve as development progresses.

## License

This project is licensed under the Apache License 2.0. See the `LICENSE` file for details.