package com.wcci.babytracker.shared;

public class AuthenticationResponse {
    private String response;
    private Long id;
    private String fullName;

    public AuthenticationResponse(String response, Long id, String fullName) {
        this.response = response;
        this.id = id;
        this.fullName = fullName;
    }

    public AuthenticationResponse(String response) {
        this.response = response;
    }

    public AuthenticationResponse(Response response) {
        this.response = response.name();
    }

    public String getResponse() {
        return response;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
