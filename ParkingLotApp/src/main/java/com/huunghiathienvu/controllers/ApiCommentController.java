/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Comment;
import com.huunghiathienvu.service.CommentService;
import com.huunghiathienvu.service.ParkinglotService;
import com.huunghiathienvu.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ThienVu
 */
@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class ApiCommentController {

    @Autowired
    private CommentService commentSer;
    @Autowired
    private ParkinglotService parkinglotSer;
    @Autowired
    private UserService userSer;

    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasRole('ADMIN') or principal.username == #commentId.user.username")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delComment(@PathVariable(value = "commentId") int commentId) {
        this.commentSer.deleteComment(commentId);
    }

    @GetMapping("")
    public ResponseEntity<List<Comment>> getCommentList(@RequestParam(required = false) Map<String, String> params) {
        List<Comment> comments = this.commentSer.getAllComments(params);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addComment(@RequestBody Map<String, String> params) {
        String plId = params.get("parkinglotId");
        String content = params.get("content");
        if (!plId.isBlank() && !content.isBlank()) {
            Comment cmt = new Comment();
            cmt.setContent(params.get("content"));
            cmt.setParkinglotId(this.parkinglotSer.getParkinglotById(Integer.parseInt(plId)));
            cmt.setUserId(this.userSer.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            this.commentSer.addOrUpdateComment(cmt);
            
            return new ResponseEntity<>("Created succesfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Insuffficient data", HttpStatus.BAD_REQUEST);
    }
}
