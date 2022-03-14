package com.example.challengerooftop.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestErrorResponse<DETAILS> {

    private String message;
    private Integer statusCode;
    private List<DETAILS> details;

    public RequestErrorResponse() {
    }

    public RequestErrorResponse(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<DETAILS> getDetails() {
        return details;
    }

    public void setDetails(List<DETAILS> details) {
        this.details = details;
    }
}
