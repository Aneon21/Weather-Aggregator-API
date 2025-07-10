package com.weather.aggregator.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.aggregator.mappers.dto.exceptions.OpenWeatherMapException;
import com.weather.aggregator.mappers.exceptions.OpenWeatherMapExceptionResponse;
import com.weather.aggregator.mappers.exceptions.WeatherServiceExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class WeatherServiceException extends RuntimeException{

    private ObjectMapper mapper;
    private WeatherServiceExceptionResponse response;

    public WeatherServiceException(String message, ObjectMapper mapper){
        super(message);
        this.mapper = mapper;
    }

    public void setResponse(String body){
        try{
            OpenWeatherMapException exception = mapper.readValue(body, OpenWeatherMapException.class);
            OpenWeatherMapExceptionResponse downstream = new OpenWeatherMapExceptionResponse();
            downstream.setError(exception.getMessage());
            downstream.setStatus(exception.getCod());

            WeatherServiceExceptionResponse response = new WeatherServiceExceptionResponse();
            response.setTimestamp(LocalDateTime.now());
            response.setMessage(this.getMessage());
            response.setDownstream(downstream);

            this.response = response;
        }
        catch (Exception e){
            throw new InternalServiceException("Unable to process this request right now.");
        }
    }

    public WeatherServiceExceptionResponse getResponse(){
        return response;
    }
}
