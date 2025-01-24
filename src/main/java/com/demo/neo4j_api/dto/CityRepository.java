package com.demo.neo4j_api.dto;

import com.demo.neo4j_api.utility.FlightPathDTO;
import com.demo.neo4j_api.utility.ReturnAirportWithACity;
import com.demo.neo4j_api.utility.FlightFullDetails;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends Neo4jRepository<City, String> {

    @Query("MATCH (c:City {country: $country}) RETURN c")
    List<City> findCitiesByCountry(String country);

    @Query("MATCH (c:City {name: $name}) RETURN c")
    Optional<City> findCityByName(String name);

    @Query("MATCH (c:City {name: $name})-[:HAS_AIRPORT]->(a:Airport) " +
            "RETURN a.code AS code, a.name AS name, a.numberOfTerminals AS numberOfTerminals, a.address AS address")
    List<Airport> findAirportsByCityName(String name);

    @Query("MATCH (a:Airport {code: $code})-[:LOCATED_IN]->(c:City) " +
            "RETURN a.code AS code, c.name AS city, a.name AS name, " +
            "a.numberOfTerminals AS numberOfTerminals, a.address AS address")
    Optional<ReturnAirportWithACity> findAirportDetailsByCode(String code);


    @Query("MATCH (departure:Airport)-[flight:FLIES_TO]->(arrival:Airport)," +
           "(departure)-[:LOCATED_IN]->(departureCity:City), " +
           "(arrival)-[:LOCATED_IN]->(arrivalCity:City) " +
           "WHERE flight.number = $flightNumber " +
           "RETURN flight.number AS number, " +
           "       departure.code AS fromAirport, " +
           "       departureCity.name AS fromCity, " +
           "       arrival.code AS toAirport, " +
           "       arrivalCity.name AS toCity, " +
           "       flight.price AS price, " +
           "       flight.flightTimeInMinutes AS flightTimeInMinutes, " +
           "       flight.operator AS operator")
    Optional<FlightFullDetails> findFullDetailsByFlightNumber(String flightNumber);

    @Query("""
    MATCH (fromCity:City {name: $fromCity})-[:HAS_AIRPORT]->(fromAirport:Airport),
          (toCity:City {name: $toCity})-[:HAS_AIRPORT]->(toAirport:Airport),
          path = (fromAirport)-[:FLIES_TO*1..3]->(toAirport)
    WITH path, 
         nodes(path)[0] AS startAirport, 
         nodes(path)[-1] AS endAirport,
         [rel IN relationships(path) | rel.number] AS flightNumbers,
         [rel IN relationships(path) | rel.price] AS flightPrices,
         [rel IN relationships(path) | rel.flightTimeInMinutes] AS flightTimes
    RETURN 
        startAirport.code AS fromAirport,
        endAirport.code AS toAirport,
        flightNumbers AS flights,
        REDUCE(totalPrice = 0, price IN flightPrices | totalPrice + price) AS totalPrice,
        REDUCE(totalTime = 0, time IN flightTimes | totalTime + time) AS timeInMinutes
""")
    List<FlightPathDTO> findRawFlightPathsBetweenTwoCities(String fromCity,String toCity);


    @Query("MATCH (n) DETACH DELETE n")
    void deleteAllNodes();

}
