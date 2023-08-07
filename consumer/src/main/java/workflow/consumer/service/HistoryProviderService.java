package workflow.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.consumer.entity.Event;
import workflow.consumer.repository.EventRepository;

import java.util.List;

@Service
public class HistoryProviderService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
