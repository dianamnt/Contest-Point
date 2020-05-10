package com.contestpoint.controller;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> listUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        if (userService.checkIfUserDoesNotExists(userDTO.getEmail())) {
            userService.createUser(userDTO);
            return ResponseEntity.ok("User saved");
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyEmailAndPassword(@RequestBody UserDTO user) throws IOException {
        UserDTO userDTO = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (userDTO == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
    }
}
