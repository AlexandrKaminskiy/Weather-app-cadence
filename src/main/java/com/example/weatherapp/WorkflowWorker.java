package com.example.weatherapp;

import com.example.weatherapp.model.WeatherInfo;
import com.uber.cadence.workflow.WorkflowMethod;

public interface WorkflowWorker {
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 300)
    WeatherInfo startWorkFlow();
}
