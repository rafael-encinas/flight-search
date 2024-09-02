package com.flightsearchback.flightsearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft {
    private String code;
    private String description;
    public Aircraft(String code, String description) {
        this.code = code;
        this.description = description;
    }
    public Aircraft(String code) {
        this.code = code;
    }
    public Aircraft() {
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Aircraft [code=" + code + ", description=" + description + "]";
    }


}
