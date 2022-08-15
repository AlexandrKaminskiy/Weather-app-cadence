package com.example.weatherapp;

import com.example.weatherapp.model.WeatherInfo;
import com.uber.cadence.activity.ActivityMethod;

import java.util.Map;

public interface WeatherActivities {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 1)
    Map<?,?> userRequest();
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 1)
    WeatherInfo saveToDb();
}
