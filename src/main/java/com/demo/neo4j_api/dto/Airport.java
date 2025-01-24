package com.demo.neo4j_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
public class Airport {

    @Id
    String code;
    String name;
    Integer numberOfTerminals;
    String address;

    @JsonIgnore
    @Relationship(type = "FLIES_TO", direction = Relationship.Direction.OUTGOING)
    private Set<FlightRelation> flights;

    @JsonIgnore
    @Relationship(type = "LOCATED_IN", direction = Relationship.Direction.OUTGOING)
    private City city;

    public Airport() {
    }

    public Airport(String code, String name, Integer numberOfTerminals, String address) {
        this.code = code;
        this.name = name;
        this.numberOfTerminals = numberOfTerminals;
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfTerminals() {
        return numberOfTerminals;
    }

    public void setNumberOfTerminals(Integer numberOfTerminals) {
        this.numberOfTerminals = numberOfTerminals;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<FlightRelation> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightRelation> flights) {
        this.flights = flights;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void addFlight(FlightRelation fligh){
        this.flights.add(fligh);
    }
}
