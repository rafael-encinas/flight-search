package com.flightsearchback.flightsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport {
    private String type;
    private String name;
    private String iataCode;
    private AirportAddress address;
    public Airport(String type, String name, String iataCode, AirportAddress address) {
        this.type = type;
        this.name = name;
        this.iataCode = iataCode;
        this.address = address;
    }

    public Airport() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public AirportAddress getAddress() {
        return address;
    }

    public void setAddress(AirportAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Airport [type=" + type + ", name=" + name + ", iataCode=" + iataCode + ", address=" + address + "]";
    }

    
    
}
