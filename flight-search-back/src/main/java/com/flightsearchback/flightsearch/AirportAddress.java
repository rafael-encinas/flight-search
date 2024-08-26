package com.flightsearchback.flightsearch;

public class AirportAddress {
    String cityName;
    String cityCode;
    String countryName;
    String countryCode;

    public AirportAddress(String cityName, String cityCode, String countryName, String countryCode) {
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public AirportAddress(){
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    

    
}
