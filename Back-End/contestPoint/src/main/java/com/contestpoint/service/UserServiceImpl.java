package com.contestpoint.service;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.mapper.UserMapper;
import com.contestpoint.model.User;
import com.contestpoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.saveData(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.removeData(userId);
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.updateData(user);
    }

    @Override
    @Transactional
    public List<UserDTO> findAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        for (User user : userRepository.findAll()) {
            UserDTO userDTO = userMapper.toDTO(user);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    @Transactional
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        }
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserDTO findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            return null;
        }
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public boolean checkIfUserDoesNotExists(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return true;
        }
        throw new Exception("The user already has an account!");
    }
}
