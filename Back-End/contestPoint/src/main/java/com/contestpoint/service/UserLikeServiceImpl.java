package com.contestpoint.service;

import com.contestpoint.dto.UserLikeDTO;
import com.contestpoint.mapper.UserLikeMapper;
import com.contestpoint.model.Contest;
import com.contestpoint.model.UserLike;
import com.contestpoint.repository.UserLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class UserLikeServiceImpl implements UserLikeService{
    @Autowired
    private UserLikeRepository UserLikeRepository;

    @Autowired
    private UserLikeMapper UserLikeMapper;

    @Override
    @Transactional
    public UserLikeDTO createUserLike(UserLikeDTO UserLikeDTO) {
        UserLike UserLike = UserLikeMapper.toEntity(UserLikeDTO);
        Long id = UserLikeRepository.saveData(UserLike);
        UserLike.setUserlikeId(id);
        return UserLikeMapper.toDTO(UserLike);
    }

    @Override
    @Transactional
    public void deleteUserLike(Long UserLikeId) {
        UserLikeRepository.removeData(UserLikeId);
    }

    @Override
    @Transactional
    public void updateUserLike(UserLikeDTO UserLikeDTO) {
        UserLike UserLike = UserLikeMapper.toEntity(UserLikeDTO);
        UserLikeRepository.updateData(UserLike);
    }

    @Override
    @Transactional
    public List<UserLikeDTO> findAllUserLikes() {
        List<UserLikeDTO> UserLikeDTOList = new ArrayList<UserLikeDTO>();

        for (UserLike UserLike : UserLikeRepository.findAll()) {
            UserLikeDTO UserLikeDTO = UserLikeMapper.toDTO(UserLike);
            UserLikeDTOList.add(UserLikeDTO);
        }

        return UserLikeDTOList;
    }

    @Override
    @Transactional
    public UserLikeDTO findById(Long id) {
        UserLike UserLike = UserLikeRepository.findById(id);
        if (UserLike == null) {
            return null;
        }
        return UserLikeMapper.toDTO(UserLike);
    }

    @Override
    @Transactional
    public UserLikeDTO isLiked(Long userId, Long contestId) {
        UserLike UserLike = UserLikeRepository.isLiked(userId, contestId);
        if (UserLike == null) {
            return null;
        }
        return UserLikeMapper.toDTO(UserLike);
    }
}
