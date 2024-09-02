package com.flightsearchback.flightsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Amenity {
    private String description;
    private boolean isChargeable;
    public Amenity(String description, boolean isChargeable) {
        this.description = description;
        this.isChargeable = isChargeable;
    }
    public Amenity() {
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isChargeable() {
        return isChargeable;
    }
    public void setChargeable(boolean isChargeable) {
        this.isChargeable = isChargeable;
    }

    
}
