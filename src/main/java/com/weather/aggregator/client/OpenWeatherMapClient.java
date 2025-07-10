package com.weather.aggregator.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.aggregator.config.OpenWeatherMapConfig;
import com.weather.aggregator.exceptions.InternalServiceException;
import com.weather.aggregator.exceptions.WeatherServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class OpenWeatherMapClient {
    private final String key;
    private final String url;
    private final HttpClient client;
    private final ObjectMapper mapper;

    @Autowired
    public OpenWeatherMapClient(ObjectMapper mapper, OpenWeatherMapConfig config){
        client = HttpClient.newBuilder().build();
        this.mapper = mapper;
        key = config.getKey();
        url = config.getUrl();
    }

    private HttpResponse<String>  fetchData(URI url){
        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InternalServiceException("Unable to process this request right now");
        }
    }

    @Cacheable(value = "weather", key = "#p0")
    public String getData(String cityName){
        URI uri = URI.create(String.format(
                "%s/weather?q=%s&appid=%s&units=metric",
                url,
                cityName,
                key
        ));

        HttpResponse<String> result = fetchData(uri);

        if(result.statusCode() != 200){
            WeatherServiceException exception = new WeatherServiceException("Unable to fetch weather data right now.", mapper);
            exception.setResponse(result.body());
            throw exception;
        }

        System.out.println("Fetched from API: " + result.body());
        return result.body();
    }

    public boolean checkAPI(){
        URI uri = URI.create(String.format(
                "%s/weather?q=%s&appid=%s&units=metric",
                url,
                "London",
                key
        ));
        HttpResponse<String> result = fetchData(uri);

        if(result.statusCode() == 200){
            return true;
        }

        return false;
    }
}
