package workflow.producer.service;

import workflow.producer.dto.PersonDto;
import workflow.producer.dto.UserRegisterDto;

import java.util.List;

public interface UserService {

    String register(UserRegisterDto userRegisterDto) throws Exception;
    List<PersonDto> getAll();
}
