package com.demo.neo4j_api.utility;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FlightPathDTO {
    private String fromAirport;
    private String toAirport;
    private List<String> flights;

    @JsonProperty("price")
    private Double totalPrice;
    @JsonProperty("flightTimeInMinutes")
    private Integer timeInMinutes;

    // Getters and setters
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

}