package com.coyoapp.tinytask.web;

import com.coyoapp.tinytask.ResourceConstants;
import com.coyoapp.tinytask.dto.UserRequest;
import com.coyoapp.tinytask.dto.UserResponse;
import com.coyoapp.tinytask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @RequestMapping(path = ResourceConstants.TINY_TASKS_V1_SECURED + "users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @RequestMapping(path = ResourceConstants.TINY_TASKS_V1_SECURED + "users/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
    return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
  }

  @RequestMapping(path = ResourceConstants.TINY_TASKS_V1_UNSECURED + "users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
    return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
  }

  @RequestMapping(path = ResourceConstants.TINY_TASKS_V1_SECURED + "users/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserRequest userRequest) {

    return new ResponseEntity<>(userService.updateUser(userRequest, userId), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(path = ResourceConstants.TINY_TASKS_V1_SECURED + "users/{userId}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable String userId) {
    userService.deleteUser(userId);
  }



}
