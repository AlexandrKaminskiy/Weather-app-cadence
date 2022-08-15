package com.example.weatherapp;

import com.example.weatherapp.model.WeatherInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

public class WeatherActivitiesImplementation implements WeatherActivities{

    @Override
    public Map<?, ?> userRequest() {
        System.out.println("hi");
        return null;
    }

    @Override
    public WeatherInfo saveToDb() {
        System.out.println("bye");
        return null;
    }
}
