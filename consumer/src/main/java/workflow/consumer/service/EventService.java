package workflow.consumer.service;

import workflow.consumer.entity.ProcessActivity;

import java.util.List;

public interface EventService {

    void addNewEmployee();
    int getProcessCount();
    List<Object> getProcessList();
    List<ProcessActivity> getProcessActivity(String username);
    int getProcessActivityCount(String username);
    List<Object> getProcessHistory(String processName);

}