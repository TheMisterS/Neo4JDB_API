package com.demo.neo4j_api.utility;

public class ReturnAirportWithACity {
    String code;
    String name;
    Integer numberOfTerminals;
    String address;
    String city;


    public ReturnAirportWithACity(String code, String name, Integer numberOfTerminals, String address, String city) {
        this.code = code;
        this.name = name;
        this.numberOfTerminals = numberOfTerminals;
        this.address = address;
        this.city = city;
    }

    public ReturnAirportWithACity() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
