package com.flightsearchback.flightsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FareDetailBySegment {
    private String segmentId;
    private String cabin;
    private String brandedFare;
    @JsonProperty("class")
    private String classLevel;
    private Amenity[] amenities;
    public FareDetailBySegment(String segmentId, String cabin, String brandedFare, String classLevel,
            Amenity[] amenities) {
        this.segmentId = segmentId;
        this.cabin = cabin;
        this.brandedFare = brandedFare;
        this.classLevel = classLevel;
        this.amenities = amenities;
    }
    public FareDetailBySegment() {
    }
    public String getSegmentId() {
        return segmentId;
    }
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }
    public String getCabin() {
        return cabin;
    }
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
    public String getBrandedFare() {
        return brandedFare;
    }
    public void setBrandedFare(String brandedFare) {
        this.brandedFare = brandedFare;
    }
    @JsonProperty("class")
    public String getClassLevel() {
        return classLevel;
    }
    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }
    public Amenity[] getAmenities() {
        return amenities;
    }
    public void setAmenities(Amenity[] amenities) {
        this.amenities = amenities;
    }
    
}
