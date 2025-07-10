package com.weather.aggregator.mappers.exceptions;

public class OpenWeatherMapExceptionResponse {
    private Integer status;
    private String error;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
