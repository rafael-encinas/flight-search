package com.flightsearchback.flightsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    private String iataCode;
    private String at;
    private String terminal;
    private String cityName;
    
    public Flight(String iataCode, String at, String terminal, String cityName) {
        this.iataCode = iataCode;
        this.at = at;
        this.terminal = terminal;
        this.cityName = cityName;
    }
    public Flight(String iataCode, String at, String terminal) {
        this.iataCode = iataCode;
        this.at = at;
        this.terminal = terminal;
    }
    public Flight(String iataCode, String at) {
        this.iataCode = iataCode;
        this.at = at;
    }
    public Flight() {
    }
    public String getIataCode() {
        return iataCode;
    }
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }
    public String getAt() {
        return at;
    }
    public void setAt(String at) {
        this.at = at;
    }
    public String getTerminal() {
        return terminal;
    }
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    @Override
    public String toString() {
        return "Flight [iataCode=" + iataCode + ", at=" + at + ", terminal=" + terminal + ", cityName=" + cityName
                + "]";
    }
    
    
    
}
