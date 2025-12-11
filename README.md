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
- Listens to orders events for inventory updates
- Provides inventory availability checks

### Notification Module (`com.example.orders`)
- Handles customer notifications
- Listens to orders events to send relevant notifications
- Supports multiple notification channels

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

### Orders
- `POST /api/transaction` - Create a new orders
- `PUT /api/transaction/{id}/complete` - Complete an orders

### Example Usage

Create an orders:
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId": "customer123", "amount": 100.00}'
```

Complete an orders:
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
