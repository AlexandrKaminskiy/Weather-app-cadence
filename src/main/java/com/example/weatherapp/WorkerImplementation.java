package com.example.weatherapp;

import com.example.weatherapp.model.WeatherInfo;
import com.example.weatherapp.repository.WeatherInfoRepository;
import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;


public class WorkerImplementation implements WorkflowWorker{


    private final ActivityOptions options =
            new ActivityOptions.Builder().setScheduleToCloseTimeout(Duration.ofHours(1)).build();
    private final WeatherActivities activities =
            Workflow.newActivityStub(WeatherActivities.class, options);

    @Override
    public WeatherInfo startWorkFlow() {

        activities.userRequest();
        activities.saveToDb();
        return new WeatherInfo();
    }
}
