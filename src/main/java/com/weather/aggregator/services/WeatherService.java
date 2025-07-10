package com.weather.aggregator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.aggregator.client.OpenWeatherMapClient;
import com.weather.aggregator.exceptions.InternalServiceException;
import com.weather.aggregator.mappers.dto.reponses.OpenWeatherMapResponse;
import com.weather.aggregator.mappers.dto.reponses.Weather;
import com.weather.aggregator.mappers.responses.HealthResponse;
import com.weather.aggregator.mappers.responses.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherService {
    private OpenWeatherMapClient client;
    private ObjectMapper mapper;

    @Autowired
    public WeatherService(OpenWeatherMapClient client, ObjectMapper mapper){
        this.client = client;
        this.mapper = mapper;
    }

    private OpenWeatherMapResponse getResult(String city){
        String data = client.getData(city);

        try{
            return mapper.readValue(data, OpenWeatherMapResponse.class);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InternalServiceException("Unable to process this request right now");
        }
    }

    public WeatherResponse getResponse(String city){
        OpenWeatherMapResponse result = getResult(city);

        WeatherResponse response = new WeatherResponse();
        response.setCity(result.getName());
        response.setTemp(result.getMain().getTemp());
        Weather weather = result.getWeather().getFirst();
        response.setCond((weather != null)?weather.getMain():null);

        return response;
    }

    private boolean isOpenWeatherMapUp(){
        return client.checkAPI();
    }

    public HealthResponse getHealthResponse(){
        HealthResponse response = new HealthResponse();
        response.setTimestamp(LocalDateTime.now());

        if(isOpenWeatherMapUp()){
            response.setStatus("UP");
        }
        else{
            response.setStatus("DOWN");
        }

        return response;
    }
}
