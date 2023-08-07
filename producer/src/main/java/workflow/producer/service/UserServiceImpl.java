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
    public String register(UserRegisterDto userRegisterDto) throws Exception {

        if (userRepository.countByUsername(userRegisterDto.getUsername()) > 0) {
            throw new RuntimeException("نام کاربری تکراری است");
        }
        Person person = Person.builder()
                .username(userRegisterDto.getUsername()).password(userRegisterDto.getPassword()).firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName()).phone(userRegisterDto.getPhone())
                .build();
        userRepository.save(person);



        var eventDto = EventDto.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName())
                .phone(userRegisterDto.getPhone())
                .serviceName("producer-service")
                .publisher("UserServiceImpl") //class name
                .build();


        try {
            kafkaTemplate.send(TOPIC, eventDto);
            return "کاربر با موفقیت ثبت شد";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public List<PersonDto> getAll() {
        var list = userRepository.findAll();
        List<PersonDto> personDto = new ArrayList<>();
        for (Person person : list) {
            personDto.add(PersonDto.builder().username(person.getUsername()).password(person.getPassword()).firstName(person.getFirstName())
                    .lastName(person.getLastName()).phone(person.getPhone()).build());
        }
        return personDto;
    }
}
