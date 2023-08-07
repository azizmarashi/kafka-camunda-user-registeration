package workflow.consumer.service;


import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.consumer.entity.ProcessActivity;
import workflow.consumer.repository.ProcessRepository;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ProcessRepository processRepository;

    @Override
    public void addNewEmployee() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.signalEventReceived("added_new_employee");
    }

    @Override
    public int getProcessCount() {
        return processRepository.processCount();
    }

    @Override
    public List<Object> getProcessList() {
       return processRepository.getProcessList();
    }

    @Override
    public List<ProcessActivity> getProcessActivity(String username) {
        return processRepository.findAllByUsername(username);
    }

    @Override
    public int getProcessActivityCount(String username) {
        return processRepository.activityCountByUsername(username);
    }

    @Override
    public List<Object> getProcessHistory(String processName) {
        return processRepository.getAllProcessHistory(processName);
    }


}