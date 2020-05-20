package com.contestpoint.service;

import com.contestpoint.dto.UserLikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLikeService {
    void createUserLike(UserLikeDTO UserLike) throws Exception;

    void deleteUserLike(Long UserLikeId);

    void updateUserLike(UserLikeDTO UserLike) throws Exception;

    List<UserLikeDTO> findAllUserLikes();

    UserLikeDTO findById(Long id);
}
