# Introduction
This project provides a RESTful API interface for a Neo4j database that implements a flight search service. The service uses Spring Boot and Java to interact with a Neo4j database and exposes endpoints for searching and managing flight data.
## Requirements
Before running the API, ensure you have the following installed on your machine:
- [Docker](https://www.docker.com/)
- [Java SDK (OpenJDK 17)](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/)
## Running the API with Docker
### Step 1: Set Up Neo4j with Docker
```bash
docker run --name neo4j -p 7474:7474 -p 7687:7687 -e NEO4J_AUTH=neo4j/testpassword -d neo4j
````
### Step 2: Build and Run the API
## Configuration
The application connects to Neo4j using the following default settings, as specified in application.properties
```bash
spring.neo4j.uri=bolt://localhost:7687
spring.neo4j.authentication.username=neo4j
spring.neo4j.authentication.password=testpassword
```