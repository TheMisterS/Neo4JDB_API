package com.demo.neo4j_api.utility;

import java.util.List;

public class FlightsBetweenCitiesInfo {

    String fromAirport;
    String toAirport;

    List<String> flights;

    Double price;
    Integer timeInMinutes;

    public FlightsBetweenCitiesInfo() {
    }

    public FlightsBetweenCitiesInfo(Integer timeInMinutes, Double price, List<String> flights, String toAirport, String fromAirport) {
        this.timeInMinutes = timeInMinutes;
        this.price = price;
        this.flights = flights;
        this.toAirport = toAirport;
        this.fromAirport = fromAirport;
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

    public List<String> getFlights() {
        return flights;
    }

    public void setFlights(List<String> flights) {
        this.flights = flights;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
}
