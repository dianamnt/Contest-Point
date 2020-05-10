package com.contestpoint.service;

import com.contestpoint.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUser(UserDTO user) throws Exception;

    void deleteUser(Long userId);

    void updateUser(UserDTO user) throws Exception;

    List<UserDTO> findAllUsers();

    UserDTO findById(Long id);

    UserDTO findByEmailAndPassword(String email, String password);

    boolean checkIfUserDoesNotExists(String email) throws Exception;
}
