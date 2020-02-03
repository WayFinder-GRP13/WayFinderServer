package com.WayFinder.Server.Main.RestController;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @RequestMapping(method = RequestMethod.GET, value = "/byCity/{country}/{city}")
    public @ResponseBody Object getWeatherByCity(@PathVariable String city, @PathVariable String country) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<Object> response = restTemplate.
                getForEntity("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country +
                                "&APPID=bc375e921d8d29a6a7679d3791091a08",
                        Object.class);

        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/forecastByCity/{country}/{city}")
    public @ResponseBody Object getForecastByCity(@PathVariable String city, @PathVariable String code) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<Object> response = restTemplate.
                getForEntity("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + code +
                                "&APPID=bc375e921d8d29a6a7679d3791091a08",
                        Object.class);

        return response;
    }

}