package com.coyoapp.tinytask.web;

import com.coyoapp.tinytask.ResourceConstants;
import com.coyoapp.tinytask.domain.User;
import com.coyoapp.tinytask.dto.TaskResponse;
import com.coyoapp.tinytask.repository.UserRepository;
import com.coyoapp.tinytask.service.TinyTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(ResourceConstants.TINY_TASKS_V1_SECURED)
@RequiredArgsConstructor
public class TinyTaskController {

  @Autowired
  private UserRepository userRepository;

  private final TinyTaskService tinyTaskService;

  @GetMapping(path = "user/{userId}/tasks",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<TaskResponse>> getAllUserTasks(@PathVariable String userId) {
    return new ResponseEntity<>(tinyTaskService.getAllUserTasks(userId), HttpStatus.OK);
  }

  @GetMapping(path = "current-user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<User> getLoggedInUser() {
    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String username = principal.getUsername();

    User user = userRepository.findByUsername(username);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
