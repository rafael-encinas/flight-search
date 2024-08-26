package com.flightsearchback.flightsearch;
import com.flightsearchback.flightsearch.FlightsearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@RestController
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
/* 

*/
    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    @GetMapping("/callclientehello")
    private String getHelloClient(){
        String uri = "http://localhost:8080/api/hello";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @GetMapping("/access")
    private Tokenresponse getToken(){
        Tokenresponse response = flightsearchService.authenticate();
        return response;
    }

    @GetMapping("/hillo")
    private Object getHillo(){
        //Set as .env variable:
        // url, client_id, client_secret
       Object hilloResponse = flightsearchService.findHillo();
       return hilloResponse;
    }

    @GetMapping("/aiports")
    public Object getMatchingAriports(@RequestParam String keyword) {
        //

        return flightsearchService.findAirportsByKeyword(keyword);
    }
    


    
}
