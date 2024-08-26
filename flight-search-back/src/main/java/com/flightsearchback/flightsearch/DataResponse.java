package com.flightsearchback.flightsearch;
import java.util.*;

public class DataResponse {
    private Object meta;
    private List<Airport> data;
    public DataResponse(Object meta, List<Airport> data) {
        this.meta = meta;
        this.data = data;
    }
    public DataResponse() {
    }
    public Object getMeta() {
        return meta;
    }
    public void setMeta(Object meta) {
        this.meta = meta;
    }
    public List<Airport> getData() {
        return data;
    }
    public void setData(List<Airport> data) {
        this.data = data;
    }

    
}
