# LibraryMS
A library management system REST API

## Project Details

#### Dependencies and Configuration
Create a Spring Boot project using Spring Initializr, and include the following dependencies:
* Spring Web
* Spring Data JPA
* H2 Database (or any other relational database you prefer)
* Spring Security (optional for authentication)

#### Database Entities
Create entities for Book, User, IssuedBook. Here's a rough structure:

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
- Role (ADMIN, USER)

**IssuedBook:**
- ID
- Book (reference to Book)
- User (reference to User)
- Issue Date
- Return Date

## Repositories

Create JPA repositories for each entity:
- BookRepository
- UserRepository
- IssuedBookRepository

## Services

Create service classes to encapsulate the business logic:
- BookService
- UserService
- IssuedBookService

## Controllers
Create REST controllers to expose the API endpoints:
- BookController
- UserController
- IssuedBookController

## API Endpoints

Book:
GET /books – List all books
GET /books/{id} – Get a specific book
POST /books – Add a new book
PUT /books/{id} – Update a book
DELETE /books/{id} – Delete a book

User:
GET /users – List all users (admin only)
GET /users/{id} – Get a specific user (admin or the user itself)
POST /users – Register a new user
PUT /users/{id} – Update a user
DELETE /users/{id} – Delete a user (admin only)

IssuedBook:
GET /issued-books – List all issued books (admin only)
GET /issued-books/{id} – Get a specific issued book
POST /issued-books – Issue a new book to a user
PUT /issued-books/{id}/return – Return an issued book

## Security (Optional)
You can implement security using Spring Security:
- Authenticate users with username and password
- Authorize endpoints based on user roles (e.g., only admin can delete a user)

## Error Handling
Add global error handling to manage exceptions and provide meaningful error messages to the clients.

## Documentation
Include API documentation using tools like Swagger.

## Testing
Write unit and integration tests for the controllers, services, and repositories to ensure everything is working as expected.

## Conclusion
This project encompasses various aspects of modern web application development, such as CRUD operations, relationship management in databases, security, and testing. Carefully designing the architecture and following best practices can make this project an excellent learning experience for working with Spring Boot and RESTful services.