/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.huunghiathienvu.service;

import com.huunghiathienvu.pojo.User;
import com.huunghiathienvu.transientpojo.MfaTokenData;
import dev.samstevens.totp.exceptions.QrGenerationException;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienVu
 */
public interface UserService extends UserDetailsService{
    User getUserByUsername(String username);
    boolean authUser(String username, String password);
    User addUser(Map<String, String> params, MultipartFile avatar);
    List<User> getAllUsers();
    MfaTokenData mfaSetup(String username) throws UnknownError, QrGenerationException;
    void changeUser(Map<String, String> params, MultipartFile avatar);
}
