/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.huunghiathienvu.pojo.Comment;
import com.huunghiathienvu.repository.CommentRepository;
import com.huunghiathienvu.service.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienVu
 */
@Service
public class CommentServiceImplement implements CommentService {
    @Autowired
    private CommentRepository commentRepo;

    @Override
    public List<Comment> getAllComments(Map<String, String> params) {
        return this.commentRepo.getAllComments(params);
    }

    @Override
    public void addOrUpdateComment(Comment cmt) {
        this.commentRepo.addOrUpdateComment(cmt);
    }

    @Override
    public void deleteComment(int commentId) {
        this.commentRepo.deleteComment(commentId);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return this.commentRepo.getCommentById(commentId);
    }
    
}
