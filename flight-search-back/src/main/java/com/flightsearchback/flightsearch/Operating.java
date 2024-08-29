package com.flightsearchback.flightsearch;

public class Operating {
    private String carrierCode;
    private String carrierDescription;

    
    public Operating(String carrierCode, String carrierDescription) {
        this.carrierCode = carrierCode;
        this.carrierDescription = carrierDescription;
    }

    public Operating(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public Operating() {
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierDescription() {
        return carrierDescription;
    }

    public void setCarrierDescription(String carrierDescription) {
        this.carrierDescription = carrierDescription;
    }
    
}
