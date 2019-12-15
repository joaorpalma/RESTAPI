package com.simplenotes.RESTAPI.Controller;

import com.simplenotes.RESTAPI.Models.User;
import com.simplenotes.RESTAPI.Results.ResponseWrapper;
import com.simplenotes.RESTAPI.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.util.List;

import static com.simplenotes.RESTAPI.Constants.ApiConstants.MESSAGE_FOR_REGEX_NUMBER_MISMATCH;
import static com.simplenotes.RESTAPI.Constants.ApiConstants.REGEX_FOR_NUMBERS;

@Validated
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService _userService;

    @GetMapping(value = "/{id}")
    public ResponseWrapper<User> getUserById(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(_userService.getById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseWrapper<List<User>> getAllusers() {
        return new ResponseWrapper<>(_userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseWrapper<User> createUser(@Valid @RequestBody User user) {
        return new ResponseWrapper<>(_userService.add(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseWrapper<User> updateUser(@Valid @RequestBody User user){
        return new ResponseWrapper<>(_userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<User> deleteUser(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(_userService.deleteById(Integer.parseInt(id)), HttpStatus.OK);
    }
}
