package com.example.weatherapp.service;

import com.example.weatherapp.model.WeatherInfo;
import com.example.weatherapp.repository.WeatherInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class WeatherInfoService {
    final private WeatherInfoRepository weatherInfoRepository;
    @Value("${api.url}")
    private String url;
    @Value("${api.key}")
    private String key;
    @Value("${api.partofpath}")
    private String partOfPath;

    public Map userReuest(String town) {
        Map<?,?> weatherInfoMap = new LinkedHashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        try {

            weatherInfoMap = restTemplate.getForObject(
                    url + "q=" + town + partOfPath +
                            key, Map.class);
        } catch (HttpClientErrorException e) {
            return weatherInfoMap;
        }
        return weatherInfoMap;
    }

    public WeatherInfo saveTodb(Map<?,?> weatherInfoOpt) {
        AtomicReference<WeatherInfo> weatherInfoAt = new AtomicReference<>();
        String name = (String) (weatherInfoOpt).get("name");
        Double temperature =(Double) ((Map<?,?>)(weatherInfoOpt).get("main")).get("temp");
        weatherInfoAt.set(new WeatherInfo(name, temperature, new Date()));
        weatherInfoRepository.save(weatherInfoAt.get());
        return weatherInfoAt.get();
    }


}
