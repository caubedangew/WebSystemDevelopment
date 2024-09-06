/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service;

import com.huunghiathienvu.pojo.Comment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThienVu
 */
public interface CommentService {
    List<Comment> getAllComments(Map<String, String> params);
    void addOrUpdateComment(Comment cmt);
    void deleteComment(int commentId);
    Comment getCommentById(int commentId);
}
