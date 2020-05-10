package com.contestpoint.mapper;

import com.contestpoint.dto.UserDTO;
import com.contestpoint.dto.UserLikeDTO;
import com.contestpoint.model.User;
import com.contestpoint.model.UserLike;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserDTO toDTO(User user)
    {
        UserDTO userDTO = new UserDTO() ;
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setInstitutionName(user.getInstitutionName());
        userDTO.setProfilePicture(user.getProfilePicture());

        return userDTO;
    }

    public User toEntity(UserDTO userDTO)
    {
        User user = new User() ;
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setInstitutionName(userDTO.getInstitutionName());
        user.setProfilePicture(userDTO.getProfilePicture());

        return user;
    }
}
