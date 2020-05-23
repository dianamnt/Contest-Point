package com.contestpoint.controller;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.service.UserService;
import com.contestpoint.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@EnableAsync(proxyTargetClass=true)
@EnableCaching(proxyTargetClass=true)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> listUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long changeId) {
        userService.deleteUser(changeId);
        return ResponseEntity.ok("Change deleted");
    }

    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) throws Exception {
        userService.updateUser(userDTO);
        return ResponseEntity.ok("Change updated");
    }

    @PostMapping("/findByEmail")
    public ResponseEntity<?> findByEmail(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO aux = userService.findByEmail(userDTO.getEmail());
        if(aux != null)
        {
            return new ResponseEntity<>(aux, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
