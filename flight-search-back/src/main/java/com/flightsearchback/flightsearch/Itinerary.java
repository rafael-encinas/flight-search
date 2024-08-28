package com.flightsearchback.flightsearch;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Itinerary {

    private String duration;
    private Segment[] segments;
    public Itinerary(String duration, Segment[] segments) {
        this.duration = duration;
        this.segments = segments;
    }
    public Itinerary() {
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public Segment[] getSegments() {
        return segments;
    }
    public void setSegments(Segment[] segments) {
        this.segments = segments;
    }
    @Override
    public String toString() {
        return "Itinerary [duration=" + duration + ", segments=" + Arrays.toString(segments) + "]";
    }  
    
}
