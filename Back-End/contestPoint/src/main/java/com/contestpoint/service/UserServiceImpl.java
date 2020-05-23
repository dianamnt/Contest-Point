package com.contestpoint.service;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.mapper.UserMapper;
import com.contestpoint.model.User;
import com.contestpoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.saveData(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.removeData(userId);
    }

    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.updateData(user);
    }

    @Transactional
    public List<UserDTO> findAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        for (User user : userRepository.findAll()) {
            UserDTO userDTO = userMapper.toDTO(user);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Transactional
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        }
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            return null;
        }
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), emptyList());
    }

//    @Override
    @Transactional
    public boolean checkIfUserDoesNotExists(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return true;
        }
        throw new Exception("The user already has an account!");
    }
}
