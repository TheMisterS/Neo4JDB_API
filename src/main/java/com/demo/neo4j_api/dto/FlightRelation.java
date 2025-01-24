package com.demo.neo4j_api.dto;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class FlightRelation {

    @Id
    @GeneratedValue
    private Long id;

    @Property("number")
    private String number;
    @Property("operator")
    private String operator;
    @Property("price")
    private Double price;
    @Property("flightTimeInMinutes")
    private Integer flightTimeInMinutes;

    @TargetNode
    private Airport destination;

    public FlightRelation(String number, String operator, Double price, Integer flightTimeInMinutes, Airport destination) {
        this.number = number;
        this.operator = operator;
        this.price = price;
        this.flightTimeInMinutes = flightTimeInMinutes;
        this.destination = destination;
    }

    public FlightRelation() {
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

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
}
