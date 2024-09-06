/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Comment;
import com.huunghiathienvu.service.CommentService;
import com.huunghiathienvu.service.ParkinglotService;
import com.huunghiathienvu.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThienVu
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentSer;
    
    @Autowired
    private UserService userSer;
    
    @Autowired
    private ParkinglotService parkinglotSer;
    
    @GetMapping("")
    public String getListComments(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("comments", this.commentSer.getAllComments(params));
        return "comment";
    }
    
    @GetMapping("/add")
    public String getAddComment(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("user", this.userSer.getAllUsers());
        model.addAttribute("parkinglot", this.parkinglotSer.getParkinglots(null));
        
        return "commentAddOrUpdate";
    }
    
    @GetMapping("/{commentId}")
    public String getUpdateComment(Model model, @PathVariable(value="commentId") int commentId) {
        model.addAttribute("comment", this.commentSer.getCommentById(commentId));
        model.addAttribute("user", this.userSer.getAllUsers());
        model.addAttribute("parkinglot", this.parkinglotSer.getParkinglots(null));
        
        return "commentAddOrUpdate";
    }
    
    @PostMapping("")
    public String postAddOrUpdate(Model model, @ModelAttribute(value="comment") Comment cmt,
            BindingResult rs) {
//        if (rs.hasErrors())
//            return "commentAddOrUpdate";
        try {
            this.commentSer.addOrUpdateComment(cmt);
            return "redirect:/comment";
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }
        
        return "commentAddOrUpdate";
        
        
    }
    
}
