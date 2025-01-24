package com.demo.neo4j_api.utility;

import com.demo.neo4j_api.dto.Airport;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

public class FlightWithCitiesAndAirportCodes {
    String number;
    String operator;
    Double price;
    Integer flightTimeInMinutes;

    String fromAirport;
    String fromCity;
    String toAirport;
    String toCity;


    public FlightWithCitiesAndAirportCodes(String number, String operator, Double price, Integer flightTimeInMinutes, String fromAirport, String fromCity, String toAirport, String toCity) {
        this.number = number;
        this.operator = operator;
        this.price = price;
        this.flightTimeInMinutes = flightTimeInMinutes;
        this.fromAirport = fromAirport;
        this.fromCity = fromCity;
        this.toAirport = toAirport;
        this.toCity = toCity;
    }

    public FlightWithCitiesAndAirportCodes() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFlightTimeInMinutes() {
        return flightTimeInMinutes;
    }

    public void setFlightTimeInMinutes(Integer flightTimeInMinutes) {
        this.flightTimeInMinutes = flightTimeInMinutes;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
}
