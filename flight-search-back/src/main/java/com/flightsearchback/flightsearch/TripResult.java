package com.flightsearchback.flightsearch;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TripResult {
    private String id;
    private Itinerary[] itineraries;
    private Price price;
    private TravelerPricing[] travelerPricings;
    private String grandTotal;
    private int totalTravelTime;
    public TripResult(String id, Itinerary[] itineraries, Price price, TravelerPricing[] travelerPricings,
            String grandTotal, int totalTravelTime) {
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
    public int getTotalTravelTime() {
        return totalTravelTime;
    }
    public void setTotalTravelTime(int totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }
    @Override
    public String toString() {
        return "TripResult [id=" + id + ", itineraries=" + Arrays.toString(itineraries) + ", price=" + price
                + ", travelerPricings=" + Arrays.toString(travelerPricings) + ", grandTotal=" + grandTotal
                + ", totalTravelTime=" + totalTravelTime + "]";
    }

    
}
