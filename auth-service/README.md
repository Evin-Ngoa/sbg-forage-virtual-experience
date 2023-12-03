# STANDARD BANK FORAGE VIRTUAL EXPERIENCE
- [Forage](https://www.theforage.com/simulations?companies=standard-bank/) - Standard Bank Virtual Experience Programs

## Overview
This project is a Spring Boot application that implements JWT-based authentication. It provides a secure way of handling user authentication using JSON Web Tokens (JWT). The application exposes a REST API endpoint for authentication, allowing users to obtain a JWT upon providing valid credentials. This token can then be used for authorizing subsequent requests to other protected endpoints in the application.

## Key Features
- **JWT-Based Authentication:** Secure authentication mechanism using JSON Web Tokens.
- **Custom Validation Error Handling:** Global exception handling for validation errors, providing clear and concise error responses.
- **Hardcoded User Credentials:** For demonstration purposes, user credentials are hardcoded, simulating the authentication process without the need for an actual database.

## Technologies
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
## Getting Started

### Prerequisites
To run this application, you will need:
- Java Development Kit (JDK), version 11 or later.
- Maven (if running outside of an IDE with integrated Maven support).

### Running the Application

1. **Clone the Repository:**
```sh
git clone https://github.com/Evin-Ngoa/sbg-forage-virtual-experience.git
cd sbg-forage-virtual-experience
```

2. **Run the Application:**
- Using Maven:
```sh
mvn spring-boot:run
```
- Or, if you're using an IDE like IntelliJ IDEA, Eclipse, or Spring Tool Suite, you can run the application by executing the main class.
3. **Access the Application:**
- The application will start and be accessible on `http://localhost:8080`.

### Using the Authentication Endpoint
- Send a POST request to `/authenticate` with the following credentials:
```sh
POST /authenticate
{
    "username": "testuser",
    "password": "testuserpassword"
}
```

### Running Tests
- Run the tests using Maven:
```sh
mvn test
```
- Alternatively, tests can be run directly from the IDE by right-clicking on the test files or test directory and selecting the run option.

## Working Credentials
For testing and demonstration purposes, the application uses the following hardcoded credentials:

- **Username: testuser**
- **Password: testuserpassword**
> Note: In a real-world application, user credentials would be securely managed and stored, often in a database, and not hardcoded as they are in this project.

## Security Considerations
- The provided JWT utility (JwtUtil) and authentication service (AuthServiceImpl) are for demonstrative purposes and include basic implementations. For a production-grade application, ensure to implement advanced security practices like secure key management, password hashing, and more robust error handling.