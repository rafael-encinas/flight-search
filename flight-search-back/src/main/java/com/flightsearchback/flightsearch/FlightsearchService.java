package com.flightsearchback.flightsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FlightsearchService {

    String access_token= null;

    @Value("${app.client_id}")
    private String client_id;

    @Value("${app.client_secret}")
    private String client_secret;

    @Value("${app.apiUrl}")
    String apiUrl;

    public Tokenresponse authenticate(){
        String url = apiUrl + "/security/oauth2/token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>( "grant_type=client_credentials&client_id="+ client_id +"&client_secret="+client_secret, headers);
        Tokenresponse response = restTemplate.postForObject(url, request, Tokenresponse.class);
        System.out.println(response.getAccess_token());
        if(!response.getAccess_token().equals(null)){
            access_token = response.getAccess_token();
            System.out.println("New access token generated:");
            System.out.println(access_token);
        }
        //System.out.println(response.access_token);
        return response;
    }

    public Object findHillo(){
        String url = apiUrl + "/reference-data/locations?subType=AIRPORT&keyword=hmo";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", "Bearer " + access_token);
        headers.setBearerAuth(access_token);

        //System.out.println( "Bearer " + access_token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

        //String accessToken = response.access_token;

        return response.getBody();
    }

    public List<Airport> findAirportsByKeyword(String keyword){

        String url = apiUrl + "/reference-data/locations?subType=AIRPORT&keyword="+keyword;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);


        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);


        Object[] foundAirports = new Object[]{response.getBody()};

        ObjectMapper objectMapper = new ObjectMapper();
        List<Airport> airports =  Arrays.stream(foundAirports).map(o->objectMapper.convertValue(o, DataResponse.class))
            .map(DataResponse::getData)
            .collect(Collectors.toList()).get(0);
        

        return airports;
        
    }
    
}
