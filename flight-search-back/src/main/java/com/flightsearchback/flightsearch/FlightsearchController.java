package com.flightsearchback.flightsearch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.flightsearchback.flightsearch.FlightsearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class FlightsearchController {

    @Autowired
    FlightsearchService flightsearchService;

    String access_token= null;

    @Value("${app.client_id}")
    private String client_id;

    @Value("${app.client_secret}")
    private String client_secret;

    @Value("${app.apiUrl}")
    String apiUrl;

    @GetMapping("/airports")
    public List<Airport> getMatchingAriports(@RequestParam String keyword) throws JsonMappingException, JsonProcessingException {
        //

        return flightsearchService.findAirportsByKeyword(keyword);
        
       //return flightsearchService.airportsTest(keyword);
    }

    @GetMapping("/flights")
    public List<TripResult> getFlights(@RequestParam Map<String,String> allParams) throws JsonMappingException, JsonProcessingException {
        //

        return flightsearchService.findFlights(allParams);
        
       //return flightsearchService.airportsTest(keyword);
    }
    


    
}
