package com.contestpoint.service;

import com.contestpoint.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createStudent(UserDTO user) throws Exception;

    void deleteStudent(Long userId);

    void updateStudent(UserDTO user) throws Exception;

    List<UserDTO> findAllStudents();

    UserDTO findById(Long id);

    UserDTO findStudentByEmailAndPassword(String email, String password);

    boolean checkIfStudentDoesNotExists(String email) throws Exception;
}
