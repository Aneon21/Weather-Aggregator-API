package com.weather.aggregator.mappers.exceptions;

import java.time.LocalDateTime;

public class WeatherServiceExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
    private OpenWeatherMapExceptionResponse downstream;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OpenWeatherMapExceptionResponse getDownstream() {
        return downstream;
    }

    public void setDownstream(OpenWeatherMapExceptionResponse downstream) {
        this.downstream = downstream;
    }
}
