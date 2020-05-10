package com.contestpoint.mapper;

import com.contestpoint.dto.UserLikeDTO;
import com.contestpoint.model.Contest;
import com.contestpoint.model.User;
import com.contestpoint.model.UserLike;
import com.contestpoint.repository.ContestRepository;
import com.contestpoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLikeMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContestRepository contestRepository;

    public UserLikeDTO toDTO(UserLike like) {
        UserLikeDTO likeDTO = new UserLikeDTO();

        likeDTO.setUserlikeId(like.getUserlikeId());
        likeDTO.setContestId(like.getContest().getContestId());
        likeDTO.setUserId(like.getUser().getUserId());

        return likeDTO;
    }

    public UserLike toEntity(UserLikeDTO likeDTO) {
        UserLike like = new UserLike();

        like.setUserlikeId(likeDTO.getUserlikeId());
        User u = userRepository.findById(likeDTO.getUserId());
        like.setUser(u);
        Contest c = contestRepository.findById(likeDTO.getContestId());
        like.setContest(c);

        return like;
    }
}
