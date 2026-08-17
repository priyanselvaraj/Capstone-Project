# Supplier Performance Rating and Management System

A complete Java 17 + Spring Boot + MySQL application for managing suppliers and measuring supplier performance.

## Features
- Supplier CRUD
- Product CRUD
- Purchase order management
- Delivery tracking
- Secure password hashing with BCrypt
- User registration and login
- Supplier rating calculation
- Automatic rating category
- Supplier ranking
- Supplier feedback
- Consistent REST API responses
- Centralized API error handling
- Simple browser dashboard at `/`
- SQL schema and sample data
- Unit tests for rating calculation

## Rating Formula
Overall Score =
- Quality × 30%
- Delivery × 30%
- Cost × 15%
- Quantity Accuracy × 10%
- Communication × 10%
- Compliance × 5%

Categories: 90+ Excellent, 75+ Good, 60+ Average, 40+ Poor, below 40 Critical.

## Technology
- Java 17
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA / Hibernate
- MySQL 8
- BCrypt
- JUnit 5
- Maven

## Project Structure
```text
src/main/java/com/supplier/rating/
  controller/       REST API controllers and exception handler
  model/entity/     JPA entities
  repository/       Spring Data repositories
  service/          Business logic
src/main/resources/
  application.properties
  static/index.html
docs/
  database/
  diagrams/
  API_Contract.md
```

## Run
1. Install JDK 17, Maven and MySQL 8.
2. Create the database:
   `mysql -u root -p < docs/database/database_schema.sql`
3. Set credentials using environment variables if needed:
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
4. Run:
   `mvn spring-boot:run`
5. Open `http://localhost:8080/`.
6. Check `http://localhost:8080/api/health`.

If MySQL uses a different password, set `DB_PASSWORD` before starting the application.

## API
See `docs/API_Contract.md` for all endpoints and JSON examples.

## Testing
Run:
`mvn test`

The project includes tests for weighted score calculation and category classification.

## Database
The application uses `spring.jpa.hibernate.ddl-auto=update` for development. The SQL schema in `docs/database/database_schema.sql` is provided for a clean database setup.

For production, use controlled database migrations such as Flyway/Liquibase and environment-managed secrets.
