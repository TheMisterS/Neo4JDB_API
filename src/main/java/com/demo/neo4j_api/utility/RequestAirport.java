package com.demo.neo4j_api.utility;

public class RequestAirport {

    String number;
    String fromAirport;
    String toAirport;
    Double price;
    Integer flightTimeInMinutes;
    String operator;

    public RequestAirport() {
    }

    public RequestAirport(String number, String fromAirport, String toAirport, Double price, Integer flightTimeInMinutes, String operator) {
        this.number = number;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.price = price;
        this.flightTimeInMinutes = flightTimeInMinutes;
        this.operator = operator;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
