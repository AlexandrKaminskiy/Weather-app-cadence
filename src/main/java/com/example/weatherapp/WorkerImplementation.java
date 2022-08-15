package com.example.weatherapp;

import com.example.weatherapp.model.WeatherInfo;
import com.uber.cadence.activity.Activity;
import com.uber.cadence.workflow.Workflow;

public class WorkerImplementation implements WorkflowWorker{
    private WeatherActivities activities = null;

    public WorkerImplementation() {
        this.activities = Workflow.newActivityStub(WeatherActivities.class);
        
    }

    @Override
    public WeatherInfo startWorkFlow() {
        activities.userRequest();
        activities.saveToDb();
        return new WeatherInfo();
    }
}
