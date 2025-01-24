package com.demo.neo4j_api;


import com.demo.neo4j_api.dto.*;
import com.demo.neo4j_api.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class APIController {


    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;


    @PutMapping("/cities")
    ResponseEntity<?> registerANewCity(@RequestBody City city) {

        if (city.getCountry().isBlank() || city.getCountry().isEmpty()) {
            return new ResponseEntity("Invalid Country", HttpStatus.BAD_REQUEST);
        } else if (city.getName().isBlank() || city.getCountry().isEmpty()) {
            return new ResponseEntity("Invalid Name", HttpStatus.BAD_REQUEST);
        }

        Optional<City> testCity = cityRepository.findById(city.getName());
        if (testCity.isPresent()) {
            return new ResponseEntity("City alreadye exists", HttpStatus.BAD_REQUEST);
        }

        cityRepository.save(city);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/cities")
    ResponseEntity<?> getCities(@RequestParam(name = "country", required = false) String country) {
        if (country == null || country.isBlank()) {
            System.out.println(country + "wowwww");
            List<City> allCities = cityRepository.findAll();
            return new ResponseEntity<>(allCities, HttpStatus.OK);
        } else {
            System.out.println("");
            CountryRequest countryRequest = new CountryRequest(country);
            List<City> citiesByCountry = cityRepository.findCitiesByCountry(countryRequest.getCountry());
            return new ResponseEntity<>(citiesByCountry, HttpStatus.OK);
        }
    }

    @GetMapping("/cities/{name}")
    ResponseEntity<?> getCity(@PathVariable String name) {
        if (name.isBlank() || name == null) {
            return new ResponseEntity("Invalid country", HttpStatus.BAD_REQUEST);
        }

        Optional<City> cityOptional = cityRepository.findCityByName(name);
        if (cityOptional.isEmpty()) {
            return new ResponseEntity("City not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cityOptional.get(), HttpStatus.OK);
    }


    @PutMapping("/cities/{name}/airports")
    ResponseEntity<?> registerAnAirport(@RequestBody Airport airport, @PathVariable String name) {
        if (airport.getAddress().isBlank() || airport.getAddress().isEmpty()) {
            return new ResponseEntity("Invalid Address", HttpStatus.BAD_REQUEST);
        } else if (airport.getName().isBlank() || airport.getName().isEmpty()) {
            return new ResponseEntity("Invalid Name", HttpStatus.BAD_REQUEST);
        } else if (airport.getNumberOfTerminals() == null) {
            return new ResponseEntity("Invalid number of terminals", HttpStatus.BAD_REQUEST);
        } else if (airport.getCode().isBlank() || airport.getCode().isEmpty()) {
            return new ResponseEntity("Invalid code", HttpStatus.BAD_REQUEST);
        }

        Optional<Airport> existingAirport = airportRepository.findById(airport.getCode());
        if (existingAirport.isPresent()) {
            System.out.println("WOW1    ");
            return new ResponseEntity<>("Airport with this code already exists", HttpStatus.NOT_FOUND);
        }

        Optional<City> cityOptional = cityRepository.findCityByName(name);
        if (cityOptional.isEmpty()) {
            System.out.println("WOW2");
            return new ResponseEntity("City not found", HttpStatus.NOT_FOUND);
        }

        // get the city object of the projected airport location and save it in the airport object
        City city = cityOptional.get();
        airport.setCity(city);

        // save airport node
        Airport savedAirport = airportRepository.save(airport);

        // append cities airport relationship
        Set<Airport> cityAirports = city.getAirports();
        if (cityAirports != null) {
            cityAirports.add(savedAirport);
        } else {
            city.setAirports(Set.of(savedAirport));
        }
        //save the city again
        cityRepository.save(city);
        return new ResponseEntity<>("Airport created succesfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cities/{name}/airports")
    ResponseEntity<?> getAirportsInACity(@PathVariable String name) {

        if (name.isBlank() || name == null) {
            return new ResponseEntity("Invalid country", HttpStatus.BAD_REQUEST);
        }

        Optional<City> cityOptional = cityRepository.findCityByName(name);
        if (cityOptional.isEmpty()) {
            return new ResponseEntity("City not found", HttpStatus.NOT_FOUND);
        }

        List<Airport> airports = cityRepository.findAirportsByCityName(name);
        return new ResponseEntity(airports, HttpStatus.OK);

    }

    @GetMapping("airports/{code}")
    ResponseEntity<?> getAirport(@PathVariable String code) {
        if (code.isBlank() || code == null) {
            return new ResponseEntity("Invalid country", HttpStatus.BAD_REQUEST);
        }

        Optional<ReturnAirportWithACity> airport = cityRepository.findAirportDetailsByCode(code);
        if (airport.isEmpty()) {
            return new ResponseEntity("Airport not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(airport.get(), HttpStatus.OK);
    }

    @PutMapping("flights")
    ResponseEntity<?> registerANewFlight(@RequestBody RequestAirport requestAirport) {

        if(requestAirport.getNumber().isBlank()){
            return new ResponseEntity<>("Invalid flight number", HttpStatus.BAD_REQUEST);
        }

        if(requestAirport.getFromAirport().isBlank()){
            return new ResponseEntity<>("Invalid departure airport code", HttpStatus.BAD_REQUEST);
        }
        if(requestAirport.getToAirport().isBlank()){
            return new ResponseEntity<>("Invalid arrival airport code", HttpStatus.BAD_REQUEST);
        }
        if(requestAirport.getPrice() == null || requestAirport.getPrice() <= 0){
            return new ResponseEntity<>("Invalid price", HttpStatus.BAD_REQUEST);
        }
        if(requestAirport.getFlightTimeInMinutes() == null || requestAirport.getFlightTimeInMinutes() <= 0){
            return new ResponseEntity<>("Invalid flight time", HttpStatus.BAD_REQUEST);
        }
        if(requestAirport.getOperator().isBlank()){
            return new ResponseEntity<>("Invalid operator", HttpStatus.BAD_REQUEST);
        }

        Optional<Airport> departureAirportOpt = airportRepository.findById(requestAirport.getFromAirport());
        Optional<Airport> arrivalAirportOpt = airportRepository.findById(requestAirport.getToAirport());


        if (departureAirportOpt.isEmpty()) {
            return new ResponseEntity<>("Departure airport not found", HttpStatus.NOT_FOUND);
        }
        if (arrivalAirportOpt.isEmpty()) {
            return new ResponseEntity<>("Arrival airport not found", HttpStatus.NOT_FOUND);
        }

        Airport departureAirport = departureAirportOpt.get();
        Airport arrivalAirport = arrivalAirportOpt.get();

        FlightRelation flight = new FlightRelation();
        flight.setNumber(requestAirport.getNumber());
        flight.setOperator(requestAirport.getOperator());
        flight.setPrice(requestAirport.getPrice());
        flight.setFlightTimeInMinutes(requestAirport.getFlightTimeInMinutes());
        flight.setDestination(arrivalAirport);

        departureAirport.addFlight(flight);
        airportRepository.save(departureAirport);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/flights/{code}")
    ResponseEntity<?> getFullFlightInfo(@PathVariable String code ){

        Optional<FlightFullDetails> flightOptional =  cityRepository.findFullDetailsByFlightNumber(code);
        if(flightOptional.isEmpty()) {
            return  new ResponseEntity<>( "Flight was not found",HttpStatus.NOT_FOUND);
        }

        FlightFullDetails flight = flightOptional.get();
        return  new ResponseEntity<>(flight ,HttpStatus.OK);

    }

    @GetMapping("/search/flights/{fromCity}/{toCity}")
    ResponseEntity<?> findFlightsToAndFromCity(@PathVariable String fromCity, @PathVariable String toCity) {
        List<FlightPathDTO> flightPaths = cityRepository.findRawFlightPathsBetweenTwoCities(fromCity, toCity);
        if (flightPaths.isEmpty()) return new ResponseEntity<>("Flights not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(flightPaths, HttpStatus.OK);
    }

    @PostMapping("/cleanup")
    ResponseEntity<?> cleanup(){
        cityRepository.deleteAllNodes();
        return  new ResponseEntity<>(HttpStatus.OK);

    }

}
