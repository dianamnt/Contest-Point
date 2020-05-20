package com.contestpoint.controller;

import com.contestpoint.dto.UserLikeDTO;
import com.contestpoint.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private UserLikeService userLikeService;

    @GetMapping("/list")
    public ResponseEntity<List<UserLikeDTO>> listUserLikes() {
        return  ResponseEntity.ok(userLikeService.findAllUserLikes());
    }

    @PostMapping("/saveUserLike")
    public ResponseEntity<String> saveUserLike(@RequestBody UserLikeDTO UserLikeDTO) throws Exception {
        userLikeService.createUserLike(UserLikeDTO);
        return ResponseEntity.ok("UserLike saved");
    }

    @PostMapping("/deleteUserLike/{UserLikeId}")
    public ResponseEntity<String> deleteUserLike(@PathVariable("UserLikeId") Long id) {
        userLikeService.deleteUserLike(id);
        return ResponseEntity.ok("UserLike deleted");
    }

    @PostMapping("/updateUserLike")
    public ResponseEntity<String> updateUserLike(@RequestBody UserLikeDTO UserLikeDTO) throws Exception {
        userLikeService.updateUserLike(UserLikeDTO);
        return ResponseEntity.ok("UserLike updated");
    }
}
