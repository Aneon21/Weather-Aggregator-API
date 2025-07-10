package com.weather.aggregator.exceptions;

import com.weather.aggregator.mappers.exceptions.InternalServiceExceptionResponse;

import java.time.LocalDateTime;

public class InternalServiceException extends RuntimeException{
    public InternalServiceException(String message){
        super(message);
    }

    public InternalServiceExceptionResponse getResponse(){
        InternalServiceExceptionResponse response = new InternalServiceExceptionResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setError(this.getMessage());
        response.setDetails("Something went wrong in our service. Please try again later.");
        return response;
    }
}
