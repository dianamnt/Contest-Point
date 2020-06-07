package com.contestpoint.controller;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.helper.util.JwtResponse;
import com.contestpoint.helper.util.JwtUtil;
import com.contestpoint.service.UserService;
import com.contestpoint.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO u = userService.findById(userDTO.getUserId());
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setEmail(userDTO.getEmail());
        u.setProfilePicture(userDTO.getProfilePicture());
        u.setInstitutionName(userDTO.getInstitutionName());
        if(userDTO.getPassword() != "")
            u.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userService.updateUser(u);
        return new ResponseEntity<>(u, HttpStatus.OK);
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
