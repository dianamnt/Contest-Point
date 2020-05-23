package com.contestpoint.controller;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.helper.util.JwtResponse;
import com.contestpoint.helper.util.JwtUtil;
import com.contestpoint.model.User;
import com.contestpoint.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAsync(proxyTargetClass=true)
@EnableCaching(proxyTargetClass=true)
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        if (userService.checkIfUserDoesNotExists(userDTO.getEmail())) {
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            userService.createUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getEmail());
        UserDTO userDTO = userService.findByEmail(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails, userDTO);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
