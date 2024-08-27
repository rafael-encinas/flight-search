package com.flightsearchback.flightsearch;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;
import java.util.stream.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter; 

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
        String url = apiUrl + "/v1/security/oauth2/token";
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

    public Object findAirportsByKeywordDeprecated(String keyword) throws JsonMappingException, JsonProcessingException{

        String url = apiUrl + "/reference-data/locations?subType=AIRPORT&keyword="+keyword;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);


        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

        return response.getBody();
        
    }

    public List<Airport> findAirportsByKeyword(String keyword) throws JsonMappingException, JsonProcessingException{

        List<Map> list = null;
        List<Airport> listAirports = new ArrayList<Airport>();
 
        String url = apiUrl + "/v1/reference-data/locations?subType=AIRPORT&keyword="+keyword;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);

        System.out.println("Request url:");
        System.out.println(url);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
    
        list = (List<Map>) response.getBody().get("data");

        for (Map item : list) { // we iterate for each one of the items of the list transforming it
            System.out.println(item.get("address"));

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(item.get("address"));

            ObjectMapper objectMapper = new ObjectMapper();
            AirportAddress address = objectMapper.readValue(json, AirportAddress.class);
            
            Airport airport = new Airport();

            airport.setType(item.get("type").toString());
            airport.setName(item.get("name").toString());
            airport.setIataCode(item.get("iataCode").toString());
            airport.setAddress(address);

            listAirports.add(airport);
        }

        return listAirports;
        
    }

    public List<Map> findFlights(Map<String,String> allParams) throws JsonMappingException, JsonProcessingException{

        List<Map> list = null;
        List<Airport> listAirports = new ArrayList<Airport>();
 
        String url = apiUrl + "/v2/shopping/flight-offers?originLocationCode="+ allParams.get("originLocationCode")
                            +"&destinationLocationCode="+ allParams.get("destinationLocationCode")
                            +"&departureDate="+ allParams.get("departureDate")
                            +"&adults="+ allParams.get("adults")
                            +"&nonStop=" + allParams.get("nonStop")
                            +"&max=25";
        if(allParams.containsKey("returnDate")){
            url = url + "&returnDate=" + allParams.get("returnDate");
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);

        System.out.println("Request url:");
        System.out.println(url);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
    
        list = (List<Map>) response.getBody().get("data");

        for (Map item : list) { // we iterate for each one of the items of the list transforming it
            /*

            System.out.println(item.get("address"));

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(item.get("address"));

            ObjectMapper objectMapper = new ObjectMapper();
            AirportAddress address = objectMapper.readValue(json, AirportAddress.class);
            
            Airport airport = new Airport();

            airport.setType(item.get("type").toString());
            airport.setName(item.get("name").toString());
            airport.setIataCode(item.get("iataCode").toString());
            airport.setAddress(address);

            listAirports.add(airport);
            */
            System.out.println(item);
            System.out.println("////////////////");
        }

        return list;
        
    }
    
}
