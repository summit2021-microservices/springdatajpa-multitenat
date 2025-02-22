package com.jornadacolaborativa.microservice.controller;

import javax.validation.Valid;

import com.jornadacolaborativa.microservice.dto.UserDTO;
import com.jornadacolaborativa.microservice.model.User;
import com.jornadacolaborativa.microservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Iterable<User>> getUsers() {
    Iterable<User> users = userService.getUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getPerson(@PathVariable Integer id) {
    User user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
    User user = userService.createUser(userDTO.getName(), userDTO.getEmail());
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> createUser(@PathVariable Integer id,
      @RequestBody @Valid UserDTO userDTO) {
    User user = userService.updateUser(id, userDTO.getName(), userDTO.getEmail());
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deletePerson(@PathVariable Integer id) {
    User user = userService.delete(id);
    return ResponseEntity.ok(user);
  }

}