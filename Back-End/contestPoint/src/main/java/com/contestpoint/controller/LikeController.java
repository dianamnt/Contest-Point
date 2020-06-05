package com.contestpoint.controller;

import com.contestpoint.dto.AuxDTO;
import com.contestpoint.dto.UserLikeDTO;
import com.contestpoint.service.ContestService;
import com.contestpoint.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private UserLikeService userLikeService;

    @Autowired
    private ContestService contestService;

    @GetMapping("/list")
    public ResponseEntity<List<UserLikeDTO>> listUserLikes() {
        return  ResponseEntity.ok(userLikeService.findAllUserLikes());
    }

    @PostMapping("/saveLike")
    public ResponseEntity<?> saveUserLike(@RequestBody UserLikeDTO userLikeDTO) throws Exception {
        UserLikeDTO newLikeDTO = userLikeService.createUserLike(userLikeDTO);
        return new ResponseEntity<>(newLikeDTO, HttpStatus.OK);
    }

    @PostMapping("/deleteLike")
    public ResponseEntity<?> deleteUserLike(@RequestBody AuxDTO auxDTO) {
        userLikeService.deleteUserLike(auxDTO.getFirstSensitiveDataParam());
        return new ResponseEntity<>(auxDTO, HttpStatus.OK);
    }

    @PostMapping("/updateUserLike")
    public ResponseEntity<String> updateUserLike(@RequestBody UserLikeDTO UserLikeDTO) throws Exception {
        userLikeService.updateUserLike(UserLikeDTO);
        return ResponseEntity.ok("UserLike updated");
    }

    @PostMapping("/isLiked")
    public ResponseEntity<?> isLiked(@RequestBody AuxDTO auxDTO) {
        UserLikeDTO userLikeDTO = userLikeService.isLiked(auxDTO.getFirstSensitiveDataParam(), auxDTO.getSecondSensitiveDataParam());
        if(userLikeDTO != null)
            return new ResponseEntity<>(userLikeDTO, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
