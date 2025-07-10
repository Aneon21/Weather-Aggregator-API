package com.weather.aggregator.controllers;

import com.weather.aggregator.mappers.responses.WeatherResponse;
import com.weather.aggregator.mappers.responses.HealthResponse;
import com.weather.aggregator.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherController {

    WeatherService service;

    @Autowired
    public WeatherController(WeatherService service){
        this.service = service;
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getOpenWeatherMapData(@RequestParam(name = "city") String city){
        var response = service.getResponse(city);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> checkOpenWeatherMapHealth(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getHealthResponse());
    }
}
