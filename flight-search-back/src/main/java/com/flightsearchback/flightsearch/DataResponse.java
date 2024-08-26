package com.flightsearchback.flightsearch;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResponse {
    private Object meta;
    private List<Object> data;
    public DataResponse(Object meta, List<Object> data) {
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
    public List<Object> getData() {
        return data;
    }
    public void setData(List<Object> data) {
        this.data = data;
    }

    
}
