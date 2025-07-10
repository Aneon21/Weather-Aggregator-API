package com.weather.aggregator.mappers.dto.exceptions;

public class OpenWeatherMapException {
    private Integer cod;
    private String message;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
