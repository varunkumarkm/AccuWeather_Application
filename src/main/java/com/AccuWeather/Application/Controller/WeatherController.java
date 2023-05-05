package com.AccuWeather.Application.Controller;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.AccuWeather.Application.Service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	
    @Autowired
    private WeatherService weatherService;
    
    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @GetMapping("/{city}")
    public String getWeatherForCity(@PathVariable String city) throws IOException {
        return weatherService.getWeatherForCity(city);
    }
}

