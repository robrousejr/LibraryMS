# LibraryMS
A library management system REST API

## Project Details

------------------

### Dependencies and Configuration
Create a Spring Boot project using Spring Initializr, and include the following dependencies:
* Spring Web
* Spring Data JPA
* [H2 Database](#h2-database)
* Lombok
* Spring Security (optional for authentication)

#### H2 Database
H2 is an in-memory database that is used for development and testing. It is not recommended for production. 
To connect to the H2 Console, navigate to localhost:8080/h2-console and enter the following details which can also be found in the `application.properties` file:
* `JDBC URL: jdbc:h2:mem:testdb`
* `Username: libraryMSUser`
* `Password: libraryMSPassword`
* `Driver Class: org.h2.Driver`

### Database Entities
There are entities for Book, IssuedBook, User, Role.

**Book:**
- ID
- Title
- Author
- ISBN
- Genre
- Available copies

**User:**
- ID
- Name
- Email
- Password
- Role (*ADMIN*, *USER*)

**IssuedBook:**
- ID
- Book (reference to Book)
- User (reference to User)
- Issue Date
- Return Date

## API Endpoints

Book:
- GET /books – List all books
- GET /books/{id} – Get a specific book
- POST /books – Add a new book
- PUT /books/{id} – Update a book
- DELETE /books/{id} – Delete a book

User:
- GET /users – List all users (admin only)
- GET /users/{id} – Get a specific user (admin or the user itself)
- POST /users – Register a new user
- PUT /users/{id} – Update a user
- DELETE /users/{id} – Delete a user (admin only)

IssuedBook:
- GET /issued-books – List all issued books (admin only)
- GET /issued-books/{id} – Get a specific issued book
- POST /issued-books – Issue a new book to a user
- PUT /issued-books/{id}/return – Return an issued book

## Security (Optional)
You can implement security using Spring Security:
- Authenticate users with username and password
- Authorize endpoints based on user roles (e.g., only admin can delete a user)

## Documentation
Include API documentation using tools like Swagger.

## Testing
Write unit and integration tests for the controllers, services, and repositories to ensure everything is working as expected.