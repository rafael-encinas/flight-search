package com.flightsearchback.flightsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TripResult {
    private String id;
    private Itinerary[] itineraries;
    private Price price;
    private TravelerPricing[] travelerPricings;
    private String grandTotal;
    private String totalTravelTime;
    public TripResult(String id, Itinerary[] itineraries, Price price, TravelerPricing[] travelerPricings,
            String grandTotal, String totalTravelTime) {
        this.id = id;
        this.itineraries = itineraries;
        this.price = price;
        this.travelerPricings = travelerPricings;
        this.grandTotal = grandTotal;
        this.totalTravelTime = totalTravelTime;
    }
    public TripResult(String id, Itinerary[] itineraries, Price price, TravelerPricing[] travelerPricings) {
        this.id = id;
        this.itineraries = itineraries;
        this.price = price;
        this.travelerPricings = travelerPricings;
    }
    public TripResult() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Itinerary[] getItineraries() {
        return itineraries;
    }
    public void setItineraries(Itinerary[] itineraries) {
        this.itineraries = itineraries;
    }
    public Price getPrice() {
        return price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }
    public TravelerPricing[] getTravelerPricings() {
        return travelerPricings;
    }
    public void setTravelerPricings(TravelerPricing[] travelerPricings) {
        this.travelerPricings = travelerPricings;
    }
    public String getGrandTotal() {
        return grandTotal;
    }
    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
    public String getTotalTravelTime() {
        return totalTravelTime;
    }
    public void setTotalTravelTime(String totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }
    

    
}
