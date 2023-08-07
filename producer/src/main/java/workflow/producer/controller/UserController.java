package workflow.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workflow.producer.dto.PersonDto;
import workflow.producer.dto.UserRegisterDto;
import workflow.producer.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
        return new ResponseEntity<>(userService.register(userRegisterDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
