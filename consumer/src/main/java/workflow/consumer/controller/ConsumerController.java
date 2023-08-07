package workflow.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import workflow.consumer.entity.Event;
import workflow.consumer.entity.ProcessActivity;
import workflow.consumer.service.EventService;
import workflow.consumer.service.HistoryProviderService;

import java.util.List;

@RestController
@RequestMapping("/history")
public class ConsumerController {

    @Autowired
    private HistoryProviderService historyProviderService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getHistory() {
        return new ResponseEntity<>(historyProviderService.getAllEvents(), HttpStatus.OK) ;
    }

    @GetMapping(path = "/process-count")
    public ResponseEntity<Integer> getProcessCount(){
        return ResponseEntity.ok(eventService.getProcessCount());
    }

    @GetMapping(path = "/process-list")
    public ResponseEntity<List<Object>> getProcessList(){
        return ResponseEntity.ok(eventService.getProcessList());
    }

    @GetMapping(path = "/process-activity")
    public ResponseEntity<List<ProcessActivity>> getProcessActivity(@RequestParam(value = "username") String username){
        return ResponseEntity.ok(eventService.getProcessActivity(username));
    }

    @GetMapping(path = "/activity-count")
    public ResponseEntity<Integer> getActivityCount(@RequestParam(value = "username") String username){
        return ResponseEntity.ok(eventService.getProcessActivityCount(username));
    }

    @GetMapping(path = "/process")
    public ResponseEntity<List<Object>> getProcessHistory(@RequestParam(value = "process-name") String processName){
        return ResponseEntity.ok(eventService.getProcessHistory(processName));
    }
}
