package com.demo.neo4j_api.dto;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface AirportRepository extends Neo4jRepository<Airport, String> {
    @Query("MATCH (source:Airport {code: $sourceCode}) " +
            "MATCH (destination:Airport {code: $destinationCode}) " +
            "MERGE (source)-[:FLIES_TO {number: $flightNumber, operator: $operator, price: $price, flightTimeInMinutes: $flightTime}]->(destination)")
    void addFlightRelationship(String sourceCode, String destinationCode, String flightNumber, String operator, Double price, Integer flightTime);
}
