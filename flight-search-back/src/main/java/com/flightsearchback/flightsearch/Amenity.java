package com.flightsearchback.flightsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Amenity {
    private String description;
    @JsonProperty("isChargeable")
    private boolean chargeable;
    public Amenity(String description, boolean chargeable) {
        this.description = description;
        this.chargeable = chargeable;
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
        return chargeable;
    }
    public void setChargeable(boolean chargeable) {
        this.chargeable = chargeable;
    }
    @Override
    public String toString() {
        return "Amenity [description=" + description + ", chargeable=" + chargeable + "]";
    }
}
