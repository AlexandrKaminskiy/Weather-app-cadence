package com.example.weatherapp;

import com.example.weatherapp.model.WeatherInfo;
import com.uber.cadence.activity.ActivityMethod;

import java.util.Map;

public interface WeatherActivities {
    Map<?,?> userRequest();
    WeatherInfo saveToDb();
}
