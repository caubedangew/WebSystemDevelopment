/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.huunghiathienvu.service;

import com.huunghiathienvu.pojo.User;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienVu
 */
public interface UserService extends UserDetailsService{
    User getUserByUsername(String username);
    boolean authUser(String username, String password);
    User addUser(Map<String, String> params, MultipartFile avatar);
}