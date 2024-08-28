package com.flightsearchback.flightsearch;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelerPricing {
    private String travelerId;
    private String fareOption;
    private String travelerType;
    private Price price;
    private FareDetailBySegment[] fareDetailsBySegment;
    public TravelerPricing(String travelerId, String fareOption, String travelerType, Price price,
            FareDetailBySegment[] fareDetailsBySegment) {
        this.travelerId = travelerId;
        this.fareOption = fareOption;
        this.travelerType = travelerType;
        this.price = price;
        this.fareDetailsBySegment = fareDetailsBySegment;
    }
    public TravelerPricing() {
    }
    public String getTravelerId() {
        return travelerId;
    }
    public void setTravelerId(String travelerId) {
        this.travelerId = travelerId;
    }
    public String getFareOption() {
        return fareOption;
    }
    public void setFareOption(String fareOption) {
        this.fareOption = fareOption;
    }
    public String getTravelerType() {
        return travelerType;
    }
    public void setTravelerType(String travelerType) {
        this.travelerType = travelerType;
    }
    public Price getPrice() {
        return price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }
    public FareDetailBySegment[] getFareDetailsBySegment() {
        return fareDetailsBySegment;
    }
    public void setFareDetailsBySegment(FareDetailBySegment[] fareDetailsBySegment) {
        this.fareDetailsBySegment = fareDetailsBySegment;
    }
    @Override
    public String toString() {
        return "TravelerPricing [travelerId=" + travelerId + ", fareOption=" + fareOption + ", travelerType="
                + travelerType + ", price=" + price + ", fareDetailsBySegment=" + Arrays.toString(fareDetailsBySegment)
                + "]";
    }
    
}
