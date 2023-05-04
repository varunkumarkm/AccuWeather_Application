package com.AccuWeather.Application.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.AccuWeather.Application.Entity.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/Accu")
public class WeatherController {
    
    @GetMapping("/weather/{id}")
    public ResponseEntity<WeatherData> getWeatherData(@PathVariable long id) throws JsonMappingException, JsonProcessingException {
        String url = "http://dataservice.accuweather.com/locations/v1/cities/neighbors/{202199}?apikey=b2wXeuk0VGcsTraJkUk0ZBpWkGLTiknv";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        
        WeatherData weatherData = new WeatherData();
        weatherData.setTemperature(root.get(0).get("Temperature").get("Metric").get("Value").asText());
        weatherData.setWeatherCondition(root.get(0).get("WeatherText").asText());
        
        return new ResponseEntity<WeatherData>(HttpStatus.ACCEPTED);
    }
}
