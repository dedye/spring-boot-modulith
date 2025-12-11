# spring-boot-modulith
# Spring Boot Modulith Example

This project demonstrates a Spring Boot application using Spring Modulith for modular architecture with Java 21.

## Features

- **Java 21**: Latest LTS version with modern language features
- **Spring Boot 3.2.0**: Latest Spring Boot framework
- **Spring Modulith 1.1.0**: Modular monolith architecture support
- **Modular Design**: Clean separation of concerns across modules

## Modules

### Transaction Module (`com.example.transaction`)
- Handles orders creation and lifecycle management
- Publishes domain events for orders state changes
- REST API endpoints for orders operations

### Inventory Module (`com.example.inventory`)
- Manages product inventory and stock levels
- Listens to Transaction events for inventory updates
- Provides inventory availability checks

### Orders Module (`com.example.orders`)
- Handles customer Orders
- Listens to Transaction events to send relevant Orders
- Supports multiple Orders channels

## Architecture

The application follows Spring Modulith principles:
- **Package-based modules**: Each module is defined by its package structure
- **Event-driven communication**: Modules communicate via application events
- **Bounded contexts**: Each module has its own domain model and responsibilities
- **Loose coupling**: Dependencies between modules are minimized

## Running the Application

```bash
# Build the project
mvn clean compile

# Run tests
mvn clean test

# Start the application
mvn spring-boot:run
```

## API Endpoints

### Transaction
- `POST /api/transaction` - Create a new Transaction
- `PUT /api/transaction/{id}/complete` - Complete an Transaction

### Example Usage

Create an Transaction:
```bash
curl -X POST http://localhost:8080/api/transaction \
  -H "Content-Type: application/json" \
  -d '{"customerId": "customer123", "amount": 100.00}'
```

Complete an Transaction:
```bash
curl -X PUT http://localhost:8080/api/transaction/1/complete
```

## Testing

The project includes:
- **Unit tests** for individual components
- **Integration tests** using `@ApplicationModuleTest`
- **Event flow testing** with Spring Modulith's test support
- **Module structure validation** to ensure architectural compliance

## Documentation

Run tests to generate module documentation:
```bash
mvn test
```

This generates PlantUML diagrams showing the modular structure in `target/spring-modulith-docs/`.
