package workflow.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import workflow.consumer.dto.EventDto;
import workflow.consumer.entity.Event;
import workflow.consumer.repository.EventRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class MessageConsumer {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "test_user_group2")
    public void consume(ConsumerRecord<String, String> event) throws JsonProcessingException {
        var eventModel = objectMapper.readValue(event.value(), EventDto.class);

        var eventEntity = Event.builder()
                .phone(eventModel.getPhone())
                .username(eventModel.getUsername())
                .firstName(eventModel.getFirstName())
                .lastName(eventModel.getLastName())
                .password(eventModel.getPassword())
                .serviceName(eventModel.getServiceName())
                .publisher(eventModel.getPublisher())
                .timestamp(LocalDateTime.now(ZoneOffset.UTC))
                .build();


        eventRepository.insert(eventEntity);
        eventService.addNewEmployee();

        IdentityService identityService = ProcessEngines.getDefaultProcessEngine().getIdentityService();
        User user = identityService.newUser(eventModel.getUsername());
        user.setId(eventModel.getUsername());
        user.setFirstName(eventModel.getFirstName());
        user.setLastName(eventModel.getLastName());
        user.setPassword(eventModel.getPassword());

        identityService.saveUser(user);

    }
}