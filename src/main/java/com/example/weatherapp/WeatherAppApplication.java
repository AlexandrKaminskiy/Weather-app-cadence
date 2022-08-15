package com.example.weatherapp;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder().setDomain("samples-domain").build());
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker("startWorkFlow");
        worker.registerWorkflowImplementationTypes(WorkerImplementation.class);
        WeatherActivities activities = new WeatherActivitiesImplementation();
        worker.registerActivitiesImplementations(activities);
        factory.start();

        WorkflowWorker task1 = workflowClient.newWorkflowStub(WorkflowWorker.class);
        task1.startWorkFlow();
    }

}
