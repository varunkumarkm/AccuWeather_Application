package com.AccuWeather.Application.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


    @Service
    public class WeatherService {
    	
	    @Value("${accuweather.api.key}")
	    private String apiKey;
	
	    public String getWeatherForCity(String city) throws IOException {
	    	
	        String url = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + city;
	        URLConnection connection = new URL(url).openConnection();
	        InputStream response = connection.getInputStream();
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode node = objectMapper.readTree(response);
	        String locationKey = node.get(0).get("Key").asText();
	
	        url = "http://dataservice.accuweather.com/currentconditions/v1/" + locationKey + "?apikey=" + apiKey;
	        connection = new URL(url).openConnection();
	        response = connection.getInputStream();
	        node = objectMapper.readTree(response);
	        int temperature = node.get(0).get("Temperature").get("Metric").get("Value").asInt();
	        String weatherText = node.get(0).get("WeatherText").asText();
	
	        return "Current weather in " + city + ": " + weatherText + ", temperature: " + temperature + "°C";
    }
}

	
