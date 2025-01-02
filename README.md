
# MessageSystem

A lightweight, scalable messaging system built with Spring Boot and Apache Cassandra. This project demonstrates an efficient way to manage and store messages between users using a distributed database.
Architecture overview: https://lucid.app/lucidchart/6b9bfc46-b9d0-40a9-96ed-ec952060d645/edit?viewport_loc=936%2C-33%2C3920%2C1923%2C0_0&invitationId=inv_108ef4d0-3927-4ea6-ae47-000c0eecb0d2
## Features

- **Messaging**: Allows users to send, receive, and manage messages.
- **Cassandra Integration**: Leverages Cassandra's distributed and fault-tolerant design for data persistence.
- **Spring Boot**: Provides a robust backend framework for RESTful APIs and modular development.
- **Data Model**: Efficiently designed schema optimized for performance in a distributed environment.
- **Scalability**: Supports horizontal scaling with Cassandra's replication and partitioning capabilities.

---

## Getting Started

### Prerequisites

To run this project, ensure you have the following installed:

- [Java 17+](https://adoptopenjdk.net/)
- [Apache Maven](https://maven.apache.org/) or any compatible build tool
- [Docker](https://www.docker.com/) (for running Cassandra locally)
- [Git](https://git-scm.com/) (to clone the repository)

---

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/NTD98/MessageSystem.git
   cd MessageSystem
   ```

2. **Start the Cassandra database**:
    - Use the provided `docker-compose.yml` to spin up a Cassandra instance:
      ```bash
      docker-compose up -d
      ```

3. **Update application configuration**:
    - Ensure `application.yml` contains the correct connection settings for Cassandra:
      ```yaml
      spring:
        data:
          cassandra:
            contact-points: localhost
            port: 9042
            keyspace-name: keyspace_messages
            schema-action: create-if-not-exists
      ```

4. **Build the project**:
   ```bash
   mvn clean install
   ```

5. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

6. **Access the API**:
    - The application runs on `http://localhost:8080`.
    - Use a tool like Postman or `curl` to interact with the RESTful endpoints.

---

## API Endpoints

| Method | Endpoint                  | Description                       |
|--------|---------------------------|-----------------------------------|
| GET    | `/api/messages`           | Retrieve all messages            |
| GET    | `/api/messages/{id}`      | Retrieve a specific message by ID|
| POST   | `/api/messages`           | Create a new message             |
| DELETE | `/api/messages/{id}`      | Delete a message by ID           |

### Example Message JSON
```json
{
  "messageId": "550e8400-e29b-41d4-a716-446655440000",
  "content": "Hello, World!",
  "sender": "user1",
  "receiver": "user2",
  "sent_at": "2024-12-25T12:00:00Z"
}
```

---

## Database Schema

### Table: `messages`

| Column         | Type    | Description                     |
|----------------|---------|---------------------------------|
| `message_id`   | UUID    | Primary Key                     |
| `content`      | Text    | Message content                 |
| `sender`       | Text    | Sender of the message           |
| `receiver`     | Text    | Receiver of the message         |
| `sent_at`      | Timestamp | Time the message was sent       |

The schema is stored in the Cassandra keyspace `keyspace_messages`.

---

## Technologies Used

- **Spring Boot**: Backend framework for creating RESTful services.
- **Apache Cassandra**: Distributed database for high availability and scalability.
- **Lombok**: Reduces boilerplate code with annotations.
- **Docker**: Simplifies deployment and database management.

---

## Development Workflow

### Run Tests
Run unit tests with:
```bash
mvn test
```

### Lint Code
Ensure code quality by running:
```bash
mvn spotless:apply
```

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`feature/my-feature`).
3. Commit your changes.
4. Push to the branch.
5. Create a pull request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

For questions or suggestions, please reach out to the project maintainer:

- **Name**: Nguyen Tien Dat
- **GitHub**: [NTD98](https://github.com/NTD98)
- **Email**: [16520199@gm.uit.edu.vn]
