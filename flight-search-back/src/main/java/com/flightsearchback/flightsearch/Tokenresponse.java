package com.flightsearchback.flightsearch;

public class Tokenresponse {
    String type;
    String username;
    String application_name;
    String client_id;
    String token_type;
    String access_token;
    String expires_in;
    String state;
    String scope;

    public Tokenresponse(String type, String username, String application_name, String client_id, String token_type,
            String access_token, String expires_in, String state, String scope) {
        this.type = type;
        this.username = username;
        this.application_name = application_name;
        this.client_id = client_id;
        this.token_type = token_type;
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.state = state;
        this.scope = scope;
    }

    public Tokenresponse(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    

    
}
