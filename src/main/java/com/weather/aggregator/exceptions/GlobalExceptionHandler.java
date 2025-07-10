package com.weather.aggregator.exceptions;

import com.weather.aggregator.mappers.exceptions.InternalServiceExceptionResponse;
import com.weather.aggregator.mappers.exceptions.WeatherServiceExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InternalServiceException.class)
    public ResponseEntity<InternalServiceExceptionResponse> handleServiceErrors(InternalServiceException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getResponse());
    }

    @ExceptionHandler(WeatherServiceException.class)
    public ResponseEntity<WeatherServiceExceptionResponse> handleOpenWeatherMapError(WeatherServiceException e){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getResponse());
    }
}
