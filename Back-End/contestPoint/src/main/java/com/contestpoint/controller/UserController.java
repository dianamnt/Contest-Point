package com.contestpoint.controller;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteChange(@PathVariable("userId") Long changeId) {
        userService.deleteUser(changeId);
        return ResponseEntity.ok("Change deleted");
    }

    @PostMapping("/updateUser")
    public ResponseEntity<String> updateChange(@RequestBody UserDTO userDTO) throws Exception {
        userService.updateUser(userDTO);
        return ResponseEntity.ok("Change updated");
    }
}
