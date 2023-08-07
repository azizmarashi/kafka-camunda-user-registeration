package workflow.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import workflow.producer.domain.Person;
import workflow.producer.dto.EventDto;
import workflow.producer.dto.PersonDto;
import workflow.producer.dto.UserRegisterDto;
import workflow.producer.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private KafkaTemplate<String, EventDto> kafkaTemplate;

    @Autowired
    private UserRepository userRepository;

    @Value("${topic.name}")
    private String TOPIC;

    @Override
    public String register(UserRegisterDto userRegisterDto) {

        if (userRepository.countByUsername(userRegisterDto.getUsername()) > 0)
            throw new RuntimeException("The username is duplicated");

        Person person = new Person();
        person.setUsername(userRegisterDto.getUsername());
        person.setFirstName(userRegisterDto.getFirstName());
        person.setLastName(userRegisterDto.getLastName());
        person.setPassword(userRegisterDto.getPassword());
        person.setPhone(userRegisterDto.getPhone());

        EventDto eventDto = new EventDto();
        eventDto.setUsername(userRegisterDto.getUsername());
        eventDto.setPassword(userRegisterDto.getPassword());
        eventDto.setFirstName(userRegisterDto.getFirstName());
        eventDto.setLastName(userRegisterDto.getLastName());
        eventDto.setPhone(userRegisterDto.getPhone());
        eventDto.setServiceName("producer-service");
        eventDto.setPublisher("UserServiceImpl");

        try {
            kafkaTemplate.send(TOPIC, eventDto);
            userRepository.save(person);
            return "User registered successfully";

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public List<PersonDto> getAll() {

        List<Person> persons = userRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();

        for (Person person : persons) {
            personDtos.add(PersonDto.builder()
                    .username(person.getUsername())
                    .password(person.getPassword())
                    .firstName(person.getFirstName())
                    .lastName(person.getLastName())
                    .phone(person.getPhone())
                    .build());
        }

        return personDtos;
    }
}