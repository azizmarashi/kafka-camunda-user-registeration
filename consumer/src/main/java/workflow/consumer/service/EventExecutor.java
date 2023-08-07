package workflow.consumer.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class EventExecutor implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        System.out.println("New User registered!");

    }

}