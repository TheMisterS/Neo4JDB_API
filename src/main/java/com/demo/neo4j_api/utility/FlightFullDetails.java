package com.demo.neo4j_api.utility;

public class FlightFullDetails {
    String number;
    String fromAirport;
    String fromCity;
    String toAirport;
    String toCity;

    Double price;
    Integer flightTimeInMinutes;
    String operator;

    public FlightFullDetails() {
    }

    public FlightFullDetails(String number, String fromAirport, String fromCity, String toAirport, String toCity, Double price, Integer flightTimeInMinutes, String operator) {
        this.number = number;
        this.fromAirport = fromAirport;
        this.fromCity = fromCity;
        this.toAirport = toAirport;
        this.toCity = toCity;
        this.price = price;
        this.flightTimeInMinutes = flightTimeInMinutes;
        this.operator = operator;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getFlightTimeInMinutes() {
        return flightTimeInMinutes;
    }

    public void setFlightTimeInMinutes(Integer flightTimeInMinutes) {
        this.flightTimeInMinutes = flightTimeInMinutes;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
