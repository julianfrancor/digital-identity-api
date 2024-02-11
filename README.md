# Digital Identity API

## Description
This project is the backend of the Digital Identity application, developed using microservices in Java 17 with Spring Boot. It provides a secure and scalable platform for managing users' digital identity, enabling efficient and secure storage, retrieval, and sharing of documents.

## Technologies
- **Programming Language:** Java 17
- **Framework:** Spring Boot
- **Microservices Management:** Spring Cloud

## Requirements
- Java 17
- Maven
- Docker (optional for containerization)

## Installation
Clone this repository to your local machine using:
```
git clone https://github.com/julianfrancor/digital-identity-api.git
```

## Configuration
To configure this project on your local environment, follow these steps:
1. Ensure Java 17 is installed.
2. Install Maven to manage dependencies and build the project.
3. Configure the necessary environment variables for external services (databases, authentication services, etc.).

## Execution
To run a specific microservice, navigate to the microservice's directory and execute:
```
mvn spring-boot:run
```

## Dockerization (Optional)
To containerize and run the microservices using Docker, follow these steps:
1. Build the Docker image of the microservice:
```
docker build -t digital-identity-service-name .
```
2. Run the Docker container:
```
docker run -p local-port:container-port digital-identity-service-name
```

## Contributing
To contribute to this project, please send a pull request with your changes for review.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.

---
