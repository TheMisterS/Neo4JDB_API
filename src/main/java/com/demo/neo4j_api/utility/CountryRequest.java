package com.demo.neo4j_api.utility;

public class CountryRequest {
    private String country;

    public CountryRequest() {
    }

    public CountryRequest(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
