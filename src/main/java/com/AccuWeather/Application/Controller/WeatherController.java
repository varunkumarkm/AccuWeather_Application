package com.AccuWeather.Application.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {
	
	
//	@Autowired
//    private WeatherService weatherService;
//    
//    Logger logger = LoggerFactory.getLogger(WeatherController.class);
//
//    @GetMapping("/{city}")
//    public String getWeatherForCity(@PathVariable String city) throws IOException {
//        return weatherService.getWeatherForCity(city);
//    }
	
	

    private final String ACCUWEATHER_API_KEY = "JXBqo7JgXkH8uXsNd10VGKQDayyJALbe";
    
    private final String BASE_URL = "http://dataservice.accuweather.com/";
    
    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @GetMapping("/{cityName}")
    public String getWeatherByCityName(@PathVariable String cityName) throws IOException {
    	
        String currentConditionsUrl = BASE_URL + "currentconditions/v1/1-204108_1_AL";
    	
        currentConditionsUrl += "?apikey=" + ACCUWEATHER_API_KEY;

        URL url = new URL(currentConditionsUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        return response.toString();
    }
}





	




